package com.astronaut.scheduler.utils;

import com.astronaut.scheduler.exceptions.InvalidTimeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeUtils {
    private static final DateTimeFormatter TF = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalTime parseTime(String s) throws InvalidTimeException {
        try {
            return LocalTime.parse(s, TF);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException("Invalid time format: " + s + ". Expected HH:mm");
        }
    }
}
