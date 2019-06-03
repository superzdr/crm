package cn.wolfcode.crm.util;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Albert on 2019/5/30.
 */
public class UserContext {
    public static HttpSession getSession(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        return session;
    }

    public static void setEmployeeSession(Employee employee){
        getSession().setAttribute("EMPLOYEE_IN_SESSION",employee);
    }

    public static void setExpressionSession(EmployeeMapper mapper, Employee employee){
        getSession().setAttribute("EXPRESSION_IN_SESSION",mapper.selectExpressionsByEmployeeId(employee.getId()));
    }

    public static Employee getEmployeeInSession(){
        return (Employee) getSession().getAttribute("EMPLOYEE_IN_SESSION");
    }

    public static List<String> getExpressionInSession(){
        return (List<String>) getSession().getAttribute("EXPRESSION_IN_SESSION");
    }
}