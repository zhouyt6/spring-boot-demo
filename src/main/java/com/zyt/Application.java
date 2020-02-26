package com.zyt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zhouyt
 * @Date: 2019/12/25
 * @Description:
 */
@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("--------------------");

        logger.info("Application start ...");

//		System.exit(0);
    }
}
