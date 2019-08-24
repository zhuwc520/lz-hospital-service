package cn.lz.hospital.utils;


import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
* @Description:    redis工具集，只列举使用到的，需要用到的可以自行添加
* @Author:         zhuwc
* @CreateDate:     2018/12/20 13:40
* @Version:        1.0
*/
public class RedisUtils
{

    @Resource
    private RedisTemplate redisTemplate;

    public static final long THREE_MONTHS = 60L * 60 * 24 * 30 * 3;
    public static final long ONE_DAY = 24L * 60 * 60;


    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key)
    {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final String key)
    {
        String result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Object obj = operations.get(key);
        if (obj != null)
        {
            result = obj.toString();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value)
    {
        boolean result = false;
        try
        {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long seconds)
    {
        boolean result = false;
        try
        {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            result = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     * 
     * @param key
     * @return
     */
    public void del(final String key)
    {
        redisTemplate.delete(key);
    }

    /**
     * 按1递减
     * 
     * @param key
     * @return
     */
    public Long decr(String key)
    {
        return redisTemplate.opsForValue().increment(key, -1);
    }

    /**
     * 按1递增
     * 
     * @param key
     * @return
     */
    public Long incr(String key)
    {
        return redisTemplate.opsForValue().increment(key, 1);

    }

    /**
     * 写入hash值
     * 
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, String value)
    {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public void hsetAll(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }


    /**
     * 获取hash值
     * 
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field)
    {
        HashOperations<String, Serializable, Object> hashOperations = redisTemplate.opsForHash();

        String result = null;
        Object obj = hashOperations.get(key, field);
        if (obj != null)
        {
            result = obj.toString();
        }

        return result;
    }

    public <T> List<T> hgetAll(String key, Class<T> clazz)
    {
        HashOperations<String, Serializable, String> hashOperations = redisTemplate.opsForHash();

        List<String> listRedis = hashOperations.values(key);

        List<T> list = new ArrayList<>();

        for (String str :
                listRedis) {
            list.add(JSON.parseObject(str, clazz));
        }
        return list;
    }

    /**
     * 删除hash值
     *
     * @param key
     * @param field
     * @return
     */
    public Long hdel(String key, String ... field)
    {
        return redisTemplate.opsForHash().delete(key, field);
    }

    public Long hlen(String key)
    {
        return redisTemplate.opsForHash().size(key);
    }

    public Set<String> hkeys(String key)
    {
        return redisTemplate.opsForHash().keys(key);
    }

    public List<String> hvals(String key)
    {
        return redisTemplate.opsForHash().values(key);
    }

    public boolean hexists(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }
}
