package com.css.common.web.syscommon.service;

/**
 * 
 *TODO 缓存数据库redis接口，主用管理用户登录Token
 * @author huangaho
 *2015-4-29上午11:23:54
 */
public interface RedisService {
    public boolean add(String key,String value);
    public void delete(String key);
    public boolean update(String key,String value);
    public String get(String key);
}
