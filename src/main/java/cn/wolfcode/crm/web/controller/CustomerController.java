package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.ISystemDictionaryItemService;
import cn.wolfcode.crm.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService service;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    @RequestMapping("/potentialList")
    @RequiresPermissions(value = {"客户列表", "customer:list"}, logical = Logical.OR)
    public String list(Model model, @ModelAttribute("qo") CustomerQueryObject qo) {
        Employee currentEmp = (Employee) SecurityUtils.getSubject().getPrincipal();
        //该段代码的意思是如果是非管理员那么查询的销售人员自能是自己
        if(!(currentEmp.isAdmin() || SecurityUtils.getSubject().hasRole("Market_Manager"))){
            qo.setSellerId(currentEmp.getId());
        }

        PageInfo<Customer> result = service.query(qo);
        model.addAttribute("result", result);

        //查询所有的营销人员
        List<Employee> sellers = employeeService.listEmpsByRoleSns("Market_Manager", "Market");
        model.addAttribute("sellers", sellers);


        //查询所有的工作
        List<SystemDictionaryItem> jobItem = systemDictionaryItemService.selectByDictionarySn("job");
        //查询所有的来源
        List<SystemDictionaryItem> sourceItem=systemDictionaryItemService.selectByDictionarySn("source");

        //查询所有的交流方式
        List<SystemDictionaryItem> communicationMethod = systemDictionaryItemService.selectByDictionarySn("communicationMethod");

        model.addAttribute("jobs",jobItem);
        model.addAttribute("sources",sourceItem);
        model.addAttribute("ccts",communicationMethod);

        return "customer/list";
    }


    @RequestMapping("/input")
    @RequiresPermissions(value = {"客户编辑", "customer:input"}, logical = Logical.OR)
    public String input(Model model, Long id) {
        if (id != null) {
            // Customer customer = service.get(id);
            //model.addAttribute("entity",customer);
        }
        return "customer/input";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiresPermissions(value = {"客户保存或更新", "customer:saveOrUpdate"}, logical = Logical.OR)
    @ResponseBody
    public JsonResult saveOrUpdate(Model model, Customer customer) {
        JsonResult result = new JsonResult();
        try {
            //System.out.println(1/0);
            service.saveOrUpdate(customer);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了");
        }
        return result;
    }

    @RequestMapping("/updateStatus")
    @RequiresPermissions(value = {"客户保存或更新", "customer:saveOrUpdate"}, logical = Logical.OR)
    @ResponseBody
    public JsonResult updateStatus(Model model, Customer customer) {
        JsonResult result = new JsonResult();
        try {
            //System.out.println(1/0);
            service.updateStatus(customer);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了");
        }
        return result;
    }

    @RequestMapping("/poolList")
    public String poolList(Model model, @ModelAttribute("qo") CustomerQueryObject qo){
        qo.setStatus(Customer.STATUS_POOL);

        PageInfo<Customer> result = service.query(qo);
        model.addAttribute("result", result);

        //查询所有的营销人员
        List<Employee> sellers = employeeService.listEmpsByRoleSns("Market_Manager", "Market");
        model.addAttribute("sellers", sellers);
        return "customer/poolList";
    }

}
