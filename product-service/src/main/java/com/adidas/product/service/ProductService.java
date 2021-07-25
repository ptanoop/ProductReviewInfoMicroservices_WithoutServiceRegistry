package com.adidas.product.service;

import com.adidas.product.entity.Product;
import com.adidas.product.entity.ProductReviewResponse;
import com.adidas.product.entity.Review;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class ProductService {

    @Value("${review.service.username}")
    private String REVIEW_SERVICE_USERNAME;
    @Value("${review.service.password}")
    private String REVIEW_SERVICE_PASSWORD;
    @Value("${review.service.url}")
    private String REVIEW_SERVICE_URL;


    @Value("${adidas.rest.link}")
    private String ADIDAS_REST_URL;

    @Value("${adidas.rest.call.user.agent}")
    private String ADIDAS_REST_CALL_USER_AGENT;

    private Product getProductFromAdidasById(String productId){
        Product adidasProduct = null;
        try {
            URL url = new URL(ADIDAS_REST_URL+"/"+productId);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("User-Agent", ADIDAS_REST_CALL_USER_AGENT);

            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + urlConnection.getResponseCode());

            }
            else{
                log.info("Product JSON String = "+sb);
                System.out.println(sb);
                ObjectMapper objectMapper = new ObjectMapper();
                adidasProduct = objectMapper.readValue(sb.toString(), Product.class);
            }
        }
        catch (Exception e){
            log.info("Product not found for productId : " + productId);
        }
        return adidasProduct;
    }

    private Review getProductReview(String productId){
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(REVIEW_SERVICE_USERNAME, REVIEW_SERVICE_PASSWORD).build();
        Review review = null;
        try {
            review = restTemplate.getForObject(REVIEW_SERVICE_URL + productId, Review.class);
        }
        catch(HttpClientErrorException ex){
            log.info("Review not found for productId : " + productId);
        }
        return review;
    }

    public ProductReviewResponse getProductById(String productId) {
        log.info("Get Product By Id From Service");
        Product product = getProductFromAdidasById(productId);
        log.info(product != null ? product.toString() : "Product Info Null");
        Review review = getProductReview(productId);
        log.info(review != null ? review.toString() : "Review Info Null");
        if(product != null && product.getId()!=null){
            ProductReviewResponse productReviewResponse = new ProductReviewResponse(product,review);
            return productReviewResponse;
        }
        log.info("Got Response from the public service");
        return null;
    }
}