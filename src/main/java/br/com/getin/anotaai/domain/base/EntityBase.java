package br.com.getin.anotaai.domain.base;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public abstract class EntityBase {
    @Id
    private final String id = UUID.randomUUID().toString();
}
