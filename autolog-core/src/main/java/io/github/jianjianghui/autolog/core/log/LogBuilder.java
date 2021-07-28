package io.github.jianjianghui.autolog.core.log;

import cn.hutool.core.text.CharSequenceUtil;
import io.github.jianjianghui.autolog.core.annotation.AutoLog;
import io.github.jianjianghui.autolog.core.log.config.LogProperties;
import io.github.jianjianghui.autolog.core.util.json.JSONUtil;
import io.github.jianjianghui.autolog.core.log.domain.LogEnd;
import io.github.jianjianghui.autolog.core.log.domain.LogStart;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.StringJoiner;


/**
 * 日志内容生成器
 *
 * @author 菅江晖
 * @date 2021/5/18 - 11:10
 */
public class LogBuilder {
    final private Object rest;
    final private LogProperties logProperties;
    final private CustomStartLogChain logStartChain;
    final private CustomEndLogChain logEndChain;

    public LogBuilder(Object rest, LogProperties logProperties, CustomLogFactory customLogFactory) {
        this.rest = rest;
        this.logProperties = logProperties;
        this.logStartChain = customLogFactory.logStartChain;
        this.logEndChain = customLogFactory.logEndChain;
    }


    public String buildStartLog(LogStart logStart) {
        Method method = logStart.getMethod();
        Object[] args = logStart.getArgs();
        StringJoiner joiner = new StringJoiner(logProperties.titleSeparator);
        joiner.add(logProperties.logStartHead);
        handleLogTopicTemplate(joiner, method);

        //custom
        logStartChain.accept(joiner);

        if (args.length == 0) {
            return joiner.toString();
        }

        if (args.length == 1) {
            joiner.add(logProperties.logStartArgs).add(JSONUtil.toJSONString(args[0]));
            return joiner.toString();
        }

        joiner.add(logProperties.logStartArgs);
        String[] methodParamNames = getMethodParamNames(method);
        StringJoiner multiParameterStringJoiner = multiParameterStringJoiner();
        for (int i = 0; i < methodParamNames.length; i++) {
            multiParameterStringJoiner.add(CharSequenceUtil.format(logProperties.logMultiParameterTemplate, methodParamNames[i], JSONUtil.toJSONString(args[i])));
        }

        joiner.add(multiParameterStringJoiner.toString());
        return joiner.toString();
    }

    public String buildEndLog(LogEnd logEnd) {
        StringJoiner joiner = new StringJoiner(logProperties.titleSeparator);
        Method method = logEnd.getMethod();
        Object result = logEnd.getResult();
        LogProperties.ResponseTag restLog = logProperties.responseTag;
        char[] msgChars = restLog.msgTag.toCharArray();
        msgChars[0] -= 32;
        char[] dataChars = restLog.dataTag.toCharArray();
        dataChars[0] -= 32;
        Object msg = null;
        Object data = null;
        try {
            Method msgMethod = result.getClass().getMethod("get" + String.valueOf(msgChars));
            msg = msgMethod.invoke(result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }

        try {
            Method dataMethod = result.getClass().getMethod("get" + String.valueOf(dataChars));
            data = dataMethod.invoke(result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }

        joiner.add(logProperties.logEndHead);
        handleLogTopicTemplate(joiner, method);

        //custom
        logEndChain.accept(joiner);

        joiner.add(CharSequenceUtil.format(logProperties.logOperationTemplate, msg));

        joiner.add(logProperties.logEndArgs).add(JSONUtil.toJSONString(data));


        return joiner.toString();
    }

    private void handleLogTopicTemplate(StringJoiner joiner, Method method) {
        if (Objects.isNull(getAutoLog(method)) || Objects.requireNonNull(getAutoLog(method)).value().isEmpty()) {
            joiner.add(CharSequenceUtil.format(logProperties.logTopicTemplate, method.getName()));
            return;
        }

        joiner.add(CharSequenceUtil.format(logProperties.logTopicTemplate, method.getAnnotation(AutoLog.class).value()));

    }

    private AutoLog getAutoLog(Method method) {
        return Objects.isNull(method.getAnnotation(AutoLog.class)) ? null : method.getAnnotation(AutoLog.class);
    }

    private StringJoiner multiParameterStringJoiner() {
        return new StringJoiner(logProperties.logMultipleParameterSeparator);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LogBuilder.class.getSimpleName() + "[", "]")
                .add("rest=" + rest)
                .add("logProperties=" + logProperties)
                .toString();
    }


    /**
     * 使用字节码工具ASM来获取方法的参数名
     */
    public static String[] getMethodParamNames(final Method method) {
        ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        return discoverer.getParameterNames(method);
    }


}


