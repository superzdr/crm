package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.query.EmployeeQueryObject;
import cn.wolfcode.crm.query.RoleRelation;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredPermission;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Albert on 2019/5/27.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService service;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping("/list")
    @RequiredPermission({"员工列表","employee:list"})
    public String list(Model model,@ModelAttribute("qo") EmployeeQueryObject qo){
        //System.out.println(qo);
        //System.out.println(departmentService.listAll());
        //System.out.println(1/0); //统一异常处理
        model.addAttribute("depts",departmentService.listAll());
        model.addAttribute("result",service.query(qo));
        return "employee/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"员工删除","employee:delete"})
    @ResponseBody
    public JsonResult delete(Model model, Long id){
        JsonResult result = new JsonResult();
        try {
            if (id != null){
                //System.out.println(1/0);
                service.deletFromEmployeeRole(id);
                service.delete(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败了");
        }
        return result;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public JsonResult batchDelete(Long[] ids){
        JsonResult result = new JsonResult();
        try {
            employeeMapper.batchDelete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除操作失败");
        }
        return result;
    }

    @RequestMapping("/input")
    public String input(Model model,Long id){
        if(id != null){
            Employee employee = service.get(id);
            //System.out.println(employee.getRoles());
            //System.out.println("-------------------------------------");
            model.addAttribute("entity",employee);
        }
        model.addAttribute("depts",departmentService.listAll());
        model.addAttribute("roles",roleService.listAll());
        return "employee/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Model model,Employee employee,Long[] ids){
        //System.out.println(employee);
        //System.out.println(Arrays.toString(ids));
        service.saveOrUpdate(employee);

        if(employee.isAdmin()){
            service.deletFromEmployeeRole(employee.getId());
            return "redirect:/employee/list.do";
        }

        if(employee.getId() != null){
            service.deletFromEmployeeRole(employee.getId());
            for (Long rid:ids) {
                service.insertIntoEmployeeRole(new RoleRelation(employee.getId(),rid));
            }
        }else{
            for (Long rid:ids) {
                service.insertIntoEmployeeRole(new RoleRelation(employee.getId(),rid));
            }
        }

        return "redirect:/employee/list.do";
    }
}
