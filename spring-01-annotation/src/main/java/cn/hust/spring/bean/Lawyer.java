package cn.hust.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class Lawyer {

    Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Lawyer{" +
                "car=" + car +
                '}';
    }
}
