package com.lynx.fqb;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {
    public static Date of(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
