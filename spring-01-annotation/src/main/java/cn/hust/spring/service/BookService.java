package cn.hust.spring.service;

import cn.hust.spring.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service
public class BookService {

//    @Qualifier("bookDao2")
    @Autowired(required = false) // 找不到并不报错
//    @Resource(name = "bookDao2") // java 的注解 默认使用类名
    @Inject
    private BookDao bookDao;

    @Override
    public String toString() {
        return "BookService{" +
                "dao=" + bookDao +
                '}';
    }
}
