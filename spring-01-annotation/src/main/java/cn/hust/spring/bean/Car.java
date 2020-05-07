package cn.hust.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {

    public Car() {
        System.out.println("Car construct...");
    }

    public void init() {
        System.out.println("Car instance init...");
    }

    public void destroy() {
        System.out.println("Car instance destroy...");
    }
}
