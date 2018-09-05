package com.cnaidun;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Configuration：标注此文件为一个配置项
 * @EnableAutoConfiguration：使用自动配置
 * @ComponentScan：可扫描的
 * @SpringBootApplication 包含上面三个
 * Application：启动管理器
 * Created by sun on 2017-1-14.
 */

@SpringBootApplication
@ComponentScan({"com.cnaidun","com.cnaidun.messageclient"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}