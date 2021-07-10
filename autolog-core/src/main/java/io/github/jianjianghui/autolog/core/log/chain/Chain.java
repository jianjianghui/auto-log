package io.github.jianjianghui.autolog.core.log.chain;

import java.util.function.Consumer;

/**
 * Chain
 *
 * @author <a href="mailto:jianjianghui@foxmail.com" >Jianghui Jian</a>
 * @date 2021/7/10 - 14:05
 */
public interface Chain<O> {
    /**
     * next
     *
     * @param consumer {@link Consumer <O>}
     * @return {@link HandlerChain <O>}
     */
    Chain<O> next(Consumer<O> consumer);
}
