package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.CustomerTraceHistory;
import cn.wolfcode.crm.mapper.CustomerTraceHistoryMapper;
import cn.wolfcode.crm.query.CustomerTraceHistoryQueryObject;
import cn.wolfcode.crm.service.ICustomerTraceHistoryService;
import cn.wolfcode.crm.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Service
public class CustomerTraceHistoryServiceImpl implements ICustomerTraceHistoryService {
    @Autowired
    private CustomerTraceHistoryMapper mapper;
    @Override
    public void saveOrUpdate(CustomerTraceHistory entity) {
        if(entity.getId() == null){
            entity.setInputUser(UserContext.getCurrentEmp());
            entity.setInputTime(new Date());
            mapper.insert(entity);
        }else{
            mapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public List<CustomerTraceHistory> listAll() {
        return null;
    }


    /*@Override
    public PageResult<Customer> query(QueryObject qo) {
        //totalpage
        System.out.println("in query");
        System.out.println(qo);
        int count =mapper.queryForCount(qo);
        System.out.println(count);

        //totalpage !=0 pageResult
        if(count == 0){
            return new PageResult<Customer>(qo.getCurrentPage(),qo.getPageSize());
        }

        List<Customer> list = mapper.queryForList(qo);
        return new PageResult<Customer>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }*/

    public PageInfo<CustomerTraceHistory> query(CustomerTraceHistoryQueryObject qo){
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<CustomerTraceHistory> list = mapper.selectForList(qo);
        return new PageInfo<>(list);
    }
}
