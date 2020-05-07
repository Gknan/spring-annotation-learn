package cn.hust.spring.config;

import cn.hust.spring.bean.Person;
import org.springframework.context.annotation.*;

@PropertySource(value = "classpath:person.properties") // 指定配置文件的位置，加载到环境变量中
@Configuration
public class PropertyValueConfig {

    @Bean
    public Person person() {
        return new Person();
    }
}
