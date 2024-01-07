package com.mycompany.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerExample {
    public static void main(String[] args) {
        // Kafka Producer konfigürasyonu
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.18.0.3"); // Docker Compose dosyanızdaki Kafka servisi adı
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Kafka Producer oluşturma
        Producer<String, String> producer = new KafkaProducer<>(props);

        // Örnek bir mesaj gönderme
        String key = "1";
        String value = "Merhaba Kafka!";

        // Mesajı gönderme
        producer.send(new ProducerRecord<>("deneme2", key, value));

        // Producer'ı kapat
        producer.close();
    }
}
