package com.jetbrains.test.jdbc.demobeansregistration.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Test
    void testHello() {
        mockMvcTester.get().uri("/api/hello").exchange().assertThat().hasStatus(HttpStatus.OK);
    }

    @Test
    void testHelloPrototype() {
        mockMvcTester.get().uri("/api/helloProto").exchange().assertThat().hasStatus(HttpStatus.OK);
    }

    @Test
    void testHelloPlain() {
        mockMvcTester.get().uri("/api/helloPlain").exchange().assertThat().hasStatus(HttpStatus.OK);
    }

}
