package cn.kevinwang.ratelimiter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wang
 * @create 2024-01-18-16:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoRateLimiter {
    double permitsPerSecond() default 0D; // 限流许可量

    String returnJson() default ""; // 限流以后默认的返回值
}
