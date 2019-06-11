package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.CustomerReportQueryObject;
import cn.wolfcode.crm.service.ICustomerReportService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Albert on 2019/5/27.
 */
@Controller
@RequestMapping("/customerReport")
public class CustomerReportController {
    @Autowired
    private ICustomerReportService service;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") CustomerReportQueryObject qo) {

        model.addAttribute("list",service.listCustomerReport(qo));
        return "customerReport/list";
    }

    @RequestMapping("/reportByBar")
    public String reportByBar(Model model, @ModelAttribute("qo") CustomerReportQueryObject qo) {
        model.addAttribute("groupTypeName",qo.getGroupTypeName());
        //model.addAttribute("list",service.listCustomerReport(qo));

        List<Object> groupTypes = new ArrayList<>();
        List<Object> datas = new ArrayList<>();
        List<Map<String, Object>> maps = service.listCustomerReport(qo);
        for (Map<String, Object> map : maps) {
            groupTypes.add(map.get("groupType"));
            datas.add(map.get("number"));
        }
        model.addAttribute("groupTypes", JSON.toJSONString(groupTypes));
        model.addAttribute("datas", JSON.toJSONString(datas));
        return "customerReport/reportByBar";
    }

    @RequestMapping("/reportByPie")
    public String reportByPie(Model model, @ModelAttribute("qo") CustomerReportQueryObject qo) {
        model.addAttribute("groupTypeName",qo.getGroupTypeName());
        //model.addAttribute("list",service.listCustomerReport(qo));

        List<Object> groupTypes = new ArrayList<>();
        List<Map<String,Object>> datas = new ArrayList<>();
        List<Map<String, Object>> maps = service.listCustomerReport(qo);
        for (Map<String, Object> map : maps) {
            groupTypes.add(map.get("groupType"));
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name",map.get("groupType"));
            hashMap.put("value",map.get("number"));
            datas.add(hashMap);
        }
        model.addAttribute("groupTypes", JSON.toJSONString(groupTypes));
        model.addAttribute("datas", JSON.toJSONString(datas));
        return "customerReport/reportByPie";
    }




}
