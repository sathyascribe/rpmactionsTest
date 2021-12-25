package com.inventica.rpmapp.ui.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeString {

    public static SimpleDateFormat format = new SimpleDateFormat(
            "MM-dd-yyyy", Locale.getDefault());
    public static SimpleDateFormat timeFormat = new SimpleDateFormat(
            "hh:mm aa", Locale.getDefault());

    public static String getDateToString(Date date) {
        return format.format(date);
    }

    public static Date getStringToDate(String givenDate) throws ParseException {
        return format.parse(givenDate);
    }

    public static Date getStringToTime(String givenTime) throws ParseException {
        return timeFormat.parse(givenTime);
    }

    public static String getTimeToString(Date date) {
        return timeFormat.format(date);
    }

    public static String secToString(int sec) {
        int minTime = (int) sec / (60000);
        int secTime = (int) sec % 60000 / 1000;

        String timeLeft = " " + minTime;
        timeLeft += ":";
        if (secTime < 10)
            timeLeft += "0";

        timeLeft += secTime;
        return timeLeft;

    }
}
