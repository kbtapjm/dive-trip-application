package io.divetrip.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.divetrip.message.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        ObjectMapper mapper = new ObjectMapper();
        String data = StringUtils.EMPTY;
        try {
            data = mapper.writeValueAsString(notification);
        } catch (JsonProcessingException ex) {
            log.error("JsonProcessingException: {}", ex.getMessage(), ex);
        }

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, data);
        future.whenComplete((result, e) -> {
            if (!Objects.isNull(e)) {
                log.error("sendWithCallback error: {}", e.getMessage(), e);
            }

            log.debug("sendWithCallback result: {}", result.getRecordMetadata());
        });
    }

}
