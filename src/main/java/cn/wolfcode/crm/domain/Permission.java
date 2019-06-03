package cn.wolfcode.crm.domain;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Permission extends Basement{
    //private Long id;

    private String name;

    private String expression;

}