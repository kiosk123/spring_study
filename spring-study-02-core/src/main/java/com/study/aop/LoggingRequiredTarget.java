package com.study.aop;

import org.springframework.stereotype.Component;

import com.study.aop.anno.LoggingRequired;

/**
 * @LoggingRequired 커스텀 AOP 애너테이션의 포인트 컷 타겟이 되는 클래스
 */
@LoggingRequired
@Component
public class LoggingRequiredTarget {
    
    public void call() {
        System.out.println("called target");
    }
}
