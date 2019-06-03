package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Albert on 2019/5/30.
 */
@Controller
public class LoginController {
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        try {
            employeeService.login(username, password);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "forward:/login.jsp";
        }
        return "forward:/role/list.do"; //注意这里需要使用forword,因为直接使用jsp文件并不会触发执行查询的过程
    }

}
