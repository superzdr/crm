package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.query.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
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
    public PageResult<Permission> query(QueryObject qo) {
        //totalpage
        System.out.println("in query");
        System.out.println(qo);
        int count =mapper.queryForCount(qo);
        System.out.println(count);

        //totalpage !=0 pageResult
        if(count == 0){
            return new PageResult<Permission>(qo.getCurrentPage(),qo.getPageSize());
        }

        List<Permission> list = mapper.queryForList(qo);
        return new PageResult<Permission>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }
}
