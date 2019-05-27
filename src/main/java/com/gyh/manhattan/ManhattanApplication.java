package com.gyh.manhattan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author gao-yh
 * 开启缓存
 */
@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession
@EnableSwagger2
public class ManhattanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManhattanApplication.class, args);
    }

}
