package cn.wolfcode.crm.web.interceptor;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.util.UserContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Albert on 2019/5/30.
 */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Employee employeeInSession = (Employee) request.getSession().getAttribute("EMPLOYEE_IN_SESSION");
        Employee employeeInSession = UserContext.getEmployeeInSession();
        if(employeeInSession == null){
            response.sendRedirect("/login.jsp");
            return false;
        }else {
            return true;
        }
    }
}
