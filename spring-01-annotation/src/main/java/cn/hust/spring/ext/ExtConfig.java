package cn.hust.spring.ext;

import cn.hust.spring.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("cn.hust.spring.ext")
public class ExtConfig {

    @Bean
    public Car car() {
        return new Car();
    }
}
