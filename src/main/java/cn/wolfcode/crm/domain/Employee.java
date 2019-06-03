package cn.wolfcode.crm.domain;

import lombok.*;

import java.util.List;

@ToString@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class Employee extends Basement{
    //private Long id;

    private String name;

    private String password;

    private String email;

    private Integer age;

    private boolean admin = false;

    private Long deptId;

    private Department dept;

    private List<Role> roles;
}