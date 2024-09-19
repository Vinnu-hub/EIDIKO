package com.example.kafka_cab_booking_System.configuration;


import com.example.kafka_cab_booking_System.entity.RideRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.listener.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    //  these is   >>>>>>   Producer configuration
    @Bean
    public DefaultKafkaProducerFactory<String, RideRequest> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put("bootstrap.servers", "localhost:9092");
        // the serilazation is we convert is that the kafka server will to send fat the mesage
        // it is in binary or machine laguage or json

        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        config.put("value.serializer", JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }


    //  these bean is a kafka template to send messge for the kafka server
    @Bean
    public KafkaTemplate<String, RideRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // these is  Consumer configuration
    @Bean
    public ConsumerFactory<String, RideRequest> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("group.id", "cab-booking-group");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", JsonDeserializer.class);
        // we do de-seralization for the commnuication from the object to the json formate easy to understand to us.
        config.put("spring.json.value.default.type", RideRequest.class.getName());
        return new DefaultKafkaConsumerFactory<>(config);
    }


    // these  bean is a  listener configuration for consuming messages
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RideRequest> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RideRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
