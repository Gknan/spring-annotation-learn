package cn.hust.spring;

import cn.hust.spring.component.MathCalculator;
import cn.hust.spring.config.AopConfig;
import cn.hust.spring.config.ProfileConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Aop {

    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AopConfig.class);

    @Test
    public void test01() {
//        printBeans(applicationContext);
        MathCalculator cal = applicationContext.getBean(MathCalculator.class);
        cal.div(1, 2);

    }



    public void printBeans(AnnotationConfigApplicationContext applicationContext) {

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));
        for(String name: beanDefinitionNames) System.out.println(name);
    }

}
