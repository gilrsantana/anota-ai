package br.com.getin.anotaai.domain.product;

import br.com.getin.anotaai.domain.category.Category;

public record ProductDTO(String title, String description, String ownerId, Integer price, Category category) {
}
