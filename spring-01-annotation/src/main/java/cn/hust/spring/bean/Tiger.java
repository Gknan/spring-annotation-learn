package cn.hust.spring.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Tiger {

    public Tiger() {
        System.out.println("Tiger construct...");
    }

    @PostConstruct
    public void init() {
        System.out.println("Tiger postConstruct....");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Tiger PreDestroy....");
    }
}
