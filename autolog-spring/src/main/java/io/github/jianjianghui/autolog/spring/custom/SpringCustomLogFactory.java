package io.github.jianjianghui.autolog.spring.custom;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.crypto.digest.MD5;
import io.github.jianjianghui.autolog.core.log.CustomLogFactory;
import io.github.jianjianghui.autolog.core.log.chain.Chain;
import io.github.jianjianghui.autolog.core.log.config.LogProperties;
import io.github.jianjianghui.autolog.spring.custom.config.SpringCustomLogProperties;
import io.github.jianjianghui.autolog.spring.custom.util.HttpRequest;

import java.util.StringJoiner;

/**
 * SpringCustomLogFactory
 *
 * @author <a href="mailto:jianjianghui@foxmail.com" >Jianghui Jian</a>
 * @date 2021/7/10 - 12:31
 */
public class SpringCustomLogFactory extends CustomLogFactory {
    final private SpringCustomLogProperties properties;

    public SpringCustomLogFactory(LogProperties logProperties, SpringCustomLogProperties properties) {
        super(logProperties);
        this.properties = properties;
        Chain<StringJoiner> logStartChain = super.getLogStartChain();
        Chain<StringJoiner> logEndChain = super.getLogEndChain();

        if (properties.isEnableIp()) {
            logStartChain = addIp(logStartChain);
            logEndChain = addIp(logEndChain);
        }

        if (properties.isEnableUniqueCode()) {
            logStartChain = addUniqueCode(logStartChain);
            logEndChain = addUniqueCode(logEndChain);
        }
    }

    private Chain<StringJoiner> addIp(Chain<StringJoiner> chain) {

        return chain.next(stringJoiner -> stringJoiner.add(CharSequenceUtil.format(logProperties.logOperationTemplate,
                properties.getIpTag() + HttpRequest.currentRequest().ip())));
    }

    private Chain<StringJoiner> addUniqueCode(Chain<StringJoiner> chain) {
        return chain.next(stringJoiner ->
                stringJoiner.add(CharSequenceUtil.format(logProperties.logOperationTemplate,
                        properties.getUniqueCodeTag() + MD5.create().digestHex(HttpRequest.currentRequest().sourceRequest().getHttpServletMapping().toString()))));
    }
}
