package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.query.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface IPermissionService {

    void delete(Long id);

    List<Permission> listAll();

    PageResult<Permission> query(QueryObject qo);
}
