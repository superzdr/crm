package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemDictionaryService;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Albert on 2019/5/27.
 */
@Controller
@RequestMapping("/systemDictionary")
public class SystemDictionaryController {
    @Autowired
    private ISystemDictionaryService service;

    @RequestMapping("/list")
    public String list(Model model, QueryObject qo){
        System.out.println(qo);
        model.addAttribute("result",service.query(qo));
        return "systemDictionary/list";
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
            SystemDictionary systemDictionary = service.get(id);
            model.addAttribute("entity",systemDictionary);
        }
        return "systemDictionary/input";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Model model, SystemDictionary systemDictionary){
       JsonResult result = new JsonResult();
        try {
            //System.out.println(1/0);
            service.saveOrUpdate(systemDictionary);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了");
        }
        return result;
    }
}
