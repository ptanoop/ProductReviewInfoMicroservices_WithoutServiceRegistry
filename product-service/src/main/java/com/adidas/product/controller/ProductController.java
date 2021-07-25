package com.adidas.product.controller;

import com.adidas.product.entity.Product;
import com.adidas.product.entity.ProductReviewResponse;
import com.adidas.product.entity.Review;
import com.adidas.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins="*", maxAge=3600)
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/{productId}")
    public ResponseEntity<ProductReviewResponse> getProductById(@PathVariable String productId) {
        log.info("Get the product Id : "+productId);
        ProductReviewResponse productResp = productService.getProductById(productId);
        log.info(productResp!=null?productResp.toString():"ProductResp is null");
        if(productResp!=null){
            return new ResponseEntity<>(productResp, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
