package io.github.jianjianghui.autolog.core.annotation;

import java.lang.annotation.*;

/**
 * 自动日志
 *
 * @author 菅江晖
 * @date 2021/5/20 - 17:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AutoLog {
    String value() default "";

    boolean ignore() default false;
}
