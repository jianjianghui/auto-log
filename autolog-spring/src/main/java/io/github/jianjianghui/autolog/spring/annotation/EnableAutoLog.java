package io.github.jianjianghui.autolog.spring.annotation;


import io.github.jianjianghui.autolog.spring.annotation.support.AutoLogImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用自动日志
 *
 * @author 菅江晖
 * @date 2021/5/18 - 16:15
 * @see AutoLogImportSelector
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@Import(AutoLogImportSelector.class)
public @interface EnableAutoLog {
    Class<?> rest();

    String[] pathPackages() default {};


}
