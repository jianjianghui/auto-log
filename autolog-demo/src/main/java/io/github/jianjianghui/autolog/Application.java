package io.github.jianjianghui.autolog;

import io.github.jianjianghui.autolog.spring.annotation.EnableAutoLog;
import io.github.jianjianghui.response.v1.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 菅江晖
 * @date 2021/5/18 - 14:18
 */
@SpringBootApplication
@EnableAutoLog(rest = Response.class, pathPackages = "io.github.jianjianghui.**")
public class Application {
    public static void main(String[] args) {

        //扫描包
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
    }
}
