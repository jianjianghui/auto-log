package io.github.jianjianghui.autolog.demo;

import io.github.jianjianghui.response.v1.Response;

/**
 * rest
 *
 * @author <a href="mailto:jianjianghui@foxmail.com" >Jianghui Jian</a>
 * @date 2021/7/10 - 16:00
 */
public class Rest<T> extends Response<T> {
    public Rest(int code, String msg, T data) {
        super(code, msg, data);
    }


}
