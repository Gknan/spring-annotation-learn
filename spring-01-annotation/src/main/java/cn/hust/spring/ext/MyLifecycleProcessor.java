package cn.hust.spring.ext;

import org.springframework.context.LifecycleProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyLifecycleProcessor implements LifecycleProcessor {
    @Override
    public void onRefresh() {
        System.out.println("================MyLifecycleProcessor...onRefresh...");
    }

    @Override
    public void onClose() {
        System.out.println("=================MyLifecycleProcessor...onClose...");
    }

    // 开启实现了 Lifecycle 的组件
    @Override
    public void start() {
        System.out.println("MyLifecycleProcessor...start...");
    }

    // 后两个方法被显示调用了，前两个并没有
    // 关闭组件
    @Override
    public void stop() {
        System.out.println("MyLifecycleProcessor...stop...");
    }

    // 检查组件是否运行
    @Override
    public boolean isRunning() {
        System.out.println("MyLifecycleProcessor...isRunning...");
        return true;
    }
}
