package com.testapp.ui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    public static String convert(String date)
    {
        SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMMM yyyy',' hh:mm:ss", Locale.getDefault());
        Date d = null;
        try {
            d = oldDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        return newDateFormat.format(d);
    }
}
