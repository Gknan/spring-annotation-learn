package cn.hust.spring;

import cn.hust.spring.bean.Tiger;
import cn.hust.spring.ext.ExtConfig;
import cn.hust.spring.ext.MyLifecycleProcessor;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class IOCTest_Ext {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ExtConfig.class);

        MyLifecycleProcessor lifecycleProcessor = applicationContext.getBean(MyLifecycleProcessor.class);
        lifecycleProcessor.start();
        lifecycleProcessor.onRefresh();
        lifecycleProcessor.onClose();

        // 发布一个事件
//        applicationContext.publishEvent(new Tiger());
//        printBeans(applicationContext);
//        BeanFactoryPostProcessor bean = applicationContext.getBean(BeanFactoryPostProcessor.class);
//        MyBeanFactoryPostProcessor bean = applicationContext.getBean(MyBeanFactoryPostProcessor.class);
        applicationContext.close();
    }

}
