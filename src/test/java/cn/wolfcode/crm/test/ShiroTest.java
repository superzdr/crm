package cn.wolfcode.crm.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Albert on 2019/6/1.
 */
public class ShiroTest {
    @Test
    public void testShiro() throws Exception {
        //根据配置文件创建securityManager工厂,该工厂的配置信息包括用户名密码列表
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //通过工厂的getInstance方法获得secutrityManager
        SecurityManager mananger = iniSecurityManagerFactory.getInstance();
        //把该manager配置给环境,决定了环境使用什么样的securityManager
        SecurityUtils.setSecurityManager(mananger);
        //通过工具包来获得一个主体subject,其实质上就是securityManager的代理,用于验证token
        Subject subject = SecurityUtils.getSubject();
        //创建用户令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        //检查验证是否通过
        System.out.println(subject.isAuthenticated());
        //进行验证
        subject.login(token);
        System.out.println(subject.isAuthenticated());

        //判断用户是否拥有对应的角色或者权限
        System.out.println(subject.isPermitted("user:delete"));
        System.out.println(Arrays.toString(subject.isPermitted("user:delete", "user:update")));
        System.out.println(subject.isPermittedAll("user:delete", "user:update"));

        System.out.println(subject.hasRole("role1"));
        System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1", "role2"))));
        System.out.println(subject.hasAllRoles(Arrays.asList("role1", "role3")));

    }

    @Test
    public void testMD5() throws Exception{
        Md5Hash md5Hash = new Md5Hash("1","admin",3);
        System.out.println(md5Hash);
    }

}
