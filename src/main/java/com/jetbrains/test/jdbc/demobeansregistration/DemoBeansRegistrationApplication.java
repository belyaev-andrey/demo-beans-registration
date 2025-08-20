package com.jetbrains.test.jdbc.demobeansregistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Import(ServiceRegistrar.class)
public class DemoBeansRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBeansRegistrationApplication.class, args);
    }

}

class MyService {

	private static final Logger log = LoggerFactory.getLogger(MyService.class);
	private String tag = "default";

    public MyService() {
		log.info("MyService created with tag: {}", tag);
    }

    public MyService(String tag) {
        this.tag = tag;
    }

    public String sayHello() {
        return "Hello %s!".formatted(tag);
    }
}

class ServiceRegistrar implements BeanRegistrar {
    @Override
    public void register(BeanRegistry registry, Environment env) {
        registry.registerBean("myServicePrototype", MyService.class,
                (spec) -> {
                    spec.prototype();
					spec.supplier((s) -> new MyService("prototype"));
                });
        registry.registerBean("myService", MyService.class);
    }
}

@RestController
@RequestMapping("/api")
class HelloController {

    private final MyService myService;
    private final MyService myServicePrototype;

    HelloController(MyService myService,
                    @Qualifier("myServicePrototype") MyService myServicePrototype) {
        this.myService = myService;
        this.myServicePrototype = myServicePrototype;
    }

    @GetMapping("hello")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok(myService.sayHello());
    }

    @GetMapping("helloProto")
    public ResponseEntity<String> getHelloProto() {
        return ResponseEntity.ok(myServicePrototype.sayHello());
    }

}