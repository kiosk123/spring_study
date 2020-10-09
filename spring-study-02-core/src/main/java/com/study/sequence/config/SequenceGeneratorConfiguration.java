package com.study.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import com.study.sequence.DataPrefixGenerator;
import com.study.sequence.SequenceGenerator;
/**
 * 
 * @ComponentScan IoC 초기화를 위한 애너테이션을 스캐닝 정보를 설정하는 애너테이션
 * @Configuration 빈 설정 정보가 있는 구성 클래스
 * @DependsOn 
 * 빈 생성 순서 보장 
 */
@ComponentScan(
        basePackages = "com.study.sequence",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = {
                                "com.study.sequence.*Dao",
                                "com.study.sequence.*Service"
                                })
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        value = Controller.class)
        }
)
@Configuration
public class SequenceGeneratorConfiguration {
    
    /**
     * sequenceGenerator 메소드명으로 Bean 생성
     * 따로 이름 명시 하고 시은 경우 @Bean(name="Bean이름")으로 설정
     * @DependsOn("dataPrefixGenerator") dataPrefixGenerator빈 먼저 생성 후 주입
     */
    @Bean
    @DependsOn("dataPrefixGenerator")
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator seqgen = new SequenceGenerator();
        //dataPrefixGenerator빈 주입
        seqgen.setPrefixGenerator(dataPrefixGenerator()); 
        seqgen.setSuffix("A");
        seqgen.setInitial(100000);
        return seqgen;
    }
    
  
    @Bean
    public DataPrefixGenerator dataPrefixGenerator() {
        DataPrefixGenerator dpg = new DataPrefixGenerator();
        dpg.setPattern("yyyyMMdd");
        return dpg;
    }
}
