package nju.edu.graduation.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/druid/*",
    initParams = {
            @WebInitParam(name = "allow",value = "localhost"),
            @WebInitParam(name = "deny",value = ""),
            @WebInitParam(name = "loginUsername",value = "admin"),
            @WebInitParam(name = "loginPassword",value = "123"),
    }
)
public class DruidStatViewServlet extends StatViewServlet {
}