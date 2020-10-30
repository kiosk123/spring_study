package com.study.mvc;

import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

/**
 * 웹플럭스 기동을 위해 AbstractReactiveWebInitializer 상속받아 구현
 *
 */
public class WebFluxInitializer extends AbstractReactiveWebInitializer {

    @Override
    protected Class<?>[] getConfigClasses() {
        return new Class<?>[] {WebFluxConfiguration.class};
    }
}
