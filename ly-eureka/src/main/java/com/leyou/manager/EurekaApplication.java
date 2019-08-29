package com.leyou.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @version : 1.0
 * @ClassName: EurekaApplication
 * @Description : 注册中心启动类
 * @auther: hejia
 * @date: 2019/3/28
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    /**
     * @author hejia
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
