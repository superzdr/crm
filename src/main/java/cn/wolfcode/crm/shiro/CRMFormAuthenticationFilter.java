package cn.wolfcode.crm.shiro;

import cn.wolfcode.crm.util.JsonResult;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by Albert on 2019/6/2.
 */
@Component("crmFormAuthenticationFilter")
public class CRMFormAuthenticationFilter extends FormAuthenticationFilter{
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        response.setContentType("text/json;charset=UTF-8");
        String jsonString = JSON.toJSONString(new JsonResult());
        response.getWriter().print(jsonString);
        return false; //false表明后面的过滤器不需要继续执行
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        response.setContentType("text/json;charset=UTF-8");
        JsonResult result = new JsonResult();
        result.mark("账户或密码错误");
        String jsonString = JSON.toJSONString(result);
        try {
            response.getWriter().print(jsonString);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return true;
    }
}
