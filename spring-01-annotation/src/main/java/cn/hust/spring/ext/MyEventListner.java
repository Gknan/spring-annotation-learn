package cn.hust.spring.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListner {

    // 标注这个方法可以监听到 ApplicationEvent 事件
    @EventListener(classes = ApplicationEvent.class)
    public void listen(ApplicationEvent event) {
        System.out.println("MyEventListner 监听到的事件是：" + event);
    }

}
