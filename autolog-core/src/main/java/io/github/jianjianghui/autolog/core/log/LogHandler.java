package io.github.jianjianghui.autolog.core.log;

import java.util.StringJoiner;

/**
 * log handler
 *
 * @author 菅江晖
 * @date 2021/5/20 - 17:55
 */
public interface LogHandler {

    /**
     * 处理日志
     *
     * @param stringJoiner stringJoiner
     */
    void handle(final StringJoiner stringJoiner);
}
