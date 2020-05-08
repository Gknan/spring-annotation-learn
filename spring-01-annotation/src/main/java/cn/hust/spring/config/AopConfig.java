package cn.hust.spring.config;

import cn.hust.spring.component.LogAspect;
import cn.hust.spring.component.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy// 开启注解扫描切面代码
@Configuration
public class AopConfig {

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

}
