package io.github.jianjianghui.autolog.core.log;

import io.github.jianjianghui.autolog.core.log.chain.Chain;
import io.github.jianjianghui.autolog.core.log.config.LogProperties;

import java.util.StringJoiner;

/**
 * log模板生成 工厂
 *
 * @author 菅江晖
 * @date 2021/5/20 - 18:02
 */
public class CustomLogFactory {
    protected final CustomStartLogChain logStartChain;
    protected final CustomEndLogChain logEndChain;
    protected final LogProperties logProperties;

    protected CustomLogFactory(LogProperties logProperties) {
        this.logStartChain = new CustomStartLogChain(stringJoiner -> nothing());
        this.logEndChain = new CustomEndLogChain(stringJoiner -> nothing());
        this.logProperties = logProperties;
    }

    public Chain<StringJoiner> getLogStartChain() {
        return logStartChain;
    }

    public Chain<StringJoiner> getLogEndChain() {
        return logEndChain;
    }

    public void nothing() {
    }


    private static class Holder {
        private static final CustomLogFactory INSTANCE = new CustomLogFactory(new LogProperties());
    }

    public static CustomLogFactory getInstance() {
        return Holder.INSTANCE;
    }

    public static CustomLogFactory create(LogProperties logProperties) {
        return new CustomLogFactory(logProperties);
    }

}
