package io.github.jianjianghui.autolog.core.log.chain;

import java.util.function.Consumer;

/**
 * chain
 *
 * @param <O> object
 * @author <a href="mailto:jianjianghui@foxmail.com" >Jianghui Jian</a>
 * @date 2021/7/8 - 14:40
 */
public interface HandlerChain<O> extends Consumer<O>, Chain<O> {


}
