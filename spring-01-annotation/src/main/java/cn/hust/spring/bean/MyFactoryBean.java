package cn.hust.spring.bean;

import cn.hust.spring.bean.Cat;
import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义工厂 bean
 */
public class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Cat();
    }

    @Override
    public Class<?> getObjectType() {
        return Cat.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
