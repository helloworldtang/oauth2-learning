package com.tangcheng.oauth2.config.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * oauth2-learning
 * 配置个人用户凭据及其所属的角色
 *
 * @author tangcheng
 * @date 7/9/2018 8:11 AM
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        //被Spring Security用来处理验证
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        //Spring Security使用UserDetailsService处理返回的用户信息，这些用户信息将由Spring Security返回
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //定义用户信息：用户名、密码、角色
        auth.inMemoryAuthentication()
                .withUser("common.user")
                .password("password1")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("USER", "ADMIN");
    }
}
