package io.github.jianjianghui.autolog.core.log.chain;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * AbstractChain
 *
 * <p>最基本的调用链内存逻辑</p>
 *
 * @author <a href="mailto:jianjianghui@foxmail.com" >Jianghui Jian</a>
 * @date 2021/7/8 - 15:33
 */
public class AbstractChain<O> implements HandlerChain<O> {
    private HandlerChain<O> next;
    private final Consumer<O> businessConsumer;

    public AbstractChain(Consumer<O> consumer) {
        this.businessConsumer = consumer;
    }

    @Override
    public HandlerChain<O> next(Consumer<O> consumer) {
        this.next = new AbstractChain<>(consumer);
        return this.next;
    }


    @Override
    public void accept(O o) {
        this.businessConsumer.accept(o);
        if (Objects.nonNull(next)) {
            this.next.accept(o);
        }
    }
}
