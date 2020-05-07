package cn.hust.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

// 默认创建的 Bean 的id 是类首字母小写
//@Primary
@Repository
public class BookDao {

    private int flag = 1;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "flag=" + flag +
                '}';
    }
}
