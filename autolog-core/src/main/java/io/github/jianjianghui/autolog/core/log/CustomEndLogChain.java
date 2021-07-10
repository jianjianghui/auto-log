package io.github.jianjianghui.autolog.core.log;

import io.github.jianjianghui.autolog.core.log.chain.AbstractChain;

import java.util.StringJoiner;
import java.util.function.Consumer;

/**
 * 自定义开始日志调用链
 *
 * @author 菅江晖
 * @date 2021/5/20 - 17:55
 */
public class CustomEndLogChain extends AbstractChain<StringJoiner> {


    public CustomEndLogChain(Consumer<StringJoiner> consumer) {
        super(consumer);
    }

}
