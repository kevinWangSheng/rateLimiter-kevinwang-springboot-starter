package cn.kevinwang.ratelimiter.aop;

import cn.kevinwang.ratelimiter.annotation.DoRateLimiter;
import cn.kevinwang.ratelimiter.value.IValueService;
import cn.kevinwang.ratelimiter.value.impl.RateLimiterValue;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wang
 * @create 2024-01-18-16:52
 */
@Aspect
@Component
public class DoRateLimiterAop {
    @Pointcut("@annotation(cn.kevinwang.ratelimiter.annotation.DoRateLimiter)")
    public void pointcut(){}

    @Around("pointcut() && @annotation(doRateLimiter)")
    public Object doLimiter(ProceedingJoinPoint jp, DoRateLimiter doRateLimiter) throws Throwable {
        IValueService valueService = new RateLimiterValue();
        return valueService.access(jp, getMethod(jp), doRateLimiter, jp.getArgs());
    }

    private Method getMethod(ProceedingJoinPoint jp) throws NoSuchMethodException {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(),methodSignature.getParameterTypes());
    }
}
