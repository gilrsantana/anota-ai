package br.com.getin.anotaai.controllers;

import br.com.getin.anotaai.domain.category.Category;
import br.com.getin.anotaai.domain.category.CategoryDTO;
import br.com.getin.anotaai.services.CategoryService;
import jakarta.websocket.server.PathParam;
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
        return ResponseEntity.ok().body(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathParam("id") String id) {
        var entity =  this.service.getById(id);
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO model){
        var entity =  this.service.insert(model);
        return ResponseEntity.ok().body(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathParam("id") String id, @RequestBody CategoryDTO model) {
        var entity =  this.service.update(id, model);
        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathParam("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
