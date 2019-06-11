package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.query.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.RoleRelation;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.shiro.CRMRealm;
import cn.wolfcode.crm.util.LogicException;
import cn.wolfcode.crm.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private CRMRealm realm;

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
    public PageInfo<Employee> query(QueryObject qo){
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Employee> list = mapper.queryForList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public int insertIntoEmployeeRole(RoleRelation rl) {
        return mapper.insertIntoEmployeeRole(rl);
    }

    @Override
    public int deletFromEmployeeRole(Long id) {
        realm.clearCached();//删除关系前先把缓存清除
        return mapper.deletFromEmployeeRole(id);
    }

    //在使用了shiro filter之后该方法实际上是没有用到的
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

    @Override
    public Workbook exportXls() {
        Workbook workbook = new HSSFWorkbook();

        List<Employee> employees = mapper.selectAll();

        Sheet sheet = workbook.createSheet("员工信息");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("账号");
        header.createCell(1).setCellValue("年龄");
        header.createCell(2).setCellValue("邮箱");

        for (int i = 0; i <employees.size() ; i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(employees.get(i).getName());
            row.createCell(1).setCellValue(employees.get(i).getAge());
            row.createCell(2).setCellValue(employees.get(i).getEmail());
        }
        return workbook;
    }

    @Override
    public void importXls(InputStream inputStream) {
        try {
            HSSFWorkbook sheets = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = sheets.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i <lastRowNum ; i++) {
                Employee employee = new Employee();
                HSSFRow row = sheet.getRow(i + 1);
                employee.setName(row.getCell(0).getStringCellValue());
                employee.setAge(Double.valueOf(row.getCell(1).getNumericCellValue()).intValue());
                employee.setEmail(row.getCell(2).getStringCellValue());
                mapper.insert(employee);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> listEmpsByRoleSns(String... roleSns) {
        return mapper.selectByRoleSns(roleSns);
    }


}
