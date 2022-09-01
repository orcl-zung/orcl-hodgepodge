package com.orcl.hodgepodge.distributedlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: orcl
 * @since: 2022-09-01 15:38
 * @history: 2022-09-01 15:38 created by orcl
 */
@SpringBootApplication
public class DistributedLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedLockApplication.class, args);
    }

}
