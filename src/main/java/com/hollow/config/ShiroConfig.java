package com.hollow.config;

import com.hollow.realm.AccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author 刘继涛
 * @version 1.0
 */

@Configuration
public class ShiroConfig {

    //过滤器工厂是一个Spring FactoryBean，用于创建ShiroFilter实例，可以将它配置在Spring IOC容器中来管理Shiro框架的安全过滤器链。
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        return factoryBean;
    }


    //安全管理器，开发者自定义的Realm需要注入到DefaultWebSecurityDManager进行管理才能生效
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("accountRealm") AccountRealm accountRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm);
        return manager;
    }

    //这里是我们自己创建的Realm 把它加入到Spring bean中
    @Bean
    public AccountRealm accountRealm(){
        return new AccountRealm();
    }
}
