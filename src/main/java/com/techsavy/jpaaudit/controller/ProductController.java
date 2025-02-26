package com.techsavy.jpaaudit.controller;

import com.techsavy.jpaaudit.dto.ProductSearchDto;
import com.techsavy.jpaaudit.entity.Product;
import com.techsavy.jpaaudit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revisions;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ok(savedProduct);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @GetMapping("/{id}/revisions")
    public ResponseEntity<Revisions<Long, Product>> getRevisions(@PathVariable Long id) {
        Revisions<Long, Product> revisions = productService.getRevisions(id);
        return ResponseEntity.ok(revisions);
    }

    @PostMapping("/products")
    public Page<Product> getProducts(@RequestBody ProductSearchDto productSearchDto, @PageableDefault Pageable pageable) {
        return productService.getProducts(productSearchDto, pageable);
    }

}
