package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.CustomerTraceHistory;
import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.CustomerTraceHistoryQueryObject;
import cn.wolfcode.crm.service.ICustomerTraceHistoryService;
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
@RequestMapping("/customerTraceHistory")
public class CustomerTraceHistoryController {
    @Autowired
    private ICustomerTraceHistoryService service;

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    @RequestMapping("/list")
    @RequiresPermissions(value={"跟进历史列表","customerTraceHistory:list"}, logical = Logical.OR)
    public String list(Model model,@ModelAttribute("qo") CustomerTraceHistoryQueryObject qo){
        System.out.println(qo);

        //查询所有的交流方式
        List<SystemDictionaryItem> communicationMethod = systemDictionaryItemService.selectByDictionarySn("communicationMethod");
        model.addAttribute("traceTypes",communicationMethod);
        model.addAttribute("result",service.query(qo));
        return "customerTraceHistory/list";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiresPermissions(value={"跟进历史保存或更新","customerTraceHistory:saveOrUpdate"}, logical = Logical.OR)
    @ResponseBody
    public JsonResult saveOrUpdate(Model model, CustomerTraceHistory customerTraceHistory){
       JsonResult result = new JsonResult();
        try {
            service.saveOrUpdate(customerTraceHistory);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了");
        }
        return result;
    }
}
