package cn.kevinwang.ratelimiter.value.impl;

import cn.hutool.json.JSONUtil;
import cn.kevinwang.ratelimiter.Constance;
import cn.kevinwang.ratelimiter.annotation.DoRateLimiter;
import cn.kevinwang.ratelimiter.value.IValueService;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author wang
 * @create 2024-01-18-16:40
 */
public class RateLimiterValue implements IValueService {
    @Override
    public Object access(ProceedingJoinPoint jp, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable {
        if(0D == doRateLimiter.permitsPerSecond()){
            return jp.proceed();
        }
        String className = jp.getTarget().getClass().getName();
        String methodName = method.getName();

        String key = className+ "_"+methodName;

        if(!Constance.rateLimiterMap.containsKey(key)){
            Constance.rateLimiterMap.put(key, RateLimiter.create(doRateLimiter.permitsPerSecond()));
        }
        RateLimiter rateLimiter = Constance.rateLimiterMap.get(key);
        // 如果还能够调用，放行
        if(rateLimiter.tryAcquire()){
            return jp.proceed();
        }
        return JSONUtil.toBean(doRateLimiter.returnJson(), method.getReturnType());
    }
}
