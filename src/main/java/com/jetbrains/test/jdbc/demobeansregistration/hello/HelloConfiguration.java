package com.jetbrains.test.jdbc.demobeansregistration.hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(HelloServiceRegistrar.class)
class HelloConfiguration {

}
