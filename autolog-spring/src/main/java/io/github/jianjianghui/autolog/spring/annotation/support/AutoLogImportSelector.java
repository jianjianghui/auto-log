package io.github.jianjianghui.autolog.spring.annotation.support;


import io.github.jianjianghui.autolog.core.AutoLogMatcher;
import io.github.jianjianghui.autolog.core.util.json.JSONUtil;
import io.github.jianjianghui.autolog.spring.annotation.EnableAutoLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 自动日志导入选择器
 *
 * @author 菅江晖
 * @date 2021/5/18 - 16:44
 * @see EnableAutoLog
 */
public class AutoLogImportSelector implements ImportBeanDefinitionRegistrar {
    private static final Logger log = LoggerFactory.getLogger(AutoLogImportSelector.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableAutoLog.class.getName());
        Optional<Map<String, Object>> annotationAttributesOptional = Optional.ofNullable(annotationAttributes);
        if (!annotationAttributesOptional.isPresent()) {
            return;
        }

        Object rest = annotationAttributes.getOrDefault("rest", null);
        String[] pathPackages = (String[]) annotationAttributes.getOrDefault("pathPackages", null);
        List<Class<?>> classes = ControllerScanner.scanController(pathPackages);
        log.trace("共扫描到{}个Controller->{}", classes.size(), JSONUtil.toJSONString(classes));
        //定义扫描bean
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(AutoLogMatcher.Matcher.class);
        beanDefinitionBuilder.addPropertyValue("rest", rest);
        beanDefinitionBuilder.addPropertyValue("packageClasses", classes);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(AutoLogMatcher.Matcher.class.getName(), beanDefinition);
    }
}
