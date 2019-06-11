package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.CustomerTraceHistory;
import cn.wolfcode.crm.domain.CustomerTransferHistory;
import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.CustomerTraceHistoryQueryObject;
import cn.wolfcode.crm.query.CustomerTransferHistoryQueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import cn.wolfcode.crm.service.ICustomerTraceHistoryService;
import cn.wolfcode.crm.service.ICustomerTransferHistoryService;
import cn.wolfcode.crm.service.ISystemDictionaryItemService;
import cn.wolfcode.crm.util.JsonResult;
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
@RequestMapping("/customerTransferHistory")
public class CustomerTransferHistoryController {
    @Autowired
    private ICustomerTransferHistoryService service;

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    @Autowired
    private ICustomerService customerService;

    @RequestMapping("/list")
    @RequiresPermissions(value={"跟进历史列表","customerTransferHistory:list"}, logical = Logical.OR)
    public String list(Model model,@ModelAttribute("qo") CustomerTransferHistoryQueryObject qo){
        System.out.println(qo);

        //查询所有的交流方式
        List<SystemDictionaryItem> communicationMethod = systemDictionaryItemService.selectByDictionarySn("communicationMethod");
        model.addAttribute("traceTypes",communicationMethod);
        model.addAttribute("result",service.query(qo));
        return "customerTransferHistory/list";
    }

    @RequestMapping("/save")
    @RequiresPermissions(value={"移交历史保存","customerTransferHistory:save"}, logical = Logical.OR)
    @ResponseBody
    public JsonResult saveOrUpdate(Model model, CustomerTransferHistory customerTransferHistory){
       JsonResult result = new JsonResult();
        try {
            service.saveOrUpdate(customerTransferHistory);
            customerService.changeSellerId(customerTransferHistory);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了");
        }
        return result;
    }

    @RequestMapping("/save2")
    @RequiresPermissions(value={"移交历史保存","customerTransferHistory:save"}, logical = Logical.OR)
    @ResponseBody
    public JsonResult save2(Model model, CustomerTransferHistory customerTransferHistory){
        JsonResult result = new JsonResult();
        try {
            service.saveOrUpdate(customerTransferHistory);
            customerService.absorb(customerTransferHistory);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了");
        }
        return result;
    }

    @RequestMapping("/absorb")
    @ResponseBody
    public JsonResult absorb(CustomerTransferHistory transferHistory){
        JsonResult result = new JsonResult();
        try {
            customerService.absorb(transferHistory);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了");
        }
        return result;
    }
}
