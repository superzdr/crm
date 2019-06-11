package cn.wolfcode.crm.query;

import cn.wolfcode.crm.domain.Customer;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Albert on 2019/6/9.
 */
@Getter@Setter
public class CustomerQueryObject extends QueryObject {
    private Integer status = Customer.STATUS_POTENTIAL;
    private Long sellerId = -1L;
    private String keyword;
}
