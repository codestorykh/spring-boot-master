package com.rean.springbootmaster.utilities;

public class DateFormatUtils {

    private DateFormatUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_WITHOUT_SECOND = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_FORMAT_WITHOUT_HOUR = "yyyy-MM-dd mm:ss";
    public static final String DATE_TIME_FORMAT_WITHOUT_HOUR_MINUTE = "yyyy-MM-dd HH";
    public static final String DATE_TIME_FORMAT_WITHOUT_HOUR_SECOND = "yyyy-MM-dd mm";
    public static final String DATE_TIME_FORMAT_WITHOUT_MINUTE_SECOND = "yyyy-MM-dd HH";
    public static final String DATE_TIME_FORMAT_WITHOUT_DATE = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT_WITHOUT_DATE_SECOND = "HH:mm";
    public static final String DATE_TIME_FORMAT_WITHOUT_DATE_MINUTE = "HH:ss";
    public static final String DATE_TIME_FORMAT_WITHOUT_DATE_HOUR = "mm:ss";
    public static final String DATE_TIME_FORMAT_WITHOUT_DATE_HOUR_MINUTE = "mm";
    public static final String DATE_TIME_FORMAT_WITHOUT_DATE_HOUR_SECOND = "ss";

    public String getDateFormat() {
        return DATE_FORMAT;
    }

    public String getDateTimeFormat() {
        return DATE_TIME_FORMAT;
    }

}
