package com.study.aop;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class ComplexCachingAspect {

    private final Map<String, Complex> cache = new ConcurrentHashMap<>();

    /**
     * 스프링 로드타임 위빙을 사용할 경우
     * IoC 컨테이너에서 선언한 빈이 Complex.new(int, int)를 호출할 경우에만
     * 스피링 에이전트가, 어드바이스를 적용한다.
     * 그렇기 때문에 AspectJ 프레임워크를 사용한 것과 결과가 다를 수 있다.
     */
    @Around("call(public Complex.new(int, int)) && args(a,b)")
    public Object cacheAround(ProceedingJoinPoint joinPoint, int a, int b)
            throws Throwable {

        String key = a + "," + b;
        Complex complex = cache.get(key);

        if (complex == null) {
            System.out.println("Cache MISS for (" + key + ")");
            complex = (Complex) joinPoint.proceed();
            cache.put(key, complex);
        } else {
            System.out.println("Cache HIT for (" + key + ")");
        }

        return complex;
    }
}
