package com.kailash.acuity.service;

public interface KafkaProducerService {
  void sendMessage(String topic, String message);
}
