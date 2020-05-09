### 一、介绍

Servlet 3.0 提供了利用注解方式注解三大组件

文档 8.1 介绍注解和插件

tomcat 7 以上支持

### 二、例子

创建 动态 web 工程 

![](pics\08-动态web工程项目结构.png)

项目结构中需要对 Paths 和 Dependencies 做配置

![](pics\09-动态web工程的paths配置.png)

![](pics\10-动态web工程的dependencies配置.png)

![](pics\11-IDEA web 项目整合 Tomcat修改后自动部署.png)

[IDEA参照](https://www.cnblogs.com/wfhking/p/9395774.html)

jsp 写请求

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <a href="hello">HelloSpringMVC</a>
  </body>
</html>
```

写 HelloServelt ，实现 HttpServlet

```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(">>>>Hello Spring MVC>>>>");
    }
}
```

通过 @WebServlet 注册

三、SpringMVC

SpirngMVC 通过一套 MVC 注解，让 POJO 成为处理请求的控制器

支持 REST 风格的 URL 请求

采用了松散耦合可插拔组件结构，扩展更灵活

![](pics\06-MVC介绍.png)

Spring 对 MVC 的实现

![](pics\07-Spring对MVC的实现.png)

前端控制器



### 三、ServletContainerInitializer  机制

#### 1、Shared lib runtime plugbility

1）Servlet 容器启动，会扫描当前应用里面每一个 jar 包的 ServletContainerInitializer 的实现

2）提供 ServletContainerInitializer 的实现类，必须绑定在 META-INF/services/javax.servlet.ServletContainerInitializer，文件内容就是 ServletContainerInitializer 实现类的全类名

总结：容器再启动应用的时候，会扫描当前应用每一个 jar 包里面 META-INF/services/javax.servlet.ServletContainerInitializer 指定的实现类，启动并运行这个实现类的方法。参数处可以传入感兴趣的类型（可以利用反射创建对象等）

onStartup() 应用启动时，运行 onStartup 方法

参数：ServletContext Set<Class<?>> 感兴趣的类型的所有的后代类型

@HandlesType() 注解容器启动的时候，会将 HandleTypes 指定的类型下面的子类（实现类，子接口等）传递过来

注意 META-INF/services 实在项目路径下，不是 web 目录下

1）使用 ServletContext 注册 web 组件（Servlet、Filter、Lisenter）

2）实例组件 UserServlet（继承 HttpServlet） UserFilter(实现 Filter 接口) UserListener（实现 ServletContextListener）监听启动的启动停止；添加三大组件并映射

UserServlet

```java
/**
 * 创建 UserServlet ，在 MyServletContainerInitializer 启动时注册进容器
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Tomcat....");
    }
}
```

UserFilter

```java
/**
 * 自定义 Filter
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("UserFilter...init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("UserFilter...doFilter...");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("UserFilter...destroy...");
    }
}
```

UserLisener

```java
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
```

MyServletContainerInitializer

```java
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
```

3）在项目启动的时候给当前项目添加三大组件，必须在项目启动的时候添加

​	1、ServletContainerInitializer 的 onStart 方法处

​	2、ServletContextListener#contextInitialized 处



#### 2、Servlet3 整合 SpringMVC 

spring-web包下 的 META-INF/services/javax.serlet.SerletContainerInitializer 文件中指明了容器启动的引导到 

Spring 的应用一启动就会加载 WebApplilcaitonInitaillizer接口下的所有组件，并且为这些组件（不是接口和抽象类）创建实例 SpringServletContainerInitializer

```java
@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {
   @Override
   public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext)
         throws ServletException {

      List<WebApplicationInitializer> initializers = new LinkedList<WebApplicationInitializer>();

      if (webAppInitializerClasses != null) {
          // 遍历 WebApplicationInitializer 的子类，创建实例
         for (Class<?> waiClass : webAppInitializerClasses) {
            // Be defensive: Some servlet containers provide us with invalid classes,
            // no matter what @HandlesTypes says...
            if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
                  WebApplicationInitializer.class.isAssignableFrom(waiClass)) {
               try {
                  initializers.add((WebApplicationInitializer) waiClass.newInstance());
               }
               catch (Throwable ex) {
                  throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
               }
            }
         }
      }

      if (initializers.isEmpty()) {
         servletContext.log("No Spring WebApplicationInitializer types detected on classpath");
         return;
      }

      servletContext.log(initializers.size() + " Spring WebApplicationInitializers detected on classpath");
      AnnotationAwareOrderComparator.sort(initializers);
      for (WebApplicationInitializer initializer : initializers) {
          // 执行每个 WebApplicationInitializer 的 onStartup(servletContext) 方法
         initializer.onStartup(servletContext);
      }
   }
```

WebApplicationInitializer 的子类：

AbstractContextLoaderInitializer、AbstractDispatcherServletInitializer、AbstractAnnotationConfigDispatcherServletInitializer

​	1、AbstractContextLoaderInitializer 创建根容器

```java
protected void registerContextLoaderListener(ServletContext servletContext) {
   WebApplicationContext rootAppContext = createRootApplicationContext();
   if (rootAppContext != null) {
      ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
      listener.setContextInitializers(getRootApplicationContextInitializers());
      servletContext.addListener(listener);
   }
```

​	2、注册 DispacherServlet

​		创建一个 Web的 IOC 容器 

​			WebApplicationContext servletAppContext = createServletApplicationContext();

​		创建一个 DispatcherServlet

​			FrameworkServlet dispatcherServlet = createDispatcherServlet(servletAppContext);

​		将创建的 DispatcherServlet 添加到 ServletContext

​			ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, dispatcherServlet);

​			getServletMappings() 可自定义

```java
protected void registerDispatcherServlet(ServletContext servletContext) {
   String servletName = getServletName();
   Assert.hasLength(servletName, "getServletName() must not return empty or null");

   WebApplicationContext servletAppContext = createServletApplicationContext();
   Assert.notNull(servletAppContext,
         "createServletApplicationContext() did not return an application " +
         "context for servlet [" + servletName + "]");

   FrameworkServlet dispatcherServlet = createDispatcherServlet(servletAppContext);
   dispatcherServlet.setContextInitializers(getServletApplicationContextInitializers());

   ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, dispatcherServlet);
   Assert.notNull(registration,
         "Failed to register servlet with name '" + servletName + "'." +
         "Check if there is another servlet registered under the same name.");

   registration.setLoadOnStartup(1);
   registration.addMapping(getServletMappings());
   registration.setAsyncSupported(isAsyncSupported());
```

​	3、AbstractAnnotationConfigDispatcherServletInitializer注解方式配置的 DispatcherServlet 

​		创建根容器 createRootApplicationContext() 

​			getRootConfigClasses();

​		创建 web 的 ioc 容器，获取配置类 createServletApplicationContext()

​			getServletConfigClasses() 可自定义

总结：

​	以注解方式启动 Spring MVC，继承 AbstractAnnotationConfigDispatcherServletInitializer，实现抽象方法，指定 DispatchServlet 的配置信息

[官方文档](https://docs.spring.io/spring/docs/4.3.26.RELEASE/spring-framework-reference/htmlsingle/#mvc-servlet)

例子

导包：

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>4.3.13.RELEASE</version>
</dependency>

<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope><!--tomcat 本身有，所以声明打包时不适用-->
</dependency>
```

继承 AbstractAnnotationConfigDispatcherServletInitializer，web 容器启动的时候创建对象，调用方法初始化容器和前端控制器，自定义初始化器

Spring 配置类

Web 配置类

拦截请求的映射 / 表示拦截所有请求（包括静态资源 js. .png）但是不包括 *.jsp

/* 拦截所有请求，jsp 页面也拦截；jps 页面是 tomcat 的 jsp 引擎解析的

![](pics\12-SpringMVC整合配置类图.png)



Spring 的配置类不扫描 Controller；父容器

SpringMVC 的配置类只扫描 Controller（userDefaultFiltrresrs= false） 禁用默认的过滤郭泽

SpringConfig

```java
@Configuration
@ComponentScan(value = {"cn.hust.springmvc"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
})// 除了 Controller 都扫描
public class SpringConfig {
}
```

webConfig

```java
@Configuration
@ComponentScan(basePackages = {"cn.hust.springmvc"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})},
        // 只导入的情况要取消默认导入过滤器
        useDefaultFilters = false) // 只导入 Controller
// 注解标注的
public class WebAppConfig {
}
```

service

```java
@Service
public class HelloService {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}
```

controller

```java
@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return helloService.sayHello("tomcat...");
    }
}
```

![](pics\13-springmvc项目中web资源文件夹.png)

#### 3、定制 Spring MVC

1、注解标注在配置类上 @EnableWebMvc，开启 SpringMVC 定制配置功能

​	<mvc:annotation-driven/>

2、配置组件（视图解析器、视图映射、静态资源映射、拦截器）

3、实现 WebMvcConfigure 接口、或者基层 WebMvcConfigureAdapter ，定制部功能

配置视图解析器 jsp() 默认所有的页面都从 /WEB-INF/xxx.jsp 下找

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Success!</h1>
</body>
</html>
```

配置类

```java
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // sp("/WEB-INF/", ".jsp"); 默认按照 /WEB-INF/xxx.jsp 来拼路径
//       registry.jsp("/WEB-INF/views", ".jsp");
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
```

静态资源访问，default-servlet-hander 静态资源测试

```html
<h1>Welcome page</h1>
<img src="static/images/13-springmvc项目中web资源文件夹.png">
</body>
</html>
```

配置类

```java
    // 配置静态资源访问，默认是 SpringMVC 会拦静态资源，配置使得 tomcat 处理静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        super.configureDefaultServletHandling(configurer);
        configurer.enable();// 开启静态资源访问支持
    }
```

拦截器，实现 HanderIntercepter 接口

```java
/**
 * 自定义拦截器
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 拦截方法之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor...preHandle...");
        return true;
    }

    /**
     * 拦截方法执行之后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor...postHandle...");
    }

    /**
     * Note: Will only be called if this interceptor's {@code preHandle}
     *     method has successfully completed and returned {@code true}!
     *     拦截器的 preHeandle 被调用成功且返回 true 是执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("MyInterceptor...afterCompletion...");

    }
}
```

配置拦截器

```java
// 配置拦截器
@Override
public void addInterceptors(InterceptorRegistry registry) {
    InterceptorRegistration registration = registry.addInterceptor(new MyInterceptor());
    registration.addPathPatterns("/**");//拦截 context 及子路径
}
```

[更多配置参照官方文档](https://docs.spring.io/spring/docs/4.3.26.RELEASE/spring-framework-reference/htmlsingle/#mvc-config)



### 四、Servlet3.0 异步请求支持

传统：tomcat 线程池分配线程给请求

Servlet3.0 支持 异步

支持异步 @WebServlet 属性指定

req.startAsync()

startAsync.start(new Runnable() {})

相应，获取异步的向下文，获取响应

![](pics\14-异步处理线程池.png)

例子

```java
@WebServlet(value = "/async", asyncSupported = true) // 开启异步支持
public class MyAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 开启异步支持
        req.startAsync();

        // 获取异步上下文
        AsyncContext asyncContext = req.getAsyncContext();

        System.out.println("doGet start Current thread: " + Thread.currentThread());

        /**
         * Causes the container to dispatch a thread, possibly from a managed
         * thread pool, to run the specified <tt>Runnable</tt>. The container may
         * propagate appropriate contextual information to the <tt>Runnable</tt>.
         */
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("processService start finish Current thread: " + Thread.currentThread());
                    processService();
                    // 从 context 中获取响应，返回结果
                    ServletResponse response = asyncContext.getResponse();
                    response.getWriter().write("Async succcess");
                    System.out.println("processService finish Current thread: " + Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        System.out.println("doGet done Current thread: " + Thread.currentThread());
    }

    public void processService() throws InterruptedException {
        System.out.println("processService ing Current thread: " + Thread.currentThread());
        Thread.sleep(3000);
    }
}
```



SpringMVC 异步处理

#### 1、自定义 Controller，返回 Callable

1、控制器返回 Callable

2、Spring MVC 异步处理将 Callable提交到 TaskExecutor，使用一个隔离的线程执行

3、DispatchServlet 和 所有的 Filter 退出 web 容器，Response 保持打开。

4、Callable 返回结果，SpringMVC 将请求重新派发给容器，恢复之前的处理

5、DispatchserServlet 根据 Callable 返回的结果继续处理（从受请求到视图渲染）

异步拦截器：（Servlet）AsyncListener 实现 （SpringMVC）AsyncHandlerInterceptor 接口

例子

```java
@Controller
public class AsynController {

