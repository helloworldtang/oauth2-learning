package com.tangcheng.oauth2.config.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * oauth2-learning
 * 配置应用级的密钥名和密钥
 * <p>
 * AuthenticationServerConfigurer类是Spring Security的核心部分，
 * 提供了执行关键验证和授权功能的基本机制
 *
 * @author tangcheng
 * @date 7/9/2018 7:39 AM
 */
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;//鉴权

    @Autowired
    private UserDetailsService userDetailsService;//返回当前登陆人的用户信息

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //定义了验证服务注册了哪些客户端应用程序，
        // 这个方法的第一件事，就是注册哪些客户端应用程序允许访问由OAuth2服务保护的服务
        clients.inMemory()
                .withClient("todoList") //应用程序名
                .secret("helloWorld")   //应用程序对应的密钥
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")//授权类型列表,以逗号分隔
                //定义调用应用程序在请求OAuth2服务器获取访问令牌时可以操作的范围
                // 可以实现根据scope进行不同的授权规则
                .scopes("web_client", "mobile_client");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }


}
