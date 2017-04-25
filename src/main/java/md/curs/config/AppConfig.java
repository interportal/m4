package md.curs.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by MG
 */
@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource sqLiteDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:sqlite:/media/markusha/Work/Projects/curs/jee/m3/m4.sqlite");
        dataSource.setDriverClassName("org.sqlite.JDBC");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
