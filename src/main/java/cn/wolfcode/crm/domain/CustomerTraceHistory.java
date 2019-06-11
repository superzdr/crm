package cn.wolfcode.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter@Setter
public class CustomerTraceHistory extends Basement{
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date traceTime;

    private String traceDetails;

    private SystemDictionaryItem type; //跟进方式

    private Integer traceResult;

    private String remark;

    private Customer customer;

    private Employee inputUser;

    private Date inputTime;

    public String getTraceResultName(){
        String resultName = "";
        if(traceResult == 1){
            resultName = "差";
        }else if(traceResult == 2){
            resultName = "中";
        }else{
            resultName = "优";
        }
        return resultName;
    }

    public String getJson(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("customerName",customer.getName());
        map.put("traceTime",sdf.format(traceTime));
        map.put("traceDetails",traceDetails);
        map.put("typeId",type.getId());
        map.put("traceResult",traceResult);
        map.put("remark",remark);
        return JSON.toJSONString(map);
    }

}