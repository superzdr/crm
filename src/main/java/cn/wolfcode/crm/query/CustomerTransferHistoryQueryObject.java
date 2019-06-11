package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Albert on 2019/6/9.
 */
@Setter@Getter
public class CustomerTransferHistoryQueryObject extends QueryObject{
    private Long sellerId = -1L;
    private String keyword;
}
