package cn.hust.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

// 注入上下文

/**
 * 通过实现 ApplicationContextAware 的方式注入上下文
 */
public class Mouse implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Mouse() {
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
