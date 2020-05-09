package cn.hust.springmvc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自定义 Listener 组件 监听应用启动
 */
public class UserLisener implements ServletContextListener {

    // Context 初始化时执行
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("UserLisener...contextInitialized...");
    }

    // Context 销毁时执行
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("UserLisener...contextDestroyed...");
    }
}
