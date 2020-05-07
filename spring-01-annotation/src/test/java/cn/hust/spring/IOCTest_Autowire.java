package cn.hust.spring;

import cn.hust.spring.bean.Boss;
import cn.hust.spring.bean.Car;
import cn.hust.spring.bean.Lawyer;
import cn.hust.spring.bean.Person;
import cn.hust.spring.config.AutowireConfig;
import cn.hust.spring.config.PropertyValueConfig;
import cn.hust.spring.dao.BookDao;
import cn.hust.spring.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IOCTest_Autowire {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowireConfig.class);

    @Test
    public void test01() {
        printBeans(applicationContext);

        BookService bean = applicationContext.getBean(BookService.class);
        System.out.println(bean);
//        applicationContext.getBean("bookDao");
//        System.out.println(bean1);
//        BookDao bean1 = (BookDao)
    }

    @Test
    public void test02() {
        printBeans(applicationContext);

        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);

        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);


//        applicationContext.getBean("bookDao");
//        System.out.println(bean1);
//        BookDao bean1 = (BookDao)
    }


    @Test
    public void test03() {
        printBeans(applicationContext);

        Lawyer lawyer = applicationContext.getBean(Lawyer.class);
        System.out.println(lawyer);

        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);

        System.out.println(applicationContext);


//        applicationContext.getBean("bookDao");
//        System.out.println(bean1);
//        BookDao bean1 = (BookDao)
    }



    public void printBeans(AnnotationConfigApplicationContext applicationContext) {

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));
        for(String name: beanDefinitionNames) System.out.println(name);
    }

}
