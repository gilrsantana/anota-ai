package br.com.getin.anotaai.services;

import br.com.getin.anotaai.domain.category.Category;
import br.com.getin.anotaai.domain.category.CategoryDTO;
import br.com.getin.anotaai.domain.category.exceptions.CategoryNotFoundException;
import br.com.getin.anotaai.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category insert(CategoryDTO dto) {
        var entity = new Category(dto);
        this.repository.insert(entity);
        return entity;
    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO model) {
        var category = repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        category.update(model);
        this.repository.save(category);
        return category;
    }

    public Category getById(String id) {
        return repository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public void delete(String id) {
        var category = repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
    }
}
