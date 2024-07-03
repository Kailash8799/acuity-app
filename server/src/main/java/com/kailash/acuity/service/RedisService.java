package com.kailash.acuity.service;

public interface RedisService {
  void save(String key, String value);
  String get(String key);
}
