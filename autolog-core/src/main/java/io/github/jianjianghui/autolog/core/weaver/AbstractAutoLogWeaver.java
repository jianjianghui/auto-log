package io.github.jianjianghui.autolog.core.weaver;

import io.github.jianjianghui.autolog.core.AutoLogMatcher;
import io.github.jianjianghui.autolog.core.log.LogBuilder;
import io.github.jianjianghui.autolog.core.log.domain.LogAbnormal;
import io.github.jianjianghui.autolog.core.log.domain.LogEnd;
import io.github.jianjianghui.autolog.core.log.domain.LogStart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 抽象自动log 织入器
 *
 * @author 菅江晖
 * @date 2021/5/18 - 11:39
 */
public abstract class AbstractAutoLogWeaver implements AutoLogWeaver {
    private static final Logger log = LoggerFactory.getLogger(AbstractAutoLogWeaver.class);
    final private AutoLogMatcher autoLogMatcher;
    final private LogBuilder logBuilder;

    public AbstractAutoLogWeaver(AutoLogMatcher autoLogMatcher, LogBuilder logBuilder) {
        this.autoLogMatcher = autoLogMatcher;
        this.logBuilder = logBuilder;
    }


    @Override
    public void handleStart(Method method, Object[] args) {
        autoLogMatcher.match(method, () -> {
            LogStart logStart = LogStart.create(method, args);
            log.info(logBuilder.buildStartLog(logStart));
        });
    }

    @Override
    public void handleEnd(Method method, Object result) {
        autoLogMatcher.match(method, () -> {
            LogEnd logEnd = new LogEnd(method, result);
            log.info(logBuilder.buildEndLog(logEnd));
        });
    }

    @Override
    public void handleException(Method method, Exception exception) {
        autoLogMatcher.match(method, () -> {
            LogAbnormal logAbnormal = new LogAbnormal(method, exception);
            log.info(logBuilder.buildAbnormalLog(logAbnormal));
        });
    }
}
