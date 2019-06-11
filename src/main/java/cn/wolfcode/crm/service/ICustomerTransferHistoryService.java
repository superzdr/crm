package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.CustomerTransferHistory;
import cn.wolfcode.crm.query.CustomerTraceHistoryQueryObject;
import cn.wolfcode.crm.query.CustomerTransferHistoryQueryObject;
import com.github.pagehelper.PageInfo;

/**
 * Created by Albert on 2019/5/27.
 */
public interface ICustomerTransferHistoryService {
    void saveOrUpdate(CustomerTransferHistory entity);

    PageInfo<CustomerTransferHistory> query(CustomerTransferHistoryQueryObject qo);
}
