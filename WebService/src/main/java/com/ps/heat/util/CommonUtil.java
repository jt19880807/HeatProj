package com.ps.heat.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    /**
     * 格式化当前日期
     * @param pattern
     * @return
     */
    public static String formantCurrentDate(String pattern){
        SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
        return df.format(new Date());
    }
}
