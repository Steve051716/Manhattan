package com.gyh.manhattan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author gao-yh
 * 开启缓存
 */
@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession
public class ManhattanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManhattanApplication.class, args);
    }

}
