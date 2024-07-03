package com.kailash.acuity.service.impl;

import com.kailash.acuity.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

  private RedisTemplate<String, Object> redisTemplate;

  public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void save(String key, String value) {
    redisTemplate.opsForValue().set(key, value);
  }

  public String get(String key) {
    return (String) redisTemplate.opsForValue().get(key);
  }
}
