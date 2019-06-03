package cn.wolfcode.crm.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role extends Basement{
    //private Long id;

    private String name;

    private String sn;

    private List<Permission> permissions;
}