    @GetMapping("/testasyn")
    public Callable<String> testAsync() {
        System.out.println("controller start..." + Thread.currentThread());

        System.out.println("processService start Current thread: " + Thread.currentThread());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                processService();
                System.out.println("processService end Current thread: " + Thread.currentThread());
                return "执行完成";
            }
        };

        System.out.println("controller end..." + Thread.currentThread());
        return callable;
    }

    public void processService() throws InterruptedException {
        System.out.println("processService ing Current thread: " + Thread.currentThread());
        Thread.sleep(3000);
    }
}
```

MyInterceptor...preHandle...
controller start...Thread[http-nio-8080-exec-18,5,main]
processService start Current thread: Thread[http-nio-8080-exec-18,5,main]

controller end...Thread[http-nio-8080-exec-18,5,main]

====第一次连接，返回Callable，关闭DispatcherServlet，关闭所有Filter==========

processService ing Current thread: Thread[MvcAsync1,5,main]

=============执行业务逻辑

processService end Current thread: Thread[MvcAsync1,5,main]

=============执行完毕，根据 Callable的回调 重新建立连接

MyInterceptor...preHandle...
MyInterceptor...postHandle...
MyInterceptor...afterCompletion...



#### 2、复杂的业务场景，返回 DeferredResult 

![](pics\15-使用异步线程池的复杂场景.png)



DeferedResultQueue 保存 创建的 DeferedResult

Controller

```java
@Controller
public class AsynController {

