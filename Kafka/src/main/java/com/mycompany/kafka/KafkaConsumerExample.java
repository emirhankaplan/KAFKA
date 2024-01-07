package com.mycompany.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        // Kafka Consumer konfigürasyonu
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.18.0.3"); // Kafka broker adresi
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "1"); // Grup ID
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // Kafka Consumer oluşturma
        Consumer<String, String> consumer = new KafkaConsumer<>(props);

        // Dinlenecek topic belirleme
        consumer.subscribe(Collections.singletonList("deneme2")); // Dinlenecek Kafka topic'i

        // Mesajları dinleme
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                // Mesajları işleme
                records.forEach(record -> {
                    System.out.printf("Received message: key = %s, value = %s%n", record.key(), record.value());
                    // Burada mesajları işleyebilirsiniz
                });
            }
        } finally {
            // Consumer kapatma
            consumer.close();
        }
    }
}
