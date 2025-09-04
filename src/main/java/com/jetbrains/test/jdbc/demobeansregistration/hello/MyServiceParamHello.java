package com.jetbrains.test.jdbc.demobeansregistration.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MyServiceParamHello implements MyService {

    private static final Logger log = LoggerFactory.getLogger(MyServiceParamHello.class);
    private String tag = "default";

    public MyServiceParamHello() {
        log.info("MyService created with tag: {}", tag);
    }

    public MyServiceParamHello(String tag) {
        this.tag = tag;
    }

    @Override
    public String sayHello() {
        return "Hello %s!".formatted(tag);
    }
}
