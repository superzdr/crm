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
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

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
    @RequiresPermissions(value={"员工列表","employee:list"}, logical = Logical.OR)
    public String list(Model model,@ModelAttribute("qo") EmployeeQueryObject qo){
        //System.out.println(qo);
        //System.out.println(departmentService.listAll());
        //System.out.println(1/0); //统一异常处理
        model.addAttribute("depts",departmentService.listAll());
        model.addAttribute("result",service.query(qo));
        return "employee/list";
    }

    @RequestMapping("/delete")
    @RequiresPermissions(value={"员工删除","employee:delete"}, logical = Logical.OR)
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
        if(employee.getId()==null){
            employee.setPassword(new Md5Hash(employee.getPassword(),employee.getName()).toString());
        }

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
    @RequiresPermissions(value={"员工导出","employee:exportXls"}, logical = Logical.OR)
    @RequestMapping("/exportXls")
    public void exportXls(HttpServletResponse response) throws Exception{
        response.setHeader("Content-Disposition","attached;filename=employee.xls");
        Workbook workbook = service.exportXls();
        workbook.write(response.getOutputStream());
        //response.getWriter().write("Hello");
    }

    @RequiresPermissions(value={"员工导入","employee:importXls"}, logical = Logical.OR)
    @RequestMapping("/importXls")
    public void importXls(HttpServletResponse response, MultipartFile file) throws Exception{
        System.out.println(file);
        service.importXls(file.getInputStream());
        response.sendRedirect("/employee/list.do");
    }
}
