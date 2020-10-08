package com.apress.springrecipes.sequence;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
/**
 * 사용 목적에 맞게 애노테이션 활용
 * @Repository 영속화(붙이면 발생한 예외를 DataAccessException런타임 예외로 랩핑해서 던짐 디버깅시 유리)
 * @Service 서비스
 * @Controller 표현
 */
@Component("sequenceDao")
public class SequeceDaoImpl implements SequenceDao {

    private final Map<String, Sequence> sequences = new HashMap<>();
    private final Map<String, AtomicInteger> values = new HashMap<>();
    
    public SequeceDaoImpl() {
        sequences.put("IT", new Sequence("IT", "30", "A"));
        values.put("IT", new AtomicInteger(10000));
    }

    @Override
    public Sequence getSequence(String sequenceId) {
        return sequences.get(sequenceId);
    }

    @Override
    public int getNextValue(String sequenceId) {
        AtomicInteger value = values.get(sequenceId);
        return value.incrementAndGet();
    }

}
