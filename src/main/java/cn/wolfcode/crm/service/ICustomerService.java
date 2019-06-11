package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.domain.CustomerTransferHistory;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface ICustomerService {
    void saveOrUpdate(Customer entity);


    List<Customer> listAll();

    PageInfo<Customer> query(CustomerQueryObject qo);

    void updateStatus(Customer customer);

    void changeSellerId(CustomerTransferHistory customerTransferHistory);

    void absorb(CustomerTransferHistory transferHistory);
}
