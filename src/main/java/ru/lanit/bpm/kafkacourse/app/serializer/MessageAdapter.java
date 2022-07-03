package ru.lanit.bpm.kafkacourse.app.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class MessageAdapter<T> implements Serializer<T>, Deserializer<T> {

    private ObjectMapper objectMapper;
    private Class<T> clazz;

    private static final String VALUE_TYPE = "value-class";
    private static final String KEY_TYPE = "key-class";
    private static final String SERIALIZATION_MESSAGE_ERROR = "Serialization class not found";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        log.info("MessageAdapter configuration");
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        configureClass(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, T t) {
        try {
            return objectMapper.writeValueAsString(t).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            log.info("Error while serializing object");
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, T data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        try {
            return (T) objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), clazz);
        } catch (Exception e) {
            log.error("Can't deserialize object");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }

    private void configureClass(Map<String, ?> configs, boolean isKey) {
        String className = getClassName(configs, isKey);
        try {
            clazz = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("Can't to execute Class.forName");
            e.printStackTrace();
        }
    }

    private String getClassName(Map<String, ?> configs, boolean isKey) {
        String type = isKey ? KEY_TYPE : VALUE_TYPE;
        if (!configs.containsKey(type)) {
            log.error(SERIALIZATION_MESSAGE_ERROR);
            throw new SerializationException(SERIALIZATION_MESSAGE_ERROR);
        }
        return (String) configs.get(type);
    }
}
