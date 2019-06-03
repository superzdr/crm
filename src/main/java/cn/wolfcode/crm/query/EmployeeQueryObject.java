package cn.wolfcode.crm.query;

import com.mysql.jdbc.StringUtils;
import lombok.*;

/**
 * Created by Albert on 2019/5/27.
 */
@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class EmployeeQueryObject extends QueryObject {
    private String keyword;
    private Long deptId = -1L;

    public String getKeyword(){
        if(StringUtils.isNullOrEmpty(keyword)){ //使用mysql的工具包StringUtils的方法对字符串进行处理
            return null;
        }else {
            return keyword;
        }
    }
}
