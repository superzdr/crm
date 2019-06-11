package cn.wolfcode.crm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date) {
        return format.format(date);
    }

    public static Date getEndDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        return c.getTime();
    }
}
