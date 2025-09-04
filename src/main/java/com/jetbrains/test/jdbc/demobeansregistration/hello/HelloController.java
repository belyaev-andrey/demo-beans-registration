package com.jetbrains.test.jdbc.demobeansregistration.hello;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class HelloController {

    private final MyService myService;
    private final MyService myServicePrototype;
    private final MyService myServicePlain;

    HelloController(@Qualifier("myServiceParamHello") MyService myService,
                    @Qualifier("myServicePrototype") MyService myServicePrototype,
                    MyService myServicePlain) {
        this.myService = myService;
        this.myServicePrototype = myServicePrototype;
        this.myServicePlain = myServicePlain;
    }

    @GetMapping("hello")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok(myService.sayHello());
    }

    @GetMapping("helloProto")
    public ResponseEntity<String> getHelloProto() {
        return ResponseEntity.ok(myServicePrototype.sayHello());
    }

    @GetMapping("helloPlain")
    public ResponseEntity<String> getHelloPlain() {
        return ResponseEntity.ok(myServicePlain.sayHello());
    }

}
