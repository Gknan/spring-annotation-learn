package cn.hust.spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Panda implements InitializingBean, DisposableBean {

    public Panda() {
        System.out.println("Panda construct ....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Panda destroy....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Panda afterPropertiesSet....");

    }
}
