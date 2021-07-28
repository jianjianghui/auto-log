package io.github.jianjianghui.autolog.demo;


import io.github.jianjianghui.autolog.core.annotation.AutoLog;
import io.github.jianjianghui.response.v1.Response;
import org.springframework.web.bind.annotation.*;


/**
 * demo
 *
 * @author 菅江晖
 * @date 2021/5/18 - 14:14
 */
@RestController
public class DemoController {


    @GetMapping("/demo01")
    public Response<String> demo01(@RequestParam(defaultValue = "菅江晖") String a) {
        return Rest.error("业务错误");
    }


    @PostMapping("/getUser")
    @AutoLog("获取用户")
    public Response<String> getUser(String token) {
        return Rest.success("请求成功", "菅江晖");
    }

    @GetMapping
    public Response<String> index(String lat, String lon) {
        return Rest.error();
    }

}
