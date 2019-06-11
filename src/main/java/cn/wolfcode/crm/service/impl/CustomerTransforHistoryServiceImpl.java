package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.CustomerTraceHistory;
import cn.wolfcode.crm.domain.CustomerTransferHistory;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.CustomerTraceHistoryMapper;
import cn.wolfcode.crm.mapper.CustomerTransferHistoryMapper;
import cn.wolfcode.crm.query.CustomerTraceHistoryQueryObject;
import cn.wolfcode.crm.query.CustomerTransferHistoryQueryObject;
import cn.wolfcode.crm.service.ICustomerTraceHistoryService;
import cn.wolfcode.crm.service.ICustomerTransferHistoryService;
import cn.wolfcode.crm.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Service
public class CustomerTransforHistoryServiceImpl implements ICustomerTransferHistoryService {
    @Autowired
    private CustomerTransferHistoryMapper mapper;
    @Override
    public void saveOrUpdate(CustomerTransferHistory entity) {
        if(entity.getId() == null){
            entity.setOperateTime(new Date());
            entity.setOperator((Employee) SecurityUtils.getSubject().getPrincipal());
            mapper.insert(entity);
        }else{

        }
    }

    public PageInfo<CustomerTransferHistory> query(CustomerTransferHistoryQueryObject qo){
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<CustomerTransferHistory> list = mapper.selectForList(qo);
        return new PageInfo<>(list);
    }
}
