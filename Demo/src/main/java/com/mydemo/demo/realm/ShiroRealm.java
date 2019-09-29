package com.mydemo.demo.realm;

import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 *
 * @author ME
 */
public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo:" + token);
        //1.把AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //2.从 UsernamePasswordToken 中获取 username 
        String username = upToken.getUsername();

        //3.调用数据库方法，从数据库中查询username对应的用户记录
        System.out.println("从数据库中获取username：" + username + " 所对应的用户信息。");

        //4.若用户不存在，则抛出异常UnknowAccountException
        if ("unknown".equals(username)) {
            throw new UnknownAccountException("用户不存在！");
        }

        //5.根据用户信息情况，决定是否抛出其他AuthenticationException异常
        if ("monster".equals(username)) {
            throw new LockedAccountException("用户被锁定！");
        }

        //6.根据用户信息情况，构建 AuthenticationInfo 对象并返回，通常使用的实现类SimpleAuthenticationInfo
        //principal:认证的实体信息，可以是username，也可以是数据表对应的用户的实体对象类
        Object principal = username;
        //credential：密码
        Object credential = null;
        if ("admin".equals(username)) {
            credential = "928bfd2577490322a6e19b793691467e";
        } else if ("user".equals(username)) {
            credential = "b8c2d5b0a37cc51f91d5e8970347a3a3";
        } else {
            throw new UnknownAccountException("用户不存在！");
        }
        //realmName：当前realm的name，调用父类的getName();方法即可
        String realName = getName();
        //credentialsSalt:盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credential, credentialsSalt, realName);

        return simpleAuthenticationInfo;
    }

//    public static void main(String[] args) {
//        String algorithmName = "MD5";
//        Object credential = "123456";
//        Object salt = ByteSource.Util.bytes("user");
//        int hashIterations = 2;
//        Object result = new SimpleHash(algorithmName, credential, salt, hashIterations);
//        System.out.println(result);
//    }

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        System.out.println("doGetAuthorizationInfo...");
        //1.从PrincipalCollection中获取登录用户信息
        Object principal = pc.getPrimaryPrincipal();
        //2.利用登录用户的信息来获取当前用户的角色或权限（可能需要查询数据库）
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if ("admin".equals(principal)) {
            roles.add("admin");
        }
        //3.创建SimpleAuthorizationInfo，并设置其roles属性
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        //4.返回SimpleAuthorizationInfo对象
        return simpleAuthorizationInfo;
    }

}

