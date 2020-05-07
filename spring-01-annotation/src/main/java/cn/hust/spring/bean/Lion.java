package cn.hust.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * ApplicationContextAware 帮助注入上下文
 * BeanNameAware 注入当前 Bean 的名字
 * EmbeddedValueResolverAware 解析 String
 */
@Component
public class Lion implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println(applicationContext);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("当前 Bean 的名字为： " + name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String s = resolver.resolveStringValue("This is ${os.name}, #{23*3}");
        System.out.println("解析的结果： " + s);
    }
}
