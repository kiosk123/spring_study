package com.apress.springrecipes.sequence.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Import 
 * 다른 구성 클래스를 현재 구성 클래스의 스코프로 가져온다
 */
@Configuration
@Import(SequenceGeneratorConfiguration.class)
public class PrefixConfiguration {
    
}
