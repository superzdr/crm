package cn.wolfcode.crm.domain;

import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class SystemDictionaryItem extends Basement{

    private Long parentId;

    private String title;

    private Integer sequence;
}