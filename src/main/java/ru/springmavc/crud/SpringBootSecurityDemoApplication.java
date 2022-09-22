package ru.springmavc.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
    }

    // нужен для избежания ошибки Whitelabel Error Page
    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }


}
