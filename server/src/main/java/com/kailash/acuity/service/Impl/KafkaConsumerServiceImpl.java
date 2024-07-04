package com.kailash.acuity.service.impl;

import com.kailash.acuity.service.KafkaConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

  @Override
  @KafkaListener(topics = "test", groupId = "acuity")
  public void consumeMessage(String message) {
    System.out.println("Message consumed from Kafka: " + message);
  }
}
