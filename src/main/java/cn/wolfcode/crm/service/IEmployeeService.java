package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.query.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.RoleRelation;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface IEmployeeService {
    void saveOrUpdate(Employee entity);
    void delete(Long id);
    Employee get(Long id);
    List<Employee> listAll();

    PageInfo<Employee> query(QueryObject qo);

    int insertIntoEmployeeRole(RoleRelation rl);

    int deletFromEmployeeRole(Long id);

    void login(String username, String password);

    Workbook exportXls();

    void importXls(InputStream inputStream);
}
