package cn.hust.spring.component;

import cn.hust.spring.bean.Yellow;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Import 导入手动注册bean 到容器中
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        System.out.println(importingClassMetadata);
        // 判断是否有 person 这个 bean
        boolean person = registry.containsBeanDefinition("gates");
        boolean color = registry.containsBeanDefinition("cn.hust.spring.bean.Color");

        if (person && color) {
            // 注册一个新的 Yellow Bean 可以指定 id
            // 声明 Bean 定义 如 bean 的 scope lazy 。。。
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Yellow.class);
            registry.registerBeanDefinition("yellow", rootBeanDefinition);
        }


    }
}
