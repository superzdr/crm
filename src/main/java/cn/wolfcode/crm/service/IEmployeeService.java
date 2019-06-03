package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.RoleRelation;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface IEmployeeService {
    void saveOrUpdate(Employee entity);
    void delete(Long id);
    Employee get(Long id);
    List<Employee> listAll();

    PageResult<Employee> query(QueryObject qo);

    int insertIntoEmployeeRole(RoleRelation rl);

    int deletFromEmployeeRole(Long id);

    void login(String username, String password);
}
