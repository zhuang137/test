package utils.helper;

import android.util.Log;

import utils.helper.constant.Features;

import static utils.helper.constant.Features.TAG_PREFIX;

public class Slog {
    public static void printLog(String tag, Exception e, String format, Object... args) {
        printLog(Log.DEBUG, tag, e, format, args);
    }
    public static void printLog(String tag, String format, Object... args) {
        printLog(Log.DEBUG, tag, format, args);
    }

    public static void printLog(int level, String tag, Exception e, String format, Object... args) {
        tag = TAG_PREFIX + tag;
        if (Log.isLoggable(tag, level)) {
            e.printStackTrace();
            Log.println(level, tag, String.format(format, args));
        }
    }
    public static void printLog(int level, String tag, String format, Object... args) {
        tag = TAG_PREFIX + tag;
        if (Log.isLoggable(tag, level)) {
            Log.println(level, tag, String.format(format, args));
        }
    }
}
