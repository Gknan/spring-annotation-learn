package cn.hust.spring.component;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition {

    /**
     *
     * @param context 标准注解位置的上下文
     * @param metadata 注解类型信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取 当前上下文的 beanactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 获取加载注册的 Bean 的类加载器
        ClassLoader classLoader = context.getClassLoader();

        // 获取 bean 定义注册情况
        BeanDefinitionRegistry registry = context.getRegistry();

        // 获取系统运行的环境信息
        Environment environment = context.getEnvironment();

        // 可以写更多的判断条件，
        // 使用 BeanDefinitionRegistry 可以判断 bean 注册信息，也可以注册bean
//        registry.containsBeanDefinition("");

//        System.out.println(environment.getProperty("os.name"));

        // 当前上下文的 beanFactory
//        System.out.println("WindowsCondition beanfacotry:  " +  beanFactory);

        // 若是 Windows 系统，满足条件
        if (environment.getProperty("os.name").contains("Windows")) return true;

        return false;
    }
}
