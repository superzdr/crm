package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Albert on 2019/5/27.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService service;

    @RequestMapping("/list")
    @RequiredPermission({"部门列表","department:list"})
    public String list(Model model, QueryObject qo){
        System.out.println(qo);
        model.addAttribute("result",service.query(qo));
        return "department/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"部门删除","department:delete"})
    @ResponseBody
    public JsonResult delete(Model model, Long id){
        JsonResult result = new JsonResult();
        try {
            if (id != null){
                service.delete(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败");
        }
        return result;
    }

    @RequestMapping("/input")
    @RequiredPermission({"部门编辑","department:input"})
    public String input(Model model,Long id){
        if(id != null){
            Department department = service.get(id);
            model.addAttribute("entity",department);
        }
        return "department/input";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"部门保存或更新","department:saveOrUpdate"})
    @ResponseBody
    public JsonResult saveOrUpdate(Model model, Department department){
       JsonResult result = new JsonResult();
        try {
            //System.out.println(1/0);
            service.saveOrUpdate(department);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了");
        }
        return result;
    }
}
