package tk.gushizone.distributed.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author gushizone@gmail.com
 * @date 2021/12/7 4:36 下午
 */
@Data
@Configuration
@ConfigurationProperties(value = "spring.datasource.ds2", ignoreInvalidFields = true)
@MapperScan(value = "tk.gushizone.distributed.ds2.mapper", sqlSessionFactoryRef = "ds2SqlSessionFactoryBean")
public class Ds2Config {

    private String url;

    private String username;

    private String password;

    private String mapperLocations;

    @Bean
    public DataSource ds2() {
        MysqlXADataSource xaDataSource = new MysqlXADataSource();
        xaDataSource.setUrl(url);
        xaDataSource.setUser(username);
        xaDataSource.setPassword(password);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        return atomikosDataSourceBean;
    }

    @Bean
    public SqlSessionFactory ds2SqlSessionFactoryBean(DataSource ds2) throws Exception {
        // mybatis-plus 要求使用 MybatisSqlSessionFactoryBean， 而不是 SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds2);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocations));
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }

}
