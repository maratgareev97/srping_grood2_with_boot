package ru.springmavc.crud;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootApplication
@PropertySource(value = {"classpath:db.properties", "classpath:hibernate.properties"})
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringBootSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
    }

    // нужен для избежания ошибки Whitelabel Error Page
    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getRequiredProperty("db.driver"));
        ds.setUrl(env.getRequiredProperty("db.url"));
        ds.setUsername(env.getRequiredProperty("db.username"));
        ds.setPassword(env.getRequiredProperty("db.password"));
        return ds;
    }


}
