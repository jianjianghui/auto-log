package io.github.jianjianghui.autolog.core.log.domain;

import java.lang.reflect.Method;
import java.util.StringJoiner;

/**
 * 异常日志
 *
 * @author 菅江晖
 * @date 2021/5/19 - 15:58
 */
public class LogAbnormal {
    Method method;
    Exception exception;

    public LogAbnormal() {
    }

    public LogAbnormal(Method method, Exception exception) {
        this.method = method;
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LogAbnormal.class.getSimpleName() + "[", "]")
                .add("method=" + method)
                .add("exception=" + exception)
                .toString();
    }
}
