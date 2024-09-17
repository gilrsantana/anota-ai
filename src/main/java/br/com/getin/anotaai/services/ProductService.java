package br.com.getin.anotaai.services;

import br.com.getin.anotaai.domain.category.exceptions.CategoryNotFoundException;
import br.com.getin.anotaai.domain.product.Product;
import br.com.getin.anotaai.domain.product.ProductDTO;
import br.com.getin.anotaai.domain.product.exceptions.ProductNotFoundException;
import br.com.getin.anotaai.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public ProductService(CategoryService categoryService, ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    public Product insert(ProductDTO dto) {
        var category = this.categoryService.getById(dto.categoryId()).orElseThrow(ProductNotFoundException::new);
        var entity = new Product(dto);
        entity.setCategory(category);
        this.productRepository.insert(entity);
        return entity;
    }

    public Product update(String id, ProductDTO dto) {
        var entity = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        if (dto.categoryId() != null)
            this.categoryService.getById(dto.categoryId()).ifPresent(entity::setCategory);
        entity.update(dto);
        this.productRepository.save(entity);
        return entity;
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Optional<Product> getById(String id) {
        return productRepository.findById(id);
    }

    public void delete(String id) {
        var entity = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(entity);
    }
}
