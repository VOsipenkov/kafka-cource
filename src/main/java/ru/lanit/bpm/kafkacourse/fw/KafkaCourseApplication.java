package ru.lanit.bpm.kafkacourse.fw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@ComponentScan(basePackages = {"ru.lanit.bpm.kafkacourse"})
public class KafkaCourseApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaCourseApplication.class, args);
	}
}
