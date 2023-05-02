package app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "app.components")
//@EnableWebMvc
public class SpringConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        final InputStream file = getClass().getClassLoader().getResourceAsStream("db/liquibase.properties");
        final Properties properties = new Properties();
        try {
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataSource.setDriverClassName(properties.getProperty("driver"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));

        return dataSource;
    }

    @Bean
    public SimpleJdbcInsert jdbcTemplate() {
        return new SimpleJdbcInsert(dataSource())
                .withTableName("receipt")
                .usingGeneratedKeyColumns("receipt_id");
    }
}
