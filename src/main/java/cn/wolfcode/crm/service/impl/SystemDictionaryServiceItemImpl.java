package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.mapper.SystemDictionaryItemMapper;
import cn.wolfcode.crm.query.ItemQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemDictionaryItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Service
public class SystemDictionaryServiceItemImpl implements ISystemDictionaryItemService {
    @Autowired
    private SystemDictionaryItemMapper mapper;
    @Override
    public void saveOrUpdate(SystemDictionaryItem entity) {
        if(entity.getId() == null){
            mapper.insert(entity);
        }else{
            mapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public SystemDictionaryItem get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionaryItem> listAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo<SystemDictionaryItem> query(ItemQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),"sequence DESC");
        List<SystemDictionaryItem> list = mapper.queryForList(qo);
        return new PageInfo<>(list);
    }


}
