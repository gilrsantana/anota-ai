package br.com.getin.anotaai.domain.category;

import br.com.getin.anotaai.domain.base.EntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("categories")
@Getter
@Setter
@NoArgsConstructor
public class Category extends EntityBase {
    private String description;
    private String title;
    private String ownerId;

    public Category(CategoryDTO categoryDTO) {
        this.description = categoryDTO.description();
        this.title = categoryDTO.title();
        this.ownerId = categoryDTO.ownerId();
    }

    public void update(CategoryDTO categoryDTO) {
        if(!categoryDTO.description().isEmpty())
            this.description = categoryDTO.description();
        if (!categoryDTO.title().isEmpty())
            this.title = categoryDTO.title();
    }
}
