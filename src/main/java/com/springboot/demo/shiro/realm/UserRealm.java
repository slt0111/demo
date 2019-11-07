package com.springboot.demo.shiro.realm;

import com.springboot.demo.config.ApplicationContextRegister;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        String userId = ((User) SecurityUtils.getSubject().getPrincipal()).getId();
        Set<String> stringSet = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        stringSet.add("user:show");
        stringSet.add("user:admin");
        info.setStringPermissions(stringSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        String password = new String((char[]) token.getCredentials());

        UserMapper userMapper = ApplicationContextRegister.getBean(UserMapper.class);
        // 查询用户信息
        User user = null;
        if (username.length() > 0) {
            user = userMapper.getUserByName(username);
            if (user == null) {
                throw new UnknownAccountException("账号或密码不正确");
            }

            // 密码错误
            if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
                throw new IncorrectCredentialsException("账号或密码不正确");
            }

            // 账号锁定
            if (user.getEnable() == false) {
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }
        } else {
           return null;
        }

        //不使用shiro自带的密码验证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
