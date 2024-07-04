package com.kailash.acuity.service.impl;

import com.kailash.acuity.service.KafkaProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void sendMessage(String topic, String message) {
    kafkaTemplate.send(topic, message);
  }
}
