package cn.hust.spring.config;

import cn.hust.spring.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@PropertySource(value = {"classpath:/dbconfig.properties"})
@Configuration
//@Profile("test")
public class ProfileConfig implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    private String driverClass;

    @Profile("dev")
    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

//    @Profile(value = "default") // 指定该 bean 生效的 Profile 环境 默认是 default 环境
    @Profile("test")
    @Bean
    public DataSource dataSourceTest(@Value("db.password") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/imooc_coupon_data?autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false&&serverTimezone=Hongkong");

        return dataSource;
    }

    @Profile("dev")
    @Bean
    public DataSource dataSourceDev(@Value("db.password") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/db2019?autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false&&serverTimezone=Hongkong");

        return dataSource;
    }

    @Profile("prod")
    @Bean
    public DataSource dataSourceProd(@Value("db.password") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false&&serverTimezone=Hongkong");

        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String driverClass = resolver.resolveStringValue("${db.driverClass}");
        this.driverClass = driverClass;
    }
}
