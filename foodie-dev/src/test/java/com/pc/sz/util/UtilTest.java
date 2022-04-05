package com.pc.sz.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilTest {
    @Test
    public void mdUtilTest() throws Exception {
        // System.out.println(MD5Utils.getMD5Str("renpengcheng"));
        SimpleDateFormat format = new SimpleDateFormat("YYYY_MM_dd");
        Date date = new Date();
        String format1 = format.format(date);
        System.out.println(format1);

        Calendar instance = Calendar.getInstance();
        System.out.println(instance.get(Calendar.YEAR));
        System.out.println(instance.get(Calendar.MONTH));
        System.out.println(instance.get(Calendar.DAY_OF_MONTH));
        System.out.println(instance.get(Calendar.DAY_OF_YEAR));
    }
}
