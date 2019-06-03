package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.mapper.RoleMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Albert on 2019/5/27.
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService service;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping("/list")
    public String list(Model model, QueryObject qo){
        System.out.println(qo);
        model.addAttribute("result",service.query(qo));
        return "role/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model,Long id){
        if (id != null){
            service.delete(id);
            roleMapper.deleteFromRolePermissionByRoleId(id);
            employeeMapper.deleteFromEmployeeRoleByRole(id);
        }
        return "redirect:/role/list.do";
    }

    @RequestMapping("/input")
    public String input(Model model,Long id){
        if(id != null){
            Role role = service.get(id);
            model.addAttribute("entity",role);
            model.addAttribute("allPermission",permissionService.listAll());
        }
        return "role/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Model model,Role role, Long[] ids){
        service.saveOrUpdate(role);
        roleMapper.deleteFromRolePermissionByRoleId(role.getId());
        if(ids != null){
            for (Long id:ids
                    ) {
                roleMapper.insetIntoRolePermission(role.getId(),id);
            }
        }

        return "redirect:/role/list.do";
    }
}
