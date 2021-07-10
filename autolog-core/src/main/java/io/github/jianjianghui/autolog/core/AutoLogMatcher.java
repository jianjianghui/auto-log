package io.github.jianjianghui.autolog.core;

import io.github.jianjianghui.autolog.core.annotation.AutoLog;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * 接口自动日志匹配器
 *
 * @author 菅江晖
 * @date 2021/5/18 - 15:38
 */
public class AutoLogMatcher {

    final Matcher matcher;

    public AutoLogMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    private boolean match0(Method method) {
        Class<?> returnType = method.getReturnType();
        boolean hitReturnType = returnType == matcher.getRest();
        if (!hitReturnType) {
            return false;
        }

        if(Optional.ofNullable(method.getAnnotation(AutoLog.class)).isPresent() && method.getAnnotation(AutoLog.class).ignore()) {
            return false;
        }

        Class<?> declaringClass = method.getDeclaringClass();
        return matcher.packageClasses.contains(declaringClass);
    }

    public void match(Method method, Runnable runnable) {
        if (match0(method)) {
            runnable.run();
        }
    }

    public static class Matcher {
        Class<?> rest;
        List<Class<?>> packageClasses;

        public Class<?> getRest() {
            return rest;
        }

        public void setRest(Class<?> rest) {
            this.rest = rest;
        }

        public List<Class<?>> getPackageClasses() {
            return packageClasses;
        }

        public void setPackageClasses(List<Class<?>> packageClasses) {
            this.packageClasses = packageClasses;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Matcher.class.getSimpleName() + "[", "]")
                    .add("rest=" + rest)
                    .add("packageClasses=" + packageClasses)
                    .toString();
        }
    }

}
