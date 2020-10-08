package com.apress.springrecipes.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.sequence.SequenceGenerator;
import com.apress.springrecipes.sequence.SequenceService;
import com.apress.springrecipes.sequence.config.PrefixConfiguration;
import com.apress.springrecipes.sequence.config.SequenceGeneratorConfiguration;

public class GetMultiConfigurationMain {

    public static void main(String[] args) {
        /**
         * 다음과 같이 구성 클래스를 함께 설정하여
         * 같은 스코프 안에 빈을 설정가능함
         * 여기서는 SequenceGeneratorConfiguration이
         * PrefixConfiguration안에 임포트 되어 있으므로
         * PrefixConfiguration만 사용해도 가능
         */
        ApplicationContext context 
        = new AnnotationConfigApplicationContext(
                PrefixConfiguration.class, 
                SequenceGeneratorConfiguration.class);
        
        SequenceService sequenceService = context.getBean(SequenceService.class);
        System.out.println(sequenceService.generate("IT"));
        System.out.println(sequenceService.generate("IT"));

    }

}
