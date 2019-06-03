package cn.wolfcode.crm.shiro;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Albert on 2019/6/2.
 */
@Component("crmRealm")
public class CRMRealm extends AuthorizingRealm {
    @Autowired
    private EmployeeMapper mapper;

    @Override //获取认证信息(可以是程序本身写定,也可以是其他文件,或者是数据库)
    //authenticationToken用户的账号信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        Employee currEmp = mapper.selectByUsername(username);
        if(currEmp != null){
            return new SimpleAuthenticationInfo(currEmp,currEmp.getPassword(),"crmRealm");
        }
        return null;
    }

    /*protected AuthenticationInfo doGetAuthenticationInfo_back(AuthenticationToken authenticationToken) throws AuthenticationException {
        Object username = authenticationToken.getPrincipal(); //获得的是账户名
        //Object password = authenticationToken.getCredentials(); //获得的是密码凭证
        if("zhangsan".equals(username)){ //认证通过
            return new SimpleAuthenticationInfo(username,"666","crmRealm"); //返回包装对象
        }

        return null;
    }*/


    @Override //获取授权信息
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
