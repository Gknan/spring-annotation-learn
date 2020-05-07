package cn.hust.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Boss {

    private Car car;

//    @Autowired // 只要一个有参构造时，有参构造方法上的 Autowire 可省略
    public Boss( Car car) {
        this.car = car;
    }

    //    @Autowired
    // 在set方法的参数中设置 Autowire 返回空，猜测是 Car 还没有创建？
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
