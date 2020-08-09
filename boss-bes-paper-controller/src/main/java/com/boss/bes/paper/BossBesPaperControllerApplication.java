package com.boss.bes.paper;

import com.boss.config.MySwagger;
import com.boss.config.RedisInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@RedisInit
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.boss.bes.paper.utils.*")
@MySwagger
@EnableTransactionManagement
@SpringBootApplication
public class BossBesPaperControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BossBesPaperControllerApplication.class, args);
    }

}
