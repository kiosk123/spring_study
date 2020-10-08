package com.apress.springrecipes.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @Autowired 해당 타입의 빈 자동 주입
 * private SequenceDao sequenceDao
 * 
 * @Autowired 해당 타입의 여러개 빈들 자동 주입
 * private SequenceDao[] sequenceDaos;
 *
 * @Autowired 해당 타입의 여러개 빈들 자동 주입
 * private List<SequenceDao> sequenceDaos;
 * 
 * 다음과 같이 함수 및 생성자에도 적용 가능, 인수의 이름과 개수에 상관없이 적용가능
 * 스프링은 인수의 타입에 맞춰서 빈을 주입
 * @Autowired
 * public void setSequenceDao(SequenceDao sequenceDao) {
 *     this.sequenceDao = sequenceDao;
 * }
 * 
 * 주입 할 빈을 못 찾으면 예외를 던지지만 다음과 같이 required에 false 옵션을 주면 지나침
 * @Autowired(required=false)
 * 
 * @Primary
 * 특정 타입을 호환하는 빈이 여러 개일 경우 주입 대상 프로터티가 (리스트, 배열, 맵등..)
 * 그룹형이 아닌 경우 주입이 안됨, 이럴 경우 @Primary를 활용하여 최우선순위로 설정
 * 
 * @Qualifier
 * 특정 이름을 가진 Bean을 주입 받는다 @Autowired와 같인 사용
 * 메서드 인수에도 사용가능
 * public void setSequenceDao(@Qualifier("sequenceDao") SequenceDao sequenceDao) {
 *    this.sequenceDao = sequenceDao;
 * }
 */

@Primary
@Component
public class SequenceService {
    
    @Autowired 
//    @Qualifier("sequenceDao")
    private SequenceDao sequenceDao;

    
    public void setSequenceDao(SequenceDao sequenceDao) {
        this.sequenceDao = sequenceDao;
    }
    
    public String generate(String sequenceId) {
        Sequence sequence = sequenceDao.getSequence(sequenceId);
        int value = sequenceDao.getNextValue(sequenceId);
        StringBuilder builder = new StringBuilder();
        builder.append(sequence.getPrefix()).append(value).append(sequence.getPrefix());
        return builder.toString();
    }

}
