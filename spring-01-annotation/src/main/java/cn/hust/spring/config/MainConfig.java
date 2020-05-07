package cn.hust.spring.config;

import cn.hust.spring.bean.*;
import cn.hust.spring.component.*;
import org.springframework.context.annotation.*;

// 配置类等于配置文件
@Configuration // 告诉 Spring 这是一个 配置类
//@ComponentScan(value = "cn.hust.spring", includeFilters = {
//        /*@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),*/
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Dog.class)},
////        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)}
//        useDefaultFilters = false
//)
//@ComponentScan(value = "cn.hust.spring")
//@ComponentScans(value = {
//        @ComponentScan(value = "cn.hust.spring", includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
//        classes = {BookService.class})}, useDefaultFilters = false)
//})
// @ComponentScan(value = "cn.hust.spring") value 指定要扫描的包
// excludeFilters = Filter[] 排除
// includeFilters = Filter[] 指定扫描的时候只需要包含哪些组件
// 重复注解，也写多个来配置
// FilterType.ANNOTATION 按照注解
// ASSIGNABLE_TYPE 按照给定的类型
// ASPECTJ
// REGEX 正则
// CUSTOM 自定义规则
@Conditional(WindowsCondition.class) // 若不满条件，该配置类不生效
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class}) // 直接给容器中导入 Color Red 两个组件
public class MainConfig {

    /*
        <bean id="person" class="cn.hust.spring.bean.Person">
        <property name="age" value="18"></property>
        <property name="name" value="张三"></property>
    </bean>
     */
//    @Bean(value = "person01") // 给容器中注册一个 Bean；类型为返回值的类型，id 默认是用方法名作为id; value 属性可以改变 bean 的名字
    public Person person() {
        return new Person("lisi", 30);
    }


    @Lazy // 懒加载，针对单实例 bean，第一次使用 bean 时创建 bean
    @Scope //
//    @Bean("person")
    public Person person02() {
        System.out.println("Person 被实例化");
        return new Person("wangwu", 23);
    }

    @Conditional(WindowsCondition.class)
    @Bean("gates")
    public Person person03() {
        return new Person("Bill Gates", 60);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linux")
    public Person person04() {
        return new Person("Linus", 50);
    }

    @Bean
    public MyFactoryBean myFactoryBean() {
        return new MyFactoryBean();
    }
}
