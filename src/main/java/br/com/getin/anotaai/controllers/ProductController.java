package br.com.getin.anotaai.controllers;


import br.com.getin.anotaai.domain.product.Product;
import br.com.getin.anotaai.domain.product.ProductDTO;
import br.com.getin.anotaai.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        var result = this.productService.getAll();

        return result.isEmpty()
            ? ResponseEntity.noContent().build()
            : ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") String id) {
        var result = this.productService.getById(id);

        return result.map(product ->
                ResponseEntity.ok().body(product)).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Product> insert(@RequestBody ProductDTO model) {
        var result = this.productService.insert(model);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO model) {
        var result = this.productService.update(id, model);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id) {
        this.productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
