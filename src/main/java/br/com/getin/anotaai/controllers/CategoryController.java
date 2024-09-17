package br.com.getin.anotaai.controllers;

import br.com.getin.anotaai.domain.category.Category;
import br.com.getin.anotaai.domain.category.CategoryDTO;
import br.com.getin.anotaai.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")

public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        var entities = this.service.getAll();
        return entities.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") String id) {
        var entity =  this.service.getById(id);
        return entity.map(category ->
                ResponseEntity.ok().body(category)).orElseGet(() ->
                ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO model){
        var entity =  this.service.insert(model);
        return ResponseEntity.ok().body(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO model) {
        var entity =  this.service.update(id, model);
        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
