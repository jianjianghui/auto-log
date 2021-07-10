package io.github.jianjianghui.autolog.core.log.domain;

import java.lang.reflect.Method;
import java.util.StringJoiner;

/**
 * 结束日志
 *
 * @author 菅江晖
 * @date 2021/5/19 - 15:58
 */
public class LogEnd {
    Method method;
    Object result;

    public void setMethod(Method method) {
        this.method = method;
    }


    public Method getMethod() {
        return method;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public LogEnd(Method method, Object result) {
        this.method = method;
        this.result = result;
    }

    public LogEnd() {
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LogEnd.class.getSimpleName() + "[", "]")
                .add("method=" + method)
                .add("result=" + result)
                .toString();
    }
}
