package com.techsavy.jpaaudit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsavy.jpaaudit.entity.Product;
import com.techsavy.jpaaudit.service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductControllerDiffblueTest {
    @Autowired
    private ProductController productController;

    @MockitoBean
    private ProductService productService;

    /**
     * Test {@link ProductController#createProduct(Product)}.
     * <p>
     * Method under test: {@link ProductController#createProduct(Product)}
     */
    @Test
    @DisplayName("Test createProduct(Product)")
    @Disabled("TODO: Complete this test")
    @Tag("MaintainedByDiffblue")
    void testCreateProduct() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.techsavy.jpaaudit.entity.Product["createdDate"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1328)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:770)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:184)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:502)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:341)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4811)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:4052)
        //   See https://diff.blue/R013 to resolve this issue.

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
        String content = (new ObjectMapper()).writeValueAsString(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(productController).build().perform(requestBuilder);
    }

    /**
     * Test {@link ProductController#createProduct(Product)}.
     * <ul>
     *   <li>Then StatusCode return {@link HttpStatus}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProductController#createProduct(Product)}
     */
    @Test
    @DisplayName("Test createProduct(Product); then StatusCode return HttpStatus")
    @Tag("MaintainedByDiffblue")
    void testCreateProduct_thenStatusCodeReturnHttpStatus() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
        //   Run dcover create --keep-partial-tests to gain insights into why
        //   a non-Spring test was created.

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
        ProductService productService = mock(ProductService.class);
        when(productService.saveProduct(Mockito.<Product>any())).thenReturn(product);
        ProductController productController = new ProductController(productService);

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
        ResponseEntity<Product> actualCreateProductResult = productController.createProduct(product2);

        // Assert
        verify(productService).saveProduct(isA(Product.class));
        HttpStatusCode statusCode = actualCreateProductResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals(200, actualCreateProductResult.getStatusCodeValue());
        assertEquals(HttpStatus.OK, statusCode);
        assertTrue(actualCreateProductResult.hasBody());
        assertTrue(actualCreateProductResult.getHeaders().isEmpty());
        assertSame(product, actualCreateProductResult.getBody());
    }

    /**
     * Test {@link ProductController#updateProduct(Product)}.
     * <p>
     * Method under test: {@link ProductController#updateProduct(Product)}
     */
    @Test
    @DisplayName("Test updateProduct(Product)")
    @Tag("MaintainedByDiffblue")
    void testUpdateProduct() throws Exception {
        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/product", uriVariables)
                .contentType(MediaType.APPLICATION_JSON);

        Product product = new Product();
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product.setId(1L);
        product.setLastModifiedBy("JaneDoe");
        product.setLastModifiedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        product.setName("Name");
        BigDecimal price = new BigDecimal("2.3");
        product.setPrice(price);
        product.setVersion(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(product));
        Object[] controllers = new Object[]{productController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }
}
