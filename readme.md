# 限流组件
使用aop+google的rateLimiter进行实现

aop进行注解拦截，之后获取注解的信息，并对RateLimiter进行注册和配置，如果能够抢到就执行，否则就限流，类似于抢占锁的概念

使用方法：

```java
@GetMapping("hello")
@DoRateLimiter(permitsPerSecond = 1,returnJson = "{\"code\":\"1111\",\"desc\":\"调用方法超过最大次数，限流返回！！\"}")
public Result sayHello(){
Result result = new Result();
result.setCode("0000");
result.setDesc("调用成功");
return result;
}
```
permitsPerSecond是最多能够执行的次数，是同时执行，不是执行一次就减少一次。 returnJson是限流以后默认的返回值。json格式。