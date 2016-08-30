package com.zgx.jurisdiction.shiro.realm;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2016/8/28.
 */
public class MyRealm extends AuthorizingRealm{

    /**
     * 为当前登录的Subject授予角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //此处应该去数据库查询用户权限信息，然后将权限吸纳新返回
        return null;
    }

    /**
     * 验证当前登录的Subject
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取给予用户名和密码的令牌
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        System.out.println("验证当前Subject时获取token为"+ ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //此处应该去数据库查询用户信息，然后跟页面传来的额数据进行对比。此处我们直接返回，不做任何处理
        System.out.println(token.getUsername()+token.getPassword());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), this.getName());
        this.setSession("currentUser",token.getUsername());
        return authenticationInfo;
    }

    /**
     * 将一些数据放到ShiroSession中,方便其他地方使用
     * @param currentUser
     * @param username
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if(null !=currentUser){
            Session session = currentUser.getSession();
            session.setTimeout(10000);
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if(null != session){
                session.setAttribute(key,value);
            }
        }
    }
}
