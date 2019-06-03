package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface IDepartmentService {
    void saveOrUpdate(Department entity);
    void delete(Long id);
    Department get(Long id);
    List<Department> listAll();

    PageInfo<Department> query(QueryObject qo);
}
