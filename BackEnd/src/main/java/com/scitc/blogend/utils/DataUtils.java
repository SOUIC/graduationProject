package com.scitc.blogend.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static String getTime(String format) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat (format);
        return simpleDateFormat.format(date);
    }
}