package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.query.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.RoleRelation;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.LogicException;
import cn.wolfcode.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper mapper;
    @Override
    public void saveOrUpdate(Employee entity) {
        if(entity.getId() == null){
            mapper.insert(entity);
        }else{
            mapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Employee get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> listAll() {
        return mapper.selectAll();
    }

    @Override
    public PageResult<Employee> query(QueryObject qo) {
        //totalpage
        int count =mapper.queryForCount(qo);

        //totalpage !=0 pageResult
        if(count == 0){
            return new PageResult<Employee>(qo.getCurrentPage(),qo.getPageSize());
        }

        List<Employee> list = mapper.queryForList(qo);
        return new PageResult<Employee>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }

    @Override
    public int insertIntoEmployeeRole(RoleRelation rl) {
        return mapper.insertIntoEmployeeRole(rl);
    }

    @Override
    public int deletFromEmployeeRole(Long id) {
        return mapper.deletFromEmployeeRole(id);
    }

    @Override
    public void login(String username, String password) {
        Employee employee = mapper.selectByUsenameAndPassword(username, password);

        if(employee == null){
            throw new LogicException("用户名或密码错误"); //数据库中没有值的时候抛出异常
        }
       /* ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();*/
        HttpSession session = UserContext.getSession(); //使用重构后的方法
        //session.setAttribute("EMPLOYEE_IN_SESSION",employee);
        UserContext.setEmployeeSession(employee);
        //session.setAttribute("EXPRESSION_IN_SESSION",mapper.selectExpressionsByEmployeeId(employee.getId()));
        UserContext.setExpressionSession(mapper,employee);
    }
}
