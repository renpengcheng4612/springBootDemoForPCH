package com.chinaclear.sz.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(RedisTemplate.class)
public class CacheUtils {

    // RedisTemplate 模板

    private RedisTemplate<String,Object>  redisTemplate;

    public CacheUtils(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate=redisTemplate;
    }

}
