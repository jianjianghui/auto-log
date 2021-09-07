package io.github.jianjianghui.autolog.core.weaver;

import java.lang.reflect.Method;

/**
 * 自动log 织入器
 *
 * @author <a href="mailto:jianjianghui@foxmail.com" >Jianghui Jian</a>
 * @date 2021/7/10 - 10:17
 */
public interface AutoLogWeaver {

    /**
     * 处理头日志
     *
     * @param method method
     * @param args   参数
     */
    void handleStart(Method method, Object[] args);

    /**
     * 处理尾日志
     *
     * @param method method
     * @param result 返回结果
     */
    void handleEnd(Method method, Object result);

    /**
     * 处理异常日志
     *
     * @param method    method
     * @param exception 异常
     */
    void handleException(Method method, Exception exception);
}
