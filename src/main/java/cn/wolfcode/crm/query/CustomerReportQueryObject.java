package cn.wolfcode.crm.query;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.util.DateUtil;
import com.mysql.jdbc.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Albert on 2019/6/9.
 */
@Getter@Setter
public class CustomerReportQueryObject extends QueryObject {
    private String keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String groupType = "seller.name";

    public String getKeyword(){
        return StringUtils.isNullOrEmpty(keyword) ? null:keyword;
    }
    //获取当前天的最后一秒的时间
    public Date getEndTime(){
        return DateUtil.getEndDate(endTime);
    }

    public String getGroupTypeName(){
        String name = "";
        if("seller.name".equals(groupType)){
            name = "营销人员";
        }else if("DATE_FORMAT(c.input_time, '%Y')".equals(groupType)){
            name = "年";
        }else if("DATE_FORMAT(c.input_time, '%Y-%m')".equals(groupType)){
            name = "月";
        }else{
            name = "日";
        }

        return name;
    }

}
