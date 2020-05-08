package cn.hust.spring.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * z在 bean 工厂中完成标准初始化，即所有的 bean 定义添加到 bean 工厂，但是没有 bean 被实例化之前执行
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        int count = beanFactory.getBeanDefinitionCount();
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        System.out.println("执行时机...MyBeanFactoryPostProcessor... postProcessBeanFactory...");
        System.out.println("当前的bean工厂的bean定义个数为：" + count + "，分别是：" + Arrays.toString(beanDefinitionNames));
    }
}
