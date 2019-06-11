package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Albert on 2019/6/9.
 */
@Setter@Getter
public class CustomerTraceHistoryQueryObject extends QueryObject{
    private Long sellerId;
    private String keyword;
}
