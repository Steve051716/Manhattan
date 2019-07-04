package com.gyh.manhattan.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author gaoyuhang
 */
public class CommonUtil {
    /**
     * 将异常返回为字符串
     * @param e
     * @return
     */
    public static String getExceptionMessage(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            e.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
