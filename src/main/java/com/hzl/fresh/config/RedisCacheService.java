package com.hzl.fresh.config;

import com.hzl.fresh.core.cache.CacheService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.concurrent.TimeUnit;
public class RedisCacheService implements CacheService<String,Object> {
    private RedisTemplate<String,Object> redisTemplate;
    private RedisSerializer keySerializer;
    private RedisSerializer valueSerializer;
    public RedisCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        valueSerializer = redisTemplate.getValueSerializer();
        keySerializer = redisTemplate.getKeySerializer();
    }

    @Override
    public boolean set(String key, Object value) {
        try {
           return redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {

                    return redisConnection.set(keySerializer.serialize(key),valueSerializer.serialize(value));
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean set(String key, Object value, long timeout) {
        try {
            return redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
               return redisConnection.set(keySerializer.serialize(key), valueSerializer.serialize(value), Expiration.from(timeout, TimeUnit.SECONDS), RedisStringCommands.SetOption.UPSERT);
            });
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object get(String key) {
        try {
            return redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
                byte[] value = redisConnection.get(keySerializer.serialize(key));
                return valueSerializer.deserialize(value);
            });
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remove(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
                redisConnection.del(keySerializer.serialize(key));
                return true;
            }));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
