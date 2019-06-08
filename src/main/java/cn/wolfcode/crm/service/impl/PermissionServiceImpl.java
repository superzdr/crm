package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.query.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper mapper;


    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }



    @Override
    public List<Permission> listAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo<Permission> query(QueryObject qo){
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Permission> list = mapper.queryForList(qo);
        return new PageInfo<>(list);
    }
}
