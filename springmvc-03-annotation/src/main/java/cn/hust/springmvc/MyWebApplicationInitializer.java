package cn.hust.springmvc;

import cn.hust.springmvc.config.SpringConfig;
import cn.hust.springmvc.config.WebAppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 自定义web应用初始化器，完成容器的创建和前端控制器的创建
 */
public class MyWebApplicationInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 返回 Spring 的配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    /**
     * 返回 SpringMVC 的配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    /**
     * 指定拦截的路径映射
     * / 拦截所有（包括静态资源），不包括 jsp
     * /* 拦截所有，包括jsp
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
