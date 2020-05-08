package cn.hust.spring;

import cn.hust.spring.config.TansactionConfig;
import cn.hust.spring.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class IOCTest_Tx {

    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(TansactionConfig.class);

    @Test
    public void test01() {
//        printBeans(applicationContext);
        UserService userService = applicationContext.getBean(UserService.class);
        String user = UUID.randomUUID().toString();
        userService.insert(user, 12);

    }



    public void printBeans(AnnotationConfigApplicationContext applicationContext) {

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));
        for(String name: beanDefinitionNames) System.out.println(name);
    }

}
