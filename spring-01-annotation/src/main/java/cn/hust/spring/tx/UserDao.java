package cn.hust.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void insert(String user, int age) {
        String sql = "INSERT tx_user(username, age) VALUES(?, ?)";

        int update = jdbcTemplate.update(sql, user, age);
        // 其他的 dao xxx 进行处理
        if (update != 0) {
            System.out.println("插入成功");
        }
        int i = 10 / 0;
    }
}
