package com.techsavy.jpaaudit.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.techsavy.jpaaudit.entity.Product;
import com.techsavy.jpaaudit.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revisions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductServiceDiffblueTest {
    @MockitoBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    /**
     * Test {@link ProductService#saveProduct(Product)}.
     * <ul>
     *   <li>Given {@link Product} (default constructor) CreatedBy is
     * {@code Jan 1, 2020 8:00am GMT+0100}.</li>
     *   <li>Then return {@link Product} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test: {@link ProductService#saveProduct(Product)}
     */
    @Test
    @DisplayName("Test saveProduct(Product); given Product (default constructor) CreatedBy is 'Jan 1, 2020 8:00am GMT+0100'; then return Product (default constructor)")
    @Tag("MaintainedByDiffblue")
    void testSaveProduct_givenProductCreatedByIsJan12020800amGmt0100_thenReturnProduct() {
        // Arrange
        Product product = new Product();
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product.setId(1L);
        product.setLastModifiedBy("JaneDoe");
        product.setLastModifiedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product.setName("Name");
        product.setPrice(new BigDecimal("2.3"));
        product.setVersion(1L);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product);

        Product product2 = new Product();
        product2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product2.setId(1L);
        product2.setLastModifiedBy("JaneDoe");
        product2.setLastModifiedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product2.setName("Name");
        product2.setPrice(new BigDecimal("2.3"));
        product2.setVersion(1L);

        // Act
        Product actualSaveProductResult = productService.saveProduct(product2);

        // Assert
        verify(productRepository).save(isA(Product.class));
        assertSame(product, actualSaveProductResult);
    }

    /**
     * Test {@link ProductService#saveProduct(Product)}.
     * <ul>
     *   <li>Then throw {@link EntityNotFoundException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProductService#saveProduct(Product)}
     */
    @Test
    @DisplayName("Test saveProduct(Product); then throw EntityNotFoundException")
    @Tag("MaintainedByDiffblue")
    void testSaveProduct_thenThrowEntityNotFoundException() {
        // Arrange
        when(productRepository.save(Mockito.<Product>any())).thenThrow(new EntityNotFoundException("An error occurred"));

        Product product = new Product();
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product.setId(1L);
        product.setLastModifiedBy("JaneDoe");
        product.setLastModifiedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product.setName("Name");
        product.setPrice(new BigDecimal("2.3"));
        product.setVersion(1L);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> productService.saveProduct(product));
        verify(productRepository).save(isA(Product.class));
    }

    /**
     * Test {@link ProductService#updateProduct(Product)}.
     * <p>
     * Method under test: {@link ProductService#updateProduct(Product)}
     */
    @Test
    @DisplayName("Test updateProduct(Product)")
    @Tag("MaintainedByDiffblue")
    void testUpdateProduct() {
        // Arrange
        Product product = new Product();
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product.setId(1L);
        product.setLastModifiedBy("JaneDoe");
        product.setLastModifiedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product.setName("Name");
        product.setPrice(new BigDecimal("2.3"));
        product.setVersion(1L);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.save(Mockito.<Product>any())).thenThrow(new EntityNotFoundException("An error occurred"));
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Product inputProduct = new Product();
        inputProduct.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        inputProduct.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        inputProduct.setId(1L);
        inputProduct.setLastModifiedBy("JaneDoe");
        inputProduct.setLastModifiedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        inputProduct.setName("Name");
        inputProduct.setPrice(new BigDecimal("2.3"));
        inputProduct.setVersion(1L);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(inputProduct));
        verify(productRepository).findById(eq(1L));
        verify(productRepository).save(isA(Product.class));
    }

    /**
     * Test {@link ProductService#updateProduct(Product)}.
     * <ul>
     *   <li>Given {@link ProductRepository} {@link CrudRepository#findById(Object)}
     * return empty.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProductService#updateProduct(Product)}
     */
    @Test
    @DisplayName("Test updateProduct(Product); given ProductRepository findById(Object) return empty")
    @Tag("MaintainedByDiffblue")
    void testUpdateProduct_givenProductRepositoryFindByIdReturnEmpty() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Product inputProduct = new Product();
        inputProduct.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        inputProduct.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        inputProduct.setId(1L);
        inputProduct.setLastModifiedBy("JaneDoe");
        inputProduct.setLastModifiedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        inputProduct.setName("Name");
        inputProduct.setPrice(new BigDecimal("2.3"));
        inputProduct.setVersion(1L);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(inputProduct));
        verify(productRepository).findById(eq(1L));
    }

    /**
     * Test {@link ProductService#updateProduct(Product)}.
     * <ul>
     *   <li>Given {@link ProductRepository} {@link CrudRepository#save(Object)}
     * return {@link Product} (default constructor).</li>
     *   <li>Then return {@link Product} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test: {@link ProductService#updateProduct(Product)}
     */
    @Test
    @DisplayName("Test updateProduct(Product); given ProductRepository save(Object) return Product (default constructor); then return Product (default constructor)")
    @Tag("MaintainedByDiffblue")
    void testUpdateProduct_givenProductRepositorySaveReturnProduct_thenReturnProduct() {
        // Arrange
        Product product = new Product();
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product.setId(1L);
        product.setLastModifiedBy("JaneDoe");
        product.setLastModifiedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product.setName("Name");
        product.setPrice(new BigDecimal("2.3"));
        product.setVersion(1L);
        Optional<Product> ofResult = Optional.of(product);

        Product product2 = new Product();
        product2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product2.setId(1L);
        product2.setLastModifiedBy("JaneDoe");
        product2.setLastModifiedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product2.setName("Name");
        product2.setPrice(new BigDecimal("2.3"));
        product2.setVersion(1L);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Product inputProduct = new Product();
        inputProduct.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        inputProduct.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        inputProduct.setId(1L);
        inputProduct.setLastModifiedBy("JaneDoe");
        inputProduct.setLastModifiedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        inputProduct.setName("Name");
        inputProduct.setPrice(new BigDecimal("2.3"));
        inputProduct.setVersion(1L);

        // Act
        Product actualUpdateProductResult = productService.updateProduct(inputProduct);

        // Assert
        verify(productRepository).findById(eq(1L));
        verify(productRepository).save(isA(Product.class));
        assertSame(product2, actualUpdateProductResult);
    }

    /**
     * Test {@link ProductService#getRevisions(Long)}.
     * <ul>
     *   <li>Then return toList Empty.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProductService#getRevisions(Long)}
     */
    @Test
    @DisplayName("Test getRevisions(Long); then return toList Empty")
    @Tag("MaintainedByDiffblue")
    void testGetRevisions_thenReturnToListEmpty() {
        // Arrange
        Revisions<Long, Product> noneResult = Revisions.none();
        when(productRepository.findRevisions(Mockito.<Long>any())).thenReturn(noneResult);

        // Act
        Revisions<Long, Product> actualRevisions = productService.getRevisions(1L);

        // Assert
        verify(productRepository).findRevisions(eq(1L));
        assertTrue(actualRevisions.toList().isEmpty());
        assertSame(noneResult, actualRevisions);
    }

    /**
     * Test {@link ProductService#getRevisions(Long)}.
     * <ul>
     *   <li>Then throw {@link EntityNotFoundException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProductService#getRevisions(Long)}
     */
    @Test
    @DisplayName("Test getRevisions(Long); then throw EntityNotFoundException")
    @Tag("MaintainedByDiffblue")
    void testGetRevisions_thenThrowEntityNotFoundException() {
        // Arrange
        when(productRepository.findRevisions(Mockito.<Long>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> productService.getRevisions(1L));
        verify(productRepository).findRevisions(eq(1L));
    }
}
