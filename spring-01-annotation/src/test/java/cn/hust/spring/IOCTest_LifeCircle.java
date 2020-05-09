package cn.hust.spring;

import cn.hust.spring.bean.Car;
import cn.hust.spring.bean.Panda;
import cn.hust.spring.bean.Tiger;
import cn.hust.spring.config.BeanLifeCricleConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_LifeCircle {

//    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifeCricleConfig.class);

    @Test
    public void testInitAndDestroy() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifeCricleConfig.class);

        System.out.println("容器创建完成...");
//        Car bean = applicationContext.getBean(Car.class);
//        Car bean2 = applicationContext.getBean(Car.class);

//        System.out.println(bean == bean2);
        applicationContext.destroy();
    }

//    @Test
//    public void testInitAndDestroy2() {
//        System.out.println("容器创建完成...");
//
//        Panda panda = applicationContext.getBean(Panda.class);
//        applicationContext.close();
//    }
//
//    @Test
//    public void testInitAndDestroy3() {
//        System.out.println("容器创建完成...");
//
//        Tiger tiger = applicationContext.getBean(Tiger.class);
//        applicationContext.close();
//    }
}
