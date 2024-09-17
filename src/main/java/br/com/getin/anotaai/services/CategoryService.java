package br.com.getin.anotaai.services;

import br.com.getin.anotaai.domain.category.Category;
import br.com.getin.anotaai.domain.category.CategoryDTO;
import br.com.getin.anotaai.domain.category.exceptions.CategoryNotFoundException;
import br.com.getin.anotaai.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Category update(String id, CategoryDTO dto) {
        var entity = repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        entity.update(dto);
        this.repository.save(entity);
        return entity;
    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Optional<Category> getById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        var entity = repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(entity);
    }
}
