package cn.wolfcode.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Setter@Getter@ToString@NoArgsConstructor@AllArgsConstructor
public class SystemDictionary extends Basement{
    private String sn;

    private String title;

    private String intro;

    public String getJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",getId());
        map.put("sn",getSn());
        map.put("title",getTitle());
        map.put("intro",getIntro());
        return JSON.toJSONString(map);
    }
}