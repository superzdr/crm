package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.domain.CustomerTransferHistory;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.CustomerMapper;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper mapper;
    @Override
    public void saveOrUpdate(Customer entity) {
        if(entity.getId() == null){
            mapper.insert(entity);
        }else{
            mapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public List<Customer> listAll() {
        return null;
    }


    /*@Override
    public PageResult<Customer> query(QueryObject qo) {
        //totalpage
        System.out.println("in query");
        System.out.println(qo);
        int count =mapper.queryForCount(qo);
        System.out.println(count);

        //totalpage !=0 pageResult
        if(count == 0){
            return new PageResult<Customer>(qo.getCurrentPage(),qo.getPageSize());
        }

        List<Customer> list = mapper.queryForList(qo);
        return new PageResult<Customer>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }*/

    public PageInfo<Customer> query(CustomerQueryObject qo){
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Customer> list = mapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public void updateStatus(Customer customer) {
        mapper.updateStatus(customer);
    }

    @Override
    public void changeSellerId(CustomerTransferHistory customerTransferHistory) {
        mapper.changeSellerId(customerTransferHistory);
    }

    @Override
    public void absorb(CustomerTransferHistory transferHistory) {
        Long id = ((Employee) SecurityUtils.getSubject().getPrincipal()).getId();
        Employee newseller = new Employee();
        newseller.setId(id);
        transferHistory.setNewseller(newseller);

        mapper.absorb(transferHistory);
    }
}
