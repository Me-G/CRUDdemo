package com.mydemo.demo.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 *
 * @author ME
 */
public class MyRealm extends AuthenticatingRealm {

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
<<<<<<< HEAD:Demo/src/main/java/com/mydemo/demo/realm/ShiroRealm.java
        Object credential = null;
        if ("admin".equals(username)) {
            credential = "928bfd2577490322a6e19b793691467e";
        } else if ("user".equals(username)) {
            credential = "b8c2d5b0a37cc51f91d5e8970347a3a3";
        } else {
            throw new UnknownAccountException("用户不存在！");
        }
=======
        Object credential = "123456";
>>>>>>> parent of ec3be1d... SSM+Log4j2+Bootstrap+Ajax+Junit5+Shiro:Demo/src/main/java/com/mydemo/demo/realm/MyRealm.java
        //realmName：当前realm的name，调用父类的getName();方法即可
        String realName = getName();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credential, realName);

        return simpleAuthenticationInfo;
    }

}
<<<<<<< HEAD:Demo/src/main/java/com/mydemo/demo/realm/ShiroRealm.java
=======

>>>>>>> parent of ec3be1d... SSM+Log4j2+Bootstrap+Ajax+Junit5+Shiro:Demo/src/main/java/com/mydemo/demo/realm/MyRealm.java
