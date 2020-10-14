package com.study.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 인터셉터는 HandlerInterceptor 를 이용하여 구현하거나 
 * HandlerInterceptorAdapter를 상속받아 구현한다.
 */
public class MeasurementInterceptor implements HandlerInterceptor {
    
    private static final Logger log = LoggerFactory.getLogger(MeasurementInterceptor.class);
    
    /**
     * 핸들러가 요청을 처리하기 직전 호출
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("intercepted in prehandle");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true; //true 일경우에만 요청처리 계속 진행하며 그 외에는 이메서드선에서 요청처리가 끝나다고 보고 유저에게 응답객체를 반환함
    }

    /**
     * 핸들러가 요청을 처리한 후 호출
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        log.info("intercepted posthandle");
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        
        long endTime = System.currentTimeMillis();
        modelAndView.addObject("handlingTime", endTime - startTime);
    }

    /**
     * 요청 처리가 완전히 완료된후(뷰까지 렌더링 된 이후) 호출
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("intercepted after completion");
    }
}
