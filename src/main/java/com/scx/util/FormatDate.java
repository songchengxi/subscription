package com.scx.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 时间日期工具类
 */
public class FormatDate {

    public static String formatNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fDate;
        fDate = sdf.format(Calendar.getInstance().getTime());
        return fDate;
    }
}
