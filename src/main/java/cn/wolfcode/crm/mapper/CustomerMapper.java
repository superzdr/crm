package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.domain.CustomerTransferHistory;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface CustomerMapper {

    int insert(Customer record);

    Customer selectByPrimaryKey(Long id);

    List<Customer> selectForList(CustomerQueryObject qo);

    int updateByPrimaryKey(Customer record);

    void updateStatus(Customer customer);

    void changeSellerId(CustomerTransferHistory customerTransferHistory);

    void absorb(CustomerTransferHistory transferHistory);
}