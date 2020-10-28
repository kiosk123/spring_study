package com.study.mvc.web;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AsyncMeasurementInterceptor implements AsyncHandlerInterceptor {

    public static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getAttribute(START_TIME) == null) {
            request.setAttribute(START_TIME, System.currentTimeMillis());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long startTime = (Long) request.getAttribute(START_TIME);
        request.removeAttribute(START_TIME);
        long endTime = System.currentTimeMillis();
        System.out.println("Response-Processing-Time: " + (endTime - startTime) + "ms.");
        System.out.println("Response-Processing-Thread: " + Thread.currentThread().getName());
    }

    /**
     * 비동기 처리를 시작하는 시점에 postHandle이나  afterCompletion 대신 호출된다. 비동기 처리가 다 끝나면 정상 흐름으로 복귀한다.
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long startTime = (Long) request.getAttribute(START_TIME);
        request.setAttribute(START_TIME, System.currentTimeMillis());
        long endTime = System.currentTimeMillis();

        System.out.println("Request-Processing-Time: " + (endTime - startTime) + "ms.");
        System.out.println("Request-Processing-Thread: " + Thread.currentThread().getName());
    }
}
