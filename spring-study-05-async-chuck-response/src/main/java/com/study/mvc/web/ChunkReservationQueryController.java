package com.study.mvc.web;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.study.mvc.Delayer;
import com.study.mvc.domain.Reservation;
import com.study.mvc.service.ReservationService;


@Controller
@RequestMapping("/reservationQuery")
public class ChunkReservationQueryController {

    private final ReservationService reservationService;
    private final TaskExecutor taskExecutor;

    public ChunkReservationQueryController(ReservationService reservationService, TaskExecutor taskExecutor) {
        this.reservationService = reservationService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping
    public void setupForm() {
    }

    @PostMapping
    public Callable<String> sumbitForm(@RequestParam("courtName") String courtName, Model model) {

        return () -> {
            List<Reservation> reservations = java.util.Collections.emptyList();

            if (courtName != null) {
                Delayer.randomDelay();
                reservations = reservationService.query(courtName);
            }

            model.addAttribute("reservations", reservations);

            return "reservationQuery";
        };
    }

//    /**
//     * ResponseBodyEmitter 클래스는 하나의 결과 대신 여러 객체를 클라이언트에 반활할 때 유용하다.
//     * 반환할 객체는 HttpMessageConverter를 이용해 결과로 변환한 다음 전송하며 
//     * 핸들러 메서드는 ResponseBodyEmitter를 반드시 반환한다.
//     * 
//     * 다음은 예약리스트를 조회하고 그 결과 레코드를 반환한다.
//     * 전부다 반환되면 결과 전송을 담당한 스레드가 처리를 마친 다음 응답을 처리할 수 있게끔 complete() 메서드를
//     * 호출해서 메모리에서 해제한다.
//     * 
//     * 이과정에서 발생한 예외를 유저에 알리고 싶은 경우 completeWithError를 호출하면 MVC Exception 핸들러를
//     * 통해 처리되고, 응답은 그 이후 완료된다.
//     * 
//     * httppie 윈도우에서 설치 : 파이썬 설치후 pip install --upgrade httpie 실행
//     * httppie 설치후 command 창에서 다음과 같이 입력후 테스트
//     * http http://localhost:8080/spring-study-05-async-chuck-response/reservationQuery courtName==Tennis#1
//     */
//    @GetMapping(params = "courtName")
//    public ResponseBodyEmitter find(@RequestParam("courtName") String courtName) {
//        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
//        taskExecutor.execute(() -> {
//            Collection<Reservation> reservations = reservationService.query(courtName);
//            try {
//                for (Reservation reservation : reservations) {
//                    emitter.send(reservation);
//                }
//                emitter.complete();
//            } catch (IOException e) {
//                emitter.completeWithError(e);
//            }
//        });
//        
//        /*
//         * 커스텀 헤더를 추가하고 싶으면 반환타입은 ResponseEntity<ResponseBodyEmitter> 로 설정한 후
//         * return ResponseEntity
//         *          .status(HttpStatus.I_AM_A_TEAPOT)
//         *          .header("Custom-Header", "Custom-Value")
//         *          .body(emitter);
//         * 형태로 반환하면 된다.
//         */
//        return emitter;
//    }
    
    
    /**
     * SseEmitter는 서버 전송 이벤트를 이용해 서버 -> 클라이언트 방향으로 이벤트를 전송한다.
     * 서버 전송 이벤트는 서버가 클라이언트에 보내는 메시지로
     * text/event-stream이라는 콘텐트 타입 헤더가 들어 있다.
     * 
     * 서버 전송 이벤트 필드 목록은 다음과 같다
     * id 이벤트아이디
     * event 이벤트타입
     * data 이벤트데이터
     * retry 이벤트 스트림에 재접속하는 시간
     * 
     * 핸들러 메서드에서 이벤트를 전송하려면 SseEmitter 인스턴스를 만들어 마지막 줄에서 반환한다.
     * http http://localhost:8080/spring-study-05-async-chuck-response/reservationQuery courtName==Tennis#1
     * 
     * 
     * HTTP/1.1 200
     * Connection: keep-alive
     * Content-Type: text/event-stream;charset=UTF-8
     * Date: Tue, 27 Oct 2020 14:42:03 GMT
     * Keep-Alive: timeout=60
     * Transfer-Encoding: chunked
     * 
     * id:217372331
     * data:{"courtName":"Tennis#1"...
     */
    @GetMapping(params = "courtName")
    public SseEmitter findAndReturnUsingSseEmitter(@RequestParam("courtName") String courtName) {
        final SseEmitter emitter = new SseEmitter();
        taskExecutor.execute(() -> {
            Collection<Reservation> reservations = reservationService.query(courtName);
            try {
                for (Reservation reservation : reservations) {
                    Delayer.delay(75); //서로 다른 이벤트가 유입되는 모습을 관찰하기 위한 코드
                   // emitter.send(reservation);
                    //서버전송 이벤트 필드 설정 후 전송
                    //이벤트마다 id, data 필드를 채워서 전송한다
                    emitter.send(SseEmitter.event().id(String.valueOf(reservation.hashCode())).data(reservation));
                }
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
    
}