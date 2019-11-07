package com.testapp.data.repository.converters;

import androidx.room.TypeConverter;

public class SecsToMills {

    public static Long secsToMills(Long secs)
    {
        return secs * 1000;
    }
}
