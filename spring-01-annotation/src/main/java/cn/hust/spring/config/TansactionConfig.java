package cn.hust.spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@EnableTransactionManagement // 开启注解事务管理
@Configuration
@ComponentScan("cn.hust.spring.tx")
public class TansactionConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/db2019?autoReconnect=true&useUnicode=true&characterEncoding" +
                "=utf8&useSSL=false&&serverTimezone=Hongkong");

        return dataSource;
    }

    // 配置类的特殊之处，出入组件的方法，多次调用，返回的是容器中的 bean，不会多次创建新的实例
    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());

        return jdbcTemplate;
    }

    // 事务管理依赖于该组件
    @Bean
    public PlatformTransactionManager platformTransactionManager() throws PropertyVetoException {
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(dataSource());
        return platformTransactionManager;
    }
}
