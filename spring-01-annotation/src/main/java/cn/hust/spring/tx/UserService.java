package cn.hust.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void insert(String user, int age){
        userDao.insert(user, age);
    }
}
