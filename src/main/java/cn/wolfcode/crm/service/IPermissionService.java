package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface IPermissionService {

    void delete(Long id);

    List<Permission> listAll();

    PageInfo<Permission> query(QueryObject qo);
}
