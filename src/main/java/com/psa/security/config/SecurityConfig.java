package com.psa.security.config;

import com.psa.Service.AuthenticationService;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by ckirb on 11/11/2016.
 */
@Configuration
@ComponentScan("com.psa")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@PropertySource("classpath:jdbc.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public DriverManagerDataSource dataSource() {
//
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//
//        driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//
//        driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
//
//        driverManagerDataSource.setUsername("PSA");
//
//        driverManagerDataSource.setPassword("1234");
//
//        return driverManagerDataSource;
//
//    }

    @Autowired
    Environment env;
    @Autowired
    AuthenticationService authenticationService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").hasAnyRole("ADMIN","USER").
                and().formLogin();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        auth.userDetailsService(authenticationService).passwordEncoder(encoder);
    }
    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }
}
