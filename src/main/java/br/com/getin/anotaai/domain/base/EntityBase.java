package br.com.getin.anotaai.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.UUID;
@Getter
@Setter
public abstract class EntityBase {
    @Id
    private String id = UUID.randomUUID().toString();
}
