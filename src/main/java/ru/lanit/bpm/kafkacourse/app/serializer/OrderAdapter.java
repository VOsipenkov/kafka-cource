package ru.lanit.bpm.kafkacourse.app.serializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import ru.lanit.bpm.kafkacourse.domain.Order;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class OrderAdapter implements Serializer<Order>, Deserializer<Order> {

    @Override
    public byte[] serialize(String s, Order order) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(order).getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public Order deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Order order = null;
        try {
            order = mapper.readValue(new String(arg1, StandardCharsets.UTF_8), Order.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
    }
}
