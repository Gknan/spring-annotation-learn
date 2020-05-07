package cn.hust.spring.config;

import cn.hust.spring.bean.Car;
import cn.hust.spring.bean.Panda;
import cn.hust.spring.bean.Tiger;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@ComponentScan(value = "cn.hust.spring.bean", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Panda.class, Tiger.class})
})
@ComponentScan(value = "cn.hust.spring.component")
@Configuration
public class BeanLifeCricleConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope(scopeName = "prototype")
//    @Bean
    public Car car() {
        return new Car();
    }
}
