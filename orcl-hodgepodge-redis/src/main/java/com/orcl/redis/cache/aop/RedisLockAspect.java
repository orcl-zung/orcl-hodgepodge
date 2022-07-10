package com.orcl.redis.cache.aop;

import com.orcl.redis.cache.anno.RedisLock;
import com.orcl.redis.exception.CustomException;
import com.orcl.redis.util.ElUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;

/**
 * @description:
 * @author: orcl
 * @since: 2022/4/25-16:49
 * @history: 2022/4/25 created by orcl
 */
@Log4j2
@Aspect
@Component
@RequiredArgsConstructor
public class RedisLockAspect {

    private final RedisLockRegistry redisLockRegistry;

    @Around("@annotation(redisLock)")
    public Object saveLogAround(ProceedingJoinPoint joinPoint, RedisLock redisLock) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();

        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();

        String lockValue = redisLock.value();
        String lockKey = redisLock.key();

        String elValue = ElUtil.analysisByMethod(method, args, lockKey);
        String redisLockKey = lockValue + elValue;

        Lock lock = redisLockRegistry.obtain(redisLockKey);

        if (!lock.tryLock()) {
            log.error("RedisLock获取锁失败, LockKey:[ {} ]", redisLockKey);
            throw new CustomException("网络繁忙, 请稍后再试!", 500);
        }

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (CustomException bizE) {
            bizE.printStackTrace();
            log.error("捕捉到业务异常: 异常信息: [ {} ] - 异常编码: [ {} ]", bizE.getMessage(), bizE.getCode());
            throw bizE;
        } catch (Throwable e) {
            e.printStackTrace();;
            log.error("执行CLASS:[{}] - 方法名:[{}] -> 执行失败!", className, method.getName());
            log.error("失败原因:{}", e.getMessage(), e);
            throw new CustomException(e.getMessage(), 500);
        } finally {
            try {
                lock.unlock();
            } catch (Exception e) {
                log.error("RedisLock释放锁失败,异常信息:{}", e.getMessage(), e);
            }
        }
        return result;
    }

}
