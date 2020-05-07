package cn.hust.spring;

import cn.hust.spring.bean.Car;
import cn.hust.spring.bean.Person;
import cn.hust.spring.config.BeanLifeCricleConfig;
import cn.hust.spring.config.PropertyValueConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IOCTest_PropertyValue {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PropertyValueConfig.class);

    @Test
    public void test01() {
        printBeans(applicationContext);

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName"); // @PropertySource 将配置文件加载到环境变量中，这里可以拿到对应的属性值
        System.out.println(property);
    }


    public void printBeans(AnnotationConfigApplicationContext applicationContext) {

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));
        for(String name: beanDefinitionNames) System.out.println(name);
    }

}
