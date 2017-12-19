package com.css.common.web.syscommon.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.css.common.web.syscommon.service.AbstractBaseRedisDao;
import com.css.common.web.syscommon.service.RedisService;

/**
 * 
 *TODO 缓存数据库redis接口，主用管理用户登录Token
 * @author huangaho
 *2015-4-29上午11:25:17
 */
@Service("redisServiceImpl")
public class RedisServiceImpl extends AbstractBaseRedisDao<String,String> implements RedisService{
    @Override
    public boolean add(final String token, final String value) {
        if(token==null || value==null || token.equals("") || value.equals("")) return false;
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] key  = serializer.serialize(token);
            byte[] name = serializer.serialize(value);
            return redisConnection.setNX(key,name);
            }
        });
        return result;
    }

    @Override
    public void delete(final String key) {
//        if (key==null || key.equals("")) return;
//        List<String> list = new ArrayList<String>();
//        list.add(key);
//        redisTemplate.delete(list);
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) {
                connection.del(redisTemplate.getStringSerializer().serialize(key));
                return null;
            }
        });
    }

    @Override
    public boolean update(String key, String value) {
        return false;
    }

    @Override
    public String get(final String token) {
        if (token==null || token.equals("")) return null;
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = redisConnection.get(serializer.serialize(token));
                if (value == null) {
                    return null;
                }
                String name = serializer.deserialize(value);
                return name;
            }
        });
        return result;
    }
}
