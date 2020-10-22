package com.study.mvc.web.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.study.mvc.domain.Member;
import com.study.mvc.domain.Members;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.study.mvc")
public class CourtRestConfiguration {

    /**
     *  RestMemberController의 getRestMembers의 membertemplate 논리뷰를 처리  
     */
    @Bean
    public View membertemplate() {
        //MarshallingView 마샬러를 사용해 응답을 렌더링하는 범용 클래스다
        return new MarshallingView(jaxb2Marshaller());
    }

    @Bean
    public Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        /**
         * setContextPath 또는 setClassesToBeBound로 마샬링 대상 지정
         */
        marshaller.setClassesToBeBound(Members.class, Member.class);
        marshaller.setMarshallerProperties(Collections.singletonMap(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE));
        return marshaller;
    }

    /**
     * 논리 뷰이름으로  뷰를 찾게하기 위한 뷰 리졸버
     */
    @Bean
    public ViewResolver viewResolver() {
        return new BeanNameViewResolver();
    }

}
