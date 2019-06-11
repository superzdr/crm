package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.CustomerTraceHistory;
import cn.wolfcode.crm.query.CustomerTraceHistoryQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface ICustomerTraceHistoryService {
    void saveOrUpdate(CustomerTraceHistory entity);

    List<CustomerTraceHistory> listAll();

    PageInfo<CustomerTraceHistory> query(CustomerTraceHistoryQueryObject qo);
}
