package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Albert on 2019/6/10.
 */
public interface CustomerReportMapper {
    /**
     * 报表查询
     * @param qo
     * @return
     */
    List<Map<String,Object>> selectCustomerReport(QueryObject qo);
}
