package cn.wolfcode.crm.service;

import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Albert on 2019/6/10.
 */
public interface ICustomerReportService {
    List<Map<String,Object>> listCustomerReport(QueryObject qo);
}
