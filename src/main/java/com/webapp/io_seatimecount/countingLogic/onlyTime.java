package com.webapp.io_seatimecount.countingLogic;

public class onlyTime {
    private static long allDays;

    public static long getAllDays() {
        return allDays;
    }

    public static void addDays(long daysToAdd) {
        onlyTime.allDays += daysToAdd;
    }

    public static void clearTheDays() {
        allDays = 0;
    }
}