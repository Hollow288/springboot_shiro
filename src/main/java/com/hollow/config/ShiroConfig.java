package com.hollow.config;

import com.hollow.realm.AccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Hashtable;
import java.util.Map;


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
        //权限设置
        Map<String, String> map = new Hashtable<>();
        //用户必须登录才能访问的
        map.put("/main","authc");
        //用户必须得到授权才可以访问的;
        map.put("/manage","perms[manage]");
        //用户必须拥有角色才能访问的
        map.put("/administrator","roles[administrator]");
        //通过map来设置过滤器
        factoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面，如果用户没登录就会跳转到login.jsp但这不是我们想要的，我们要让他跳转到login.html
        //并且要注意，这里的路径是先会访问到我门controller->视图解析器->templates
        factoryBean.setLoginUrl("/login");
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
