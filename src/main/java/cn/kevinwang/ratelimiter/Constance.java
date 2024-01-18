package cn.kevinwang.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang
 * @create 2024-01-18-16:44
 */
public class Constance {
    public static final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();
}
