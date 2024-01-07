package com.mycompany.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Kafka {

    public static void main(String[] args) {
        // Kafka Producer
        produceMessage();

        // Kafka Consumer
        consumeMessages();
    }

    private static void produceMessage() {
        // Producer configuration
        Properties producerProps = new Properties();
        producerProps.put("bootstrap.servers", "172.18.0.3"); // Değişiklik burada
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Producer creation
        try (Producer<String, String> producer = new KafkaProducer<>(producerProps)) {
            // Sample Student object
            Student s1 = new Student();
            s1.setId(1);
            s1.setName("John Doe");

            // Send Student object to Kafka
            producer.send(new ProducerRecord<>("deneme2", Integer.toString(s1.getId()), s1.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consumeMessages() {
        // Consumer configuration
        Properties consumerProps = new Properties();
        consumerProps.put("bootstrap.servers", "172.18.0.3"); // Değişiklik burada
        consumerProps.put("group.id", "1001");
        consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Consumer creation
        try (Consumer<String, String> consumer = new KafkaConsumer<>(consumerProps)) {
            // Subscribe to the topic
            consumer.subscribe(Collections.singletonList("deneme2"));

            // Listen for messages
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    System.out.printf("Received message: key = %s, value = %s%n", record.key(), record.value());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

