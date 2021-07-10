package io.github.jianjianghui.autolog.spring;

import io.github.jianjianghui.autolog.core.AutoLogMatcher;
import io.github.jianjianghui.autolog.core.weaver.AbstractAutoLogWeaver;
import io.github.jianjianghui.autolog.core.log.LogBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 自动记录log 织入器
 *
 * @author 菅江晖
 * @date 2021/5/18 - 11:39
 */
@Aspect
public class SpringAutoLogWeaver extends AbstractAutoLogWeaver {
    private static final Logger log = LoggerFactory.getLogger(SpringAutoLogWeaver.class);

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
    public void all() {
    }

    @Before("get1() || post() || put() || delete() || all()")
    public void handleStart(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        this.handleStart(method, joinPoint.getArgs());
    }

    @AfterReturning(value = "get1() || post() || put() || delete() || all()", returning = "result")
    public void handleEnd(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        this.handleEnd(method, result);
    }


}
