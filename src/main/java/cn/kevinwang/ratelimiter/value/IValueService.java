package cn.kevinwang.ratelimiter.value;

import cn.kevinwang.ratelimiter.annotation.DoRateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author wang
 * @create 2024-01-18-16:39
 */
public interface IValueService {
    Object access(ProceedingJoinPoint jp, Method method, DoRateLimiter doRateLimiter,Object[] args) throws Throwable;
}
