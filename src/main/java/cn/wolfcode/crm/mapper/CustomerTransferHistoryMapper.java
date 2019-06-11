package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.CustomerTransferHistory;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface CustomerTransferHistoryMapper {


    int insert(CustomerTransferHistory record);

    List<CustomerTransferHistory> selectForList(QueryObject qo);

}