package com.techsavy.jpaaudit.service;

import com.techsavy.jpaaudit.dto.ProductSearchDto;
import com.techsavy.jpaaudit.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductSpecification implements Specification<Product> {

    private ProductSearchDto productSearchDto;

    public ProductSpecification(ProductSearchDto productSearchDto) {
        this.productSearchDto = productSearchDto;
    }

    @Override
    public Predicate toPredicate
            (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (null != productSearchDto.getId()) {
            predicates.add(builder.equal(root.get("id"), productSearchDto.getId()));
        }

        if (null != productSearchDto.getName() && !productSearchDto.getName().isBlank()) {
            predicates.add(builder.equal(root.get("name"), productSearchDto.getName()));
        }

        return builder.and(predicates.toArray(Predicate[]::new));
    }
}
