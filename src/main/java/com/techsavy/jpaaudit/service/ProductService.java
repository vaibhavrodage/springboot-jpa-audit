package com.techsavy.jpaaudit.service;

import com.techsavy.jpaaudit.dto.ProductSearchDto;
import com.techsavy.jpaaudit.entity.Product;
import com.techsavy.jpaaudit.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product inputProduct) {
        Optional<Product> optProduct = productRepository.findById(inputProduct.getId());
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            product.setName(inputProduct.getName());
            product.setPrice(inputProduct.getPrice());
            return productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Product Not found");
        }
    }

    public Revisions<Long, Product> getRevisions(Long id) {
        return productRepository.findRevisions(id);
    }

    public Page<Product> getProducts(ProductSearchDto productSearchDto, Pageable pageable) {
        ProductSpecification productSpecification = new ProductSpecification(productSearchDto);
        return productRepository.findAll(productSpecification, pageable);
    }


}
