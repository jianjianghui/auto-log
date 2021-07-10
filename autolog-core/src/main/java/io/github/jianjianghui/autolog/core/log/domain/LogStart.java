package io.github.jianjianghui.autolog.core.log.domain;


import java.lang.reflect.Method;

/**
 * LogStart
 *
 * @author 菅江晖
 * @date 2021/5/19 - 15:58
 */
public class LogStart {
    Method method;
    Object[] args;

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }

    public LogStart(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }

    public LogStart() {
    }

    public static LogStart create(Method method, Object[] args) {
        return new LogStart(method, args);
    }


//    public static LogStart parseLogStart(JoinPoint joinPoint) {
//        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
//        Object[] args = joinPoint.getArgs();
//        return new LogStart(method,args);
//    }
}
