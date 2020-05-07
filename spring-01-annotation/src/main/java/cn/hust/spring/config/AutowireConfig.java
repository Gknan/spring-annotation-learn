package cn.hust.spring.config;

import cn.hust.spring.bean.Car;
import cn.hust.spring.bean.Lawyer;
import cn.hust.spring.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@ComponentScan(value = {"cn.hust.spring.service", "cn.hust.spring.controller", "cn.hust.spring.dao", "cn.hust.spring.bean"})
public class AutowireConfig {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setFlag(2);
        return bookDao;
    }


    @Autowired // 这里的 Autowire 可省略
    @Bean
    public Lawyer lawyer(Car car) {
        Lawyer lawyer = new Lawyer();
        lawyer.setCar(car);
        return lawyer;
    }
}
