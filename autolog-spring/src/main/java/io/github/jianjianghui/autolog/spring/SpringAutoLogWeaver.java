package io.github.jianjianghui.autolog.spring;

import io.github.jianjianghui.autolog.core.AutoLogMatcher;
import io.github.jianjianghui.autolog.core.log.LogBuilder;
import io.github.jianjianghui.autolog.core.weaver.AbstractAutoLogWeaver;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpEntity;

import java.lang.reflect.Method;

/**
 * 自动记录log 织入器
 *
 * @author 菅江晖
 * @date 2021/5/18 - 11:39
 */
@Aspect
public class SpringAutoLogWeaver extends AbstractAutoLogWeaver implements Ordered {
    private static final Logger log = LoggerFactory.getLogger(SpringAutoLogWeaver.class);

    public static int AUTO_LOG_ORDER = Integer.MAX_VALUE;

    public SpringAutoLogWeaver(AutoLogMatcher autoLogMatcher, LogBuilder logBuilder) {
        super(autoLogMatcher, logBuilder);
    }


    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void get1() {
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void post() {
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void put() {
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void delete() {
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void request() {
    }

    @Pointcut(value = "get1() || post() || put() || delete() || request()")
    public void all() {
    }


    @Before("all()")
    public void handleStart(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        this.handleStart(method, joinPoint.getArgs());
    }

    @AfterReturning(value = "all()", returning = "result")
    public void handleEnd(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        this.handleEnd(method, leachResult(result));
    }

    @AfterThrowing(value = "all()", throwing = "ex", argNames = "joinPoint,ex")
    public void handleException(JoinPoint joinPoint, Exception ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        this.handleException(method, ex);
    }


    @SuppressWarnings("all")
    private Object leachResult(Object result) {
        //issue: 用于支持 spring HttpEntity
        if (result instanceof HttpEntity) {
            return ((HttpEntity) result).getBody();
        }

        return result;
    }


    @Override
    public int getOrder() {
        return AUTO_LOG_ORDER;
    }
}
