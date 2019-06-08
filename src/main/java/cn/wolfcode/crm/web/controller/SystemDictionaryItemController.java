package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.ItemQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemDictionaryItemService;
import cn.wolfcode.crm.service.ISystemDictionaryService;
import cn.wolfcode.crm.util.JsonResult;
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
@RequestMapping("/systemDictionaryItem")
public class SystemDictionaryItemController {
    @Autowired
    private ISystemDictionaryItemService service;

    @Autowired
    private ISystemDictionaryService dictionaryService;

    //由于需要对额外的关键字进行查询,需要对qo进行升级,升级后的qo中包含parentid
    //另外需要对mapper中的查询语句进行修改
    @RequestMapping("/list")
    public String list(Model model,@ModelAttribute("qo") ItemQueryObject qo){
        model.addAttribute("dictionaryList",dictionaryService.listAll());
        if(qo.getParentId() != null){
            model.addAttribute("result",service.query(qo));
        }
        return "systemDictionaryItem/list";
    }


    @RequestMapping("/delete")
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
    public String input(Model model,Long id){
        if(id != null){
            SystemDictionaryItem systemDictionaryItem = service.get(id);
            model.addAttribute("entity",systemDictionaryItem);
        }
        return "systemDictionary/input";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Model model, SystemDictionaryItem systemDictionaryItem){
       JsonResult result = new JsonResult();
        try {
            //System.out.println(1/0);
            service.saveOrUpdate(systemDictionaryItem);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了");
        }
        return result;
    }
}
