package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.mapper.CustomerReportMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Albert on 2019/6/10.
 */
@Service
public class CustomerReportServiceImpl implements ICustomerReportService {
    @Autowired
    private CustomerReportMapper mapper;

    @Override
    public List<Map<String, Object>> listCustomerReport(QueryObject qo) {
        return mapper.selectCustomerReport(qo);
    }
}
