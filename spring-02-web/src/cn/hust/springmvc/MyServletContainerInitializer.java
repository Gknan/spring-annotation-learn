package cn.hust.springmvc;

import cn.hust.springmvc.filter.UserFilter;
import cn.hust.springmvc.listener.UserLisener;
import cn.hust.springmvc.service.HelloService;
import cn.hust.springmvc.servlet.UserServlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * ServletContainerInitializer 在容器启动时创建，需要再 META-INF/services/javax.servlet.ServletContainerInitializer 中指定实现类的全路径名
 */
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {

    /**
     * 在任何 Servlet 监听器作用前，应用启动后生效
     * @param set @HandlesTypes 标准的类或接口的子类或子接口的类对象
     * @param servletContext Web 的 ServletContext 对象
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("通过 @HandlesTypes 注解传入的类对象有：");
        for (Class<?> aClass : set) {
            System.out.println(aClass);
        }

        // 使用 ServletContext 注册三大组件
        // 注册 Servlet
        ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet", new UserServlet());
        // 为 Servlet 添加映射路径
        userServlet.addMapping("/user");

        // 注册 Listener
        servletContext.addListener(UserLisener.class);

        // 注册 Filter
        FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter", UserFilter.class);
        // 为 Filter 添加映射
        userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/user");
    }
}
