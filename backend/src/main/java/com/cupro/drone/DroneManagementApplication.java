package com.cupro.drone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cupro.drone.repository")
public class DroneManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneManagementApplication.class, args);
    }
}
