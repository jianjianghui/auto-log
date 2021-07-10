package io.github.jianjianghui.autolog.springboot.autoconfigura;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jianjianghui.autolog.core.AutoLogMatcher;
import io.github.jianjianghui.autolog.core.log.CustomLogFactory;
import io.github.jianjianghui.autolog.core.log.LogBuilder;
import io.github.jianjianghui.autolog.core.log.config.LogProperties;
import io.github.jianjianghui.autolog.core.util.json.JsonMapper;
import io.github.jianjianghui.autolog.spring.SpringAutoLogWeaver;
import io.github.jianjianghui.autolog.spring.custom.SpringCustomLogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Objects;


/**
 * 自动配置
 *
 * @author 菅江晖
 * @date 2021/5/18 - 14:00
 */
@SpringBootConfiguration
@EnableConfigurationProperties(SpringLogProperties.class)
public class AutoLogAutoConfiguration {

    /**
     * @see io.github.jianjianghui.autolog.spring.annotation.support.AutoLogImportSelector
     */

    final AutoLogMatcher.Matcher matcher;


    final ObjectMapper objectMapper;

    public AutoLogAutoConfiguration(@Autowired(required = false) AutoLogMatcher.Matcher matcher, ObjectMapper objectMapper) {
        this.matcher = matcher;
        this.objectMapper = objectMapper;
    }

    @Bean
    @ConditionalOnBean(AutoLogMatcher.Matcher.class)
    public SpringAutoLogWeaver autoLogWeaver(SpringLogProperties springLogProperties) {
        new JsonMapper(objectMapper);
        LogProperties properties = convertLogProperties(springLogProperties);
        CustomLogFactory customLogFactory = new SpringCustomLogFactory(properties, springLogProperties.customLog);
        return new SpringAutoLogWeaver(new AutoLogMatcher(matcher),
                new LogBuilder(matcher.getRest(), properties, customLogFactory));
    }

    private LogProperties convertLogProperties(SpringLogProperties springLogProperties) {
        if (Objects.isNull(springLogProperties)) {
            springLogProperties = new SpringLogProperties();
        }

        LogProperties properties = new LogProperties();
        LogProperties.ResponseTag responseTag = new LogProperties.ResponseTag();
        BeanUtils.copyProperties(springLogProperties, properties);
        BeanUtils.copyProperties(springLogProperties.getResponseTag(), responseTag);
        properties.setResponseTag(responseTag);
        return properties;
    }


}
