package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.ItemQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface ISystemDictionaryItemService {
    void saveOrUpdate(SystemDictionaryItem entity);
    void delete(Long id);
    SystemDictionaryItem get(Long id);
    List<SystemDictionaryItem> listAll();

    PageInfo<SystemDictionaryItem> query(ItemQueryObject qo);

    List<SystemDictionaryItem> selectByDictionarySn(String sn);
}
