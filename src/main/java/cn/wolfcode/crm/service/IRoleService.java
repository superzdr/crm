package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface IRoleService {
    void saveOrUpdate(Role entity);
    void delete(Long id);
    Role get(Long id);
    List<Role> listAll();

    PageResult<Role> query(QueryObject qo);
}
