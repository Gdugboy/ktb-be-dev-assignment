package org.jerry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // 🔹 스프링 부트 애플리케이션이라는 것을 선언
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); // 🔹 스프링 부트 애플리케이션 실행
    }
}