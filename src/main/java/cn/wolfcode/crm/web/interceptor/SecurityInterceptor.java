package cn.wolfcode.crm.web.interceptor;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.util.RequiredPermission;
import cn.wolfcode.crm.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Albert on 2019/5/30.
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Employee employeeInSession = (Employee) request.getSession().getAttribute("EMPLOYEE_IN_SESSION");
        Employee employeeInSession = UserContext.getEmployeeInSession();
        if(employeeInSession.isAdmin()){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if(!method.isAnnotationPresent(RequiredPermission.class)){
            return true;
        }

        RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
        //List<String> expressionInSession = (List<String>) request.getSession().getAttribute("EXPRESSION_IN_SESSION");
        List<String> expressionInSession = UserContext.getExpressionInSession();
        if(expressionInSession.contains(annotation.value()[1])){
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request,response);
        return false;
    }
}
