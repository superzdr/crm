package cn.wolfcode.crm.test;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.mapper.SystemDictionaryItemMapper;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Albert on 2019/5/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceImplTest {
   /* @Autowired
    private IDepartmentService service;
    @Test
    public void saveOrUpdate() throws Exception {
        //Department dept = new Department(6L,"销售部","xs");
        //service.saveOrUpdate(dept);
    }

    @Test
    public void delete() throws Exception {
        service.delete(7L);
    }

    @Test
    public void get() throws Exception {
        System.out.println(service.get(2L));
    }

    @Test
    public void listAll() throws Exception {
        System.out.println(service.getClass());
        System.out.println(service.listAll());
    }

    @Autowired
    private IEmployeeService employeeService;
    @Test
    public void testEmp() throws Exception{
        //employeeService.login("赵总","1");
    }

    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    public void testEmployeeMapper() throws Exception{
        System.out.println(employeeMapper.selectExpressionsByEmployeeId(6L));
    }*/

    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;
    @Test
    public void testSdim() throws Exception{
        System.out.println(systemDictionaryItemMapper.selectAll());
    }

}