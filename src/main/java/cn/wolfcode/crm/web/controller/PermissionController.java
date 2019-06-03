package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Albert on 2019/5/27.
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService service;

    @Autowired
    private PermissionMapper mapper;

    @RequestMapping("/list")
    public String list(Model model, QueryObject qo){
        System.out.println(qo);
        model.addAttribute("result",service.query(qo));
        return "permission/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model,Long id){
        if (id != null){
            service.delete(id);
        }
        return "redirect:/permission/list.do";
    }
    @Autowired
    private ApplicationContext ctx;

    @RequestMapping("/reload")
    public String reload(Model model){
        //开始前先从数据库中读取数据
        List<String> existPermission = mapper.selectExpression();

        //
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> values = beansWithAnnotation.values();
        for (Object bean:values
             ) {
            Method[] declaredMethods = bean.getClass().getDeclaredMethods();
            for (Method method: declaredMethods
                 ) {
                if (method.isAnnotationPresent(RequiredPermission.class)){
                    RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                    String[] value = annotation.value();
                    Permission permission = new Permission();
                    permission.setName(value[0]);
                    permission.setExpression(value[1]);

                    if(!existPermission.contains(permission.getExpression())){
                        mapper.insert(permission);
                    }

                }
            }


        }
        return "redirect:/permission/list.do";
    }



}
