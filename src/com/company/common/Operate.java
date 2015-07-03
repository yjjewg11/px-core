package com.company.common;

import org.apache.log4j.Level;

/**
 * 自定义LOG4j级别LIFE
 *
 * Created by CrazyHarry on 2015/3/27.
 */
public class Operate extends Level {
    private static final long serialVersionUID = 1L;
 
    protected Operate(int level, String levelStr, int syslogEquivalent) {
        super(level, levelStr, syslogEquivalent);
    }
 
    /**
     * 定义log的权重为介于OFF和FATAL之间，便于打印LIFE级别的log
     */
    public static final int OPERATE_INT = OFF_INT - 10;
 
    public static final Level OPERATE = new Operate(OPERATE_INT, "OPERATE", 10);
 
    public static Level toLevel(String logArgument) {
        if (logArgument != null && logArgument.toUpperCase().equals("OPERATE")) {
            return OPERATE;
        }
        return (Level) toLevel(logArgument);
    }
 
    public static Level toLevel(int val) {
        if (val == OPERATE_INT) {
            return OPERATE;
        }
        return (Level) toLevel(val, Level.DEBUG);
    }
 
    public static Level toLevel(int val, Level defaultLevel) {
        if (val == OPERATE_INT) {
            return OPERATE;
        }
        return Level.toLevel(val, defaultLevel);
    }
 
    public static Level toLevel(String logArgument, Level defaultLevel) {
        if (logArgument != null && logArgument.toUpperCase().equals("OPERATE")) {
            return OPERATE;
        }
        return Level.toLevel(logArgument, defaultLevel);
    }
}