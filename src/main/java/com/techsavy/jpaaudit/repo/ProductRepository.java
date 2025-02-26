package com.techsavy.jpaaudit.repo;

import com.techsavy.jpaaudit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, RevisionRepository<Product,Long,Long>, JpaSpecificationExecutor<Product> {
}