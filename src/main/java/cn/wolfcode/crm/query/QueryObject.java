package cn.wolfcode.crm.query;

import lombok.*;

/**
 * Created by Albert on 2019/5/27.
 */
@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 5;

    public int getStart(){
        return (currentPage - 1) * pageSize;
    }
}
