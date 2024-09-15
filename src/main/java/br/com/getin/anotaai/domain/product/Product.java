package br.com.getin.anotaai.domain.product;

import br.com.getin.anotaai.domain.base.EntityBase;
import br.com.getin.anotaai.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends EntityBase {
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private Category category;

    public Product(ProductDTO productDTO) {
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.ownerId = productDTO.ownerId();
        this.price = productDTO.price();
        this.category = productDTO.category();
    }
}
