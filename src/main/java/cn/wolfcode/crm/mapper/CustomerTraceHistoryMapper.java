package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.CustomerTraceHistory;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface CustomerTraceHistoryMapper {


    int insert(CustomerTraceHistory record);


    List<CustomerTraceHistory> selectForList(QueryObject qo);


    int updateByPrimaryKey(CustomerTraceHistory record);
}