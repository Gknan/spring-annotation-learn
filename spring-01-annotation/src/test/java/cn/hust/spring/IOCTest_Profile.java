package cn.hust.spring;

import cn.hust.spring.bean.Boss;
import cn.hust.spring.bean.Car;
import cn.hust.spring.bean.Lawyer;
import cn.hust.spring.config.AutowireConfig;
import cn.hust.spring.config.ProfileConfig;
import cn.hust.spring.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Profile {

    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(ProfileConfig.class);

    @Test
    public void test01() {
        printBeans(applicationContext);
    }

    @Test
    public void test02() {

        // 编码的方式制定 Profile
        // 1 创建 无参数 ApplicationContext
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 2 设置要激活的环境
        context.getEnvironment().setActiveProfiles("dev");

        // 3 注册配置类
         context.register(ProfileConfig.class);

         // 4 启动刷新容器
         context.refresh();
        printBeans(context);
    }



    public void printBeans(AnnotationConfigApplicationContext applicationContext) {

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));
        for(String name: beanDefinitionNames) System.out.println(name);
    }

}
