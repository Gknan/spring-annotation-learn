package cn.hust.spring;

import cn.hust.spring.bean.Person;
import cn.hust.spring.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//
//        Person person = (Person) context.getBean("person");
//
//        System.out.println(person);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//        Person person = (Person) context.getBean("person");
//        System.out.println(person);

        // person
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        for (String beanName: beanNamesForType) System.out.println(beanName);

    }
}
