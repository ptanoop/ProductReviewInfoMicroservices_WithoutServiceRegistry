package com.adidas.product.controller;

import com.adidas.product.entity.Product;
import com.adidas.product.entity.ProductReviewResponse;
import com.adidas.product.entity.Review;
import com.adidas.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@Slf4j
class ProductControllerIntegrationTest {

    @Autowired
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getProductById_successCase() throws Exception {
        Product sampleProduct = new Product();
        sampleProduct.setId("AL1000");
        sampleProduct.setProductType("inline");
        sampleProduct.setModelNumber("CBY96");
        sampleProduct.setName("Adilette Comfort Slides");
        Review review = new Review("AL1000", 4.0, 100);
        when(productService.getProductById("AL1000")).thenReturn(new ProductReviewResponse(sampleProduct, review));
        mockMvc.perform(MockMvcRequestBuilders.get("/products/AL1000"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.name").value("Adilette Comfort Slides"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productReview.numberOfReviews").value(100))
                .andExpect(status().isOk());
    }

    @Test
    void getProductById_withoutReview() throws Exception {
        Product sampleProduct = new Product();
        sampleProduct.setId("AL1001");
        sampleProduct.setProductType("inline");
        sampleProduct.setModelNumber("CBY96");
        sampleProduct.setName("Adilette Comfort Slides");
        when(productService.getProductById("AL1001")).thenReturn(new ProductReviewResponse(sampleProduct, null));
        mockMvc.perform(MockMvcRequestBuilders.get("/products/AL1001"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.name").value("Adilette Comfort Slides"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productReview").isEmpty())
                .andExpect(status().isOk());
    }

    @Test
    void getProductById_failureCase() throws Exception {
        when(productService.getProductById(any(String.class))).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1000"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
