package com.jetbrains.test.jdbc.demobeansregistration.hello;

import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;

class HelloServiceRegistrar implements BeanRegistrar {
    @Override
    public void register(BeanRegistry registry, Environment env) {

        registry.registerBean(MyService.getDefaultImplementationClass(), (BeanRegistry.Spec::primary) );

        registry.registerBean("myServicePrototype", MyServiceParamHello.class,
                (spec) -> {
                    spec.prototype();
                    spec.supplier((s) -> new MyServiceParamHello("prototype"));
                });
        registry.registerBean("myServiceParamHello", MyServiceParamHello.class);
    }
}
