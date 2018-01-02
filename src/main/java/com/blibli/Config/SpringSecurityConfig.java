package com.blibli.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select employee_uname, password, status from employee where employee_uname=? AND status=1")
                .authoritiesByUsernameQuery(
                        "select employee_uname, role from employee where employee_uname=?")
                .passwordEncoder(bCryptPasswordEncoder);
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                //pada access disini pakai hasAuthority (instead of hasRole) karena
                //kalau pake hasRole, di database harus ada prefix ROLE_ pada record-nya, contoh ROLE_CASHIER
                .antMatchers("/transaction/**").access("hasAnyAuthority('Cashier', 'Manager')")
                .antMatchers("/home/**","/", "/book/**", "/category/**", "/discount/**", "/emptyStok/**", "/report/**", "/report/**", "/report-detail/**", "/report-month/**", "/report-year/**", "/tampilemp/**", "/store/**", "/createBook/**", "/createCategory/**","/createEmployee/**","/createStore/**").access("hasAuthority('Manager')")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable();
    }

}
