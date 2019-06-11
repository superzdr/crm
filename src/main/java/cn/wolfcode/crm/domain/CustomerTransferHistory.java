package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter@Setter
public class CustomerTransferHistory extends Basement{
    //private Long id;

    private Customer customer;

    private Employee operator;

    private Date operateTime;

    private Employee oldseller;

    private Employee newseller;

    private String reason;

}