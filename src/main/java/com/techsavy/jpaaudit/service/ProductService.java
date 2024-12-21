package com.techsavy.jpaaudit.service;

import com.techsavy.jpaaudit.entity.Product;
import com.techsavy.jpaaudit.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product inputProduct){
        Optional<Product> optProduct = productRepository.findById(inputProduct.getId());
        if(optProduct.isPresent()){
            Product product = optProduct.get();
            product.setName(inputProduct.getName());
            product.setPrice(inputProduct.getPrice());
            return productRepository.save(product);
        }else{
            throw new EntityNotFoundException("Product Not found");
        }
    }
}
