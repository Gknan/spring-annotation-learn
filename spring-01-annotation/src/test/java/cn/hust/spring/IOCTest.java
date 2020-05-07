package cn.hust.spring;

import cn.hust.spring.bean.Color;
import cn.hust.spring.bean.Person;
import cn.hust.spring.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

    public void printBeans(AnnotationConfigApplicationContext applicationContext) {

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));
        for(String name: beanDefinitionNames) System.out.println(name);

    }

    @Test
    public void test01 () {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));
        for(String name: beanDefinitionNames) System.out.println(name);
    }

    @Test
    public void test02 () {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        Person person = (Person) applicationContext.getBean("person");

        Person person2 = (Person) applicationContext.getBean("person");

        System.out.println(person == person2);
    }

    @Test
    public void test03 () {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

//        applicationContext.getBean()

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String name: beanDefinitionNames) System.out.println(name);
    }

    @Test
    public void testImport() {
        printBeans(applicationContext);
        Color bean = (Color) applicationContext.getBean("cn.hust.spring.bean.Color");

        // Color{name='null'}
        System.out.println(bean);
    }

    @Test
    public void testFactoryBean() {
        printBeans(applicationContext);

        Object bean1 = applicationContext.getBean("myFactoryBean");
        Object bean2 = applicationContext.getBean("myFactoryBean");

        System.out.println(bean1 == bean2);

        // 默认获取的是创建的 bean
        // bean id 上加 & 获取的是 FactoryBean
//        Object myFactoryBean = applicationContext.getBean("&myFactoryBean");
//        System.out.println(myFactoryBean.getClass());
    }
}
