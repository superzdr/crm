package cn.wolfcode.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer extends Basement{

    private String name;

    private Integer age;

    private Integer gender;

    private String tel;

    private String qq;
    //职业
    private SystemDictionaryItem job;
    //来源
    private SystemDictionaryItem source;
    //营销人员
    private Employee seller;
    //录入人员
    private Employee inputUser;

    private Date inputTime;
    //为每个数字命名
    public static final Integer STATUS_POTENTIAL = 0;
    public static final Integer STATUS_NORMAL = 1;
    public static final Integer STATUS_POOL = 2;
    public static final Integer STATUS_FAILED = 3;
    public static final Integer STATUS_LOSE = 4;

    private Integer status = STATUS_POTENTIAL;

    public String getJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("age",age);
        map.put("gender",gender);
        map.put("tel",tel);
        map.put("qq",qq);
        map.put("jobId",job.getId());
        map.put("sourceId",source.getId());
        map.put("sellerId",seller.getId());
        map.put("sellerName",seller.getName());

        return JSON.toJSONString(map);
    }


}