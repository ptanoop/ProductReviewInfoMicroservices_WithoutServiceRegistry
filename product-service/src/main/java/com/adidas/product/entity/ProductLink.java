package com.adidas.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreType
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductLink implements Serializable {

    private String type;
    private String productId;
    private String name;
    private String url;
    private String image;
    private String altImage;
    @JsonProperty("pricing_information")
    private PricingInformation pricingInformation;
    @JsonProperty("badge_style")
    private String badgeStyle;
    @JsonProperty("badge_text")
    private String badgeText;
    @JsonProperty("search_color")
    private String searchColor;
    @JsonProperty("default_color")
    private String defaultColor;
    private String source;

}
