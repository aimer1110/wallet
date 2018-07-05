package com.longchain.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtils {
    public static Timestamp nowT(){
        return new Timestamp(System.currentTimeMillis());
    }
}
