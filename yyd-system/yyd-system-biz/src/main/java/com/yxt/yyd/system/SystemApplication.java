package com.yxt.yyd.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author dimengzhe
 * @date 2020/9/11 14:44
 * @description
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.yxt.yyd.common.base.redis",
        "com.yxt.yyd.common.base.config",
        "com.yxt.yyd.system"
})
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
