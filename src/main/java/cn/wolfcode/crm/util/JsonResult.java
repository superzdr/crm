package cn.wolfcode.crm.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Albert on 2019/6/1.
 */
@Getter@Setter
public class JsonResult {
    private boolean success = true;
    private String errorMsg;
    public void mark(String errorMsg){
        this.success = false;
        this.errorMsg = errorMsg;
    }
}
