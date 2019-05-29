package nju.edu.graduation;

import nju.edu.graduation.config.CustomWebSocket;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.IOException;

@SpringBootApplication
@MapperScan(value = "nju.edu.graduation.dao")
public class GraduationApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(GraduationApplication.class, args);
        CustomWebSocket.sendInfo("test");
    }

}
