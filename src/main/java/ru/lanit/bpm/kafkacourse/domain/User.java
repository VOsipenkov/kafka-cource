package ru.lanit.bpm.kafkacourse.domain;

import lombok.Data;

@Data
public class User {
    private String name;
    private String surname;
    private int age;
}
