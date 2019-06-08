package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public JsonResult delete(Model model, Long id){
        JsonResult result = new JsonResult();
        try {
            if (id != null){
                service.delete(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除操作失败");
        }

        return result;
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
            //因为使用的是增强(Advisor),使用的是继承的代理类,因此要获得原本的注解需要在父类中获取
            Method[] declaredMethods = bean.getClass().getSuperclass().getDeclaredMethods();
            for (Method method: declaredMethods
                 ) {
                if (method.isAnnotationPresent(RequiresPermissions.class)){
                    RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);
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
