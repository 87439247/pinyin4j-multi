package net.sourceforge.pinyin4j.multipinyin;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.logging.Level;

/**
 * Created by 刘一波 on 16/7/28.
 * E-Mail:yibo.liu@tqmall.com
 */
public final class PinyinLogger {
    public static Logger logger;
    public static ESLogger logger1;
    public static java.util.logging.Logger logger2;

    static {
        try {
            logger = LoggerFactory.getLogger("pinyin4j-multi");
        } catch (Throwable e) {
            try {
                System.out.println("slf4j不能用,使用ESLogger");
                logger1 = Loggers.getLogger("pinyin4j-multi");
            } catch (Throwable e1) {
                System.out.println("ESLogger不能用,使用jdk log");
                logger2 = java.util.logging.Logger.getLogger("pinyin4j-multi");
            }
        }
    }

    /**
     * Logs an INFO level message.
     */
    public static void info(String msg, Object... params) {
        if (logger != null) {
            logger.info(msg, params);
        } else if (logger1 != null) {
            logger1.info(msg, params);
        } else if (logger2 != null) {
            logger2.log(Level.INFO, msg, params);
        } else {
            System.out.println(msg + " " + Arrays.toString(params));
        }
    }

    /**
     * Logs an ERROR level message.
     */
    public static void error(String msg, Object... params) {
        if (logger != null) {
            logger.error(msg, params);
        } else if (logger1 != null) {
            logger1.error(msg, params);
        } else if (logger2 != null) {
            logger2.log(Level.SEVERE, msg, params);
        } else {
            System.err.println(msg + " " + Arrays.toString(params));
        }
    }

    /**
     * Logs an ERROR level message.
     */
    public static void error(String msg, Throwable cause, Object... params) {
        if (logger != null) {
            logger.error(msg, cause, params);
        } else if (logger1 != null) {
            logger1.error(msg, cause, params);
        } else if (logger2 != null) {
            logger2.log(Level.SEVERE, msg, cause);
        } else {
            System.err.println(msg + " " + Arrays.toString(params));
        }
    }
}
