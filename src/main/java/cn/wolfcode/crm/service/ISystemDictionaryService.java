package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface ISystemDictionaryService {
    void saveOrUpdate(SystemDictionary entity);
    void delete(Long id);
    SystemDictionary get(Long id);
    List<SystemDictionary> listAll();

    PageInfo<SystemDictionary> query(QueryObject qo);
}
