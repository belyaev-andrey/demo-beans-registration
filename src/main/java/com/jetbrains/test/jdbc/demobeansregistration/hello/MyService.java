package com.jetbrains.test.jdbc.demobeansregistration.hello;

interface MyService {

    String sayHello();

    static Class<? extends MyService> getDefaultImplementationClass() {
        return MyServicePlainHello.class;
    }

    class MyServicePlainHello implements MyService {
        @Override
        public String sayHello() {
            return "Hello!!!";
        }

    }
}
