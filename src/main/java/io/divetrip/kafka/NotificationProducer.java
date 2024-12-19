package io.divetrip.kafka;

import io.divetrip.message.model.Notification;
import io.divetrip.util.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class NotificationProducer {

    private static final String TOPIC_NAME = "notification-topic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, String data) {
        kafkaTemplate.send(topic, data);
    }

    public void sendWithCallback(Notification notification) {
        String data = ObjectMapperUtils.writeValueAsString(notification);

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, data);
        future.whenComplete((result, e) -> {
            if (!Objects.isNull(e)) {
                log.error("sendWithCallback error: {}", e.getMessage(), e);
            }

            log.debug("####################################################################################################################");
            log.debug("sendWithCallback ProducerRecord: {}", result.getProducerRecord());
            log.debug("sendWithCallback RecordMetadata: {}", result.getRecordMetadata());
            log.debug("sendWithCallback RecordMetadata hasOffset: {}", result.getRecordMetadata().hasOffset());
            log.debug("sendWithCallback RecordMetadata offset: {}", result.getRecordMetadata().offset());
            log.debug("sendWithCallback RecordMetadata topic: {}", result.getRecordMetadata().topic());
            log.debug("sendWithCallback RecordMetadata hasTimestamp: {}", result.getRecordMetadata().hasTimestamp());
            log.debug("sendWithCallback RecordMetadata partition: {}", result.getRecordMetadata().partition());
            log.debug("sendWithCallback RecordMetadata serializedKeySize: {}", result.getRecordMetadata().serializedKeySize());
            log.debug("sendWithCallback RecordMetadata serializedValueSize: {}", result.getRecordMetadata().serializedValueSize());
            log.debug("sendWithCallback RecordMetadata timestamp: {}", result.getRecordMetadata().timestamp());
            log.debug("####################################################################################################################");
        });
    }

}
