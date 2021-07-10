package io.github.jianjianghui.autolog.spring.annotation.support;


import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 接口扫描
 *
 * @author 菅江晖
 * @date 2021/5/19 - 9:33
 */
public class ControllerScanner {

    public static List<Class<?>> scanController(String... basePackages) {
        List<Class<?>> classes = new LinkedList<>();
        // 扫描指定注解的组件
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
        Arrays.stream(basePackages).map(provider::findCandidateComponents).forEach(beanDefinitions -> beanDefinitions.forEach(beanDefinition -> {
            try {
                classes.add(Class.forName(beanDefinition.getBeanClassName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }));

        return classes;
    }
}
