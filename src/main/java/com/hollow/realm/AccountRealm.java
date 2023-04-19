package com.hollow.realm;

import com.hollow.entity.Account;
import com.hollow.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 刘继涛
 * @version 1.0
 */


public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;
    /**
     * 授权操作
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        //设置角色
        Set<String> roles = new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //设置权限
        info.addStringPermission(account.getPerms());

        return info;
    }

    /**
     * 认证操作
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //我们将 authenticationToken 对象转换为 UsernamePasswordToken 对象，
        //因为我们需要获取用户的用户名和密码信息，并将其用于后续的身份验证操作，比如验证用户的身份是否合法。
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //我们先对用户名进行检验，得到用户名后去查数据库中是否存在该用户
        Account account = accountService.findByUsername(token.getUsername());
        if (account!= null) {
            //account表示用户的账号，account.getPassword()表示用户的密码，getName()是在使用该认证信息对象时获取当前用户的身份标识信息。
            return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
        }
        //如果上面if不成立，就说明用户不存在，会抛出unknownAccountException异常
        return null;
    }
}
