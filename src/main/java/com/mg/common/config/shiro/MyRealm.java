package com.mg.common.config.shiro;

import com.mg.beans.UserEntity;
import com.mg.dao.User.UserRepository;
import com.mg.service.user.IUser;
import com.mg.utils.EnDecryptUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: fujian
 * @Date: 2018/7/20 10:28
 * @Description:
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private IUser userService;
    @Autowired
    UserRepository userRepository;

    /**
     * 为当前subject授权
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*//查询用户名称
        User user = loginService.findByName(name);
        //添加角色和权限
        for (Role role:user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission:role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }*/
        info.addRole("guest");
        return info;
    }

    /**
     * 认证登陆subject身份
     * @param authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();  //得到用户名
        /*根据用户名从库里查询用户*/
        UserEntity user = userRepository.findByUserName(username);
        if (user==null) {
            throw new UnknownAccountException();
        } else if(user.getStatus()==0) {
            throw new DisabledAccountException();
        } else {
            // 校验密码
            ByteSource credentialsSalt=ByteSource.Util.bytes(user.getUserName());//盐值为用户账户名
            return new SimpleAuthenticationInfo(username, user.getPassWord(),credentialsSalt, getName());
        }
    }

}
