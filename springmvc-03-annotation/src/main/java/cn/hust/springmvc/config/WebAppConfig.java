package cn.hust.springmvc.config;

import cn.hust.springmvc.interceptor.MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc // 定制 SpringMVC 的第一步，开启 SpringMvc 支持；第二步，配置类继承 WebMvcConfigurerAdapter
@Configuration
@ComponentScan(basePackages = {"cn.hust.springmvc"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})},
        // 只导入的情况要取消默认导入过滤器
        useDefaultFilters = false) // 只导入 Controller
// 注解标注的
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // sp("/WEB-INF/", ".jsp"); 默认按照 /WEB-INF/xxx.jsp 来拼路径
//       registry.jsp("/WEB-INF/views", ".jsp");
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // 配置静态资源访问，默认是 SpringMVC 会拦静态资源，配置使得 tomcat 处理静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        super.configureDefaultServletHandling(configurer);
        configurer.enable();// 开启静态资源访问支持
    }

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new MyInterceptor());
        registration.addPathPatterns("/**");//拦截 context 及子路径
    }
}