    @Autowired
    DeferredResultQueue deferredResultQueue;

    @GetMapping("/testasyn")
    public Callable<String> testAsync() {
        System.out.println("controller start..." + Thread.currentThread());

        System.out.println("processService start Current thread: " + Thread.currentThread());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                processService();
                System.out.println("processService end Current thread: " + Thread.currentThread());
                return "success";
            }
        };

        System.out.println("controller end..." + Thread.currentThread());
        return callable;
    }

    @ResponseBody
    @GetMapping("/testasyn2")
    public DeferredResult<String> testAsync2() {
        // 创建 DeferdResult 并返回，可以设置超时时间，如果监听到 DefredResult.setData 则返回
        DeferredResult<String> deferredResult = new DeferredResult<String>(3000L, new String("timeout"));

        deferredResultQueue.add(deferredResult);
        return deferredResult;
    }

    @ResponseBody
    @GetMapping("/create")
    public String create() {
        DeferredResult<String> deferredResult = deferredResultQueue.get();
        String order = UUID.randomUUID().toString();
        deferredResult.setResult(order);
        return order;
    }

    public void processService() throws InterruptedException {
        System.out.println("processService ing Current thread: " + Thread.currentThread());
        Thread.sleep(3000);
    }
}
```

模仿队列组件

```java
@Component
public class DeferredResultQueue {

    private Queue<DeferredResult> innerQueue = new ConcurrentLinkedDeque<>();

    public void add(DeferredResult result) {
        innerQueue.add(result);
    }

    public DeferredResult<String> get() {
        return innerQueue.poll();
    }
}
```

文档](https://docs.spring.io/spring/docs/4.3.26.RELEASE/spring-framework-reference/htmlsingle/#mvc-config)