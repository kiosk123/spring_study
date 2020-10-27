package com.study.mvc.web;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import com.study.mvc.Delayer;
import com.study.mvc.domain.Reservation;
import com.study.mvc.service.ReservationService;


@Controller
@RequestMapping("/reservationQuery")
public class AsyncReservationQueryController {

    private final ReservationService reservationService;
    private final TaskExecutor taskExecutor;
    private final AsyncListenableTaskExecutor asyncTaskExecutor;

    public AsyncReservationQueryController(ReservationService reservationService, 
            AsyncTaskExecutor taskExecutor,
            AsyncListenableTaskExecutor asyncTaskExecutor) {
        this.reservationService = reservationService;
        this.taskExecutor = taskExecutor;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    @GetMapping
    public void setupForm() {}

    @PostMapping
    public Callable<String> sumbitForm(@RequestParam("courtName") String courtName, Model model) {
        return () -> {
            List<Reservation> reservations = java.util.Collections.emptyList();

            if (courtName != null) {
                Delayer.randomDelay(); //처리시간 모의를 위한 랜덤 딜레이 생성기
                reservations = reservationService.query(courtName);
            }

            model.addAttribute("reservations", reservations);

            return "reservationQuery";
        };
    }
    
    
    /**
     * Callable 대신에 Defered Result 사용
     */
    @PostMapping
    public DeferredResult<String> sumbitFormByDefered(@RequestParam("courtName") String courtName, Model model) {
        final DeferredResult<String> result = new DeferredResult<>();
        
        // taskExeutor에 Runnable로 실행할 작업을 전달
        taskExecutor.execute(() -> {
            List<Reservation> reservations = java.util.Collections.emptyList();

            if (courtName != null) {
                Delayer.randomDelay();
                reservations = reservationService.query(courtName);
            }

            model.addAttribute("reservations", reservations);
            //DeferredResult의 setResult에 결과값을 설정
            //예외가 발생시 DeferredResult.setErrorResult의 인수로 보내 처리한다.
            result.setResult("reservationQuery");
            
            //result.setErrorResult(new Exception()); //값으로 예외가 설정되면 ExceptionHandler가 처리됨
        });
        return result;
    }
    
    /**
     * CompletableFuture 사용
     */
    @PostMapping
    public CompletableFuture<String> sumbitFormByFuture(@RequestParam("courtName") String courtName, Model model) {

        //첫번째 파라미터가 Runnable, 두번째가 TaskExecutor
        //이것을 이용해서 CompletableFuture 인스턴스를 조합하거나 연결해서 모든 기능을 최대한 활용할 수 있다.
        return CompletableFuture.supplyAsync(() -> {
            List<Reservation> reservations = java.util.Collections.emptyList();

            if (courtName != null) {
                Delayer.randomDelay();
                reservations = reservationService.query(courtName);
            }

            model.addAttribute("reservations", reservations);

            return "reservationQuery";

        }, taskExecutor);
    }
    
    /**
     * ListenableFuture를 활용
     * ListenableFuture은 내부적으로 성공 / 실패시
     * DeferredResult.setResult, DeferredResult.setErrorREsult를 호출
     */
    @PostMapping
    public ListenableFuture<String> sumbitFormListenable(@RequestParam("courtName") String courtName, Model model) {

        //AsyncListenableTaskExecutor를 활용한다
        return asyncTaskExecutor.submitListenable(() -> {
            List<Reservation> reservations = java.util.Collections.emptyList();

            if (courtName != null) {
                Delayer.randomDelay();
                reservations = reservationService.query(courtName);
            }

            model.addAttribute("reservations", reservations);

            return "reservationQuery";
        });
    }
    
}