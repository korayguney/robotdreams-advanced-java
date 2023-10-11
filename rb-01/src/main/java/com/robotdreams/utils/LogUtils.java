package com.robotdreams.utils;

public final class LogUtils {
    private static final String stars = "****************************** ";

    public static void logMethodStart(String methodName) {
        System.out.printf("\n%s %s %s\n", stars, methodName.toUpperCase(), stars);
    }

}
