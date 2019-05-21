package com.gyh.manhattan.utils;

/**
 * @author 小龙哒 (https://blog.csdn.net/u014131617/article/details/88396730)
 */
public class IdUtil {
    /**
     * 随机id生成，使用雪花算法
     */
    public static Long getRandomId() {
        SnowflakeIdWorker sf = new SnowflakeIdWorker();
        long id = sf.nextId();
        return id;
    }

    /**
     *
     * @return
     */
    public static String getRandomIdToString() {
        return getRandomId().toString();
    }
}
