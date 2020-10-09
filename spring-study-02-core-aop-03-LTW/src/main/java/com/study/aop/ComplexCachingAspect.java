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
     * call은 스프링 AOP에서 지원하지 않으므로 이것을 사용하기 위해서는 
     * AspecJ 프레임워크를 직접 사용해야 하며 사용법은 META-INF에 aop.xml 파일을 설정하고,
     * 위빙해 넣을 대상 클래스와 애스펙트 클래스를 설정한다.
     * 
     * call 안에 Complex(int,int) 생성자를 호출하는 조인포인트를 실행하고,
     * 캐시된 Complex가 있으면 캐시된 걸 반환하고 없으면 생성자를 통해 새로 생성한 Complex를 반환한다. 
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
