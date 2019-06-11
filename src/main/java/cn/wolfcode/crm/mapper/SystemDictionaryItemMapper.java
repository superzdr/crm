package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.ItemQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    int updateByPrimaryKey(SystemDictionaryItem record);

    List<SystemDictionaryItem> queryForList(ItemQueryObject qo);

    List<SystemDictionaryItem> selectByDictionarySn(@Param("sn") String sn);
}