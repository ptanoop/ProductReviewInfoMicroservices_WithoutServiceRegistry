package com.adidas.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreType
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDescription implements Serializable {

    private String title;
    private String text;
    private String subtitle;
    private List<String> usps;
    @JsonProperty("wash_care_instructions")
    private WashCareInstructions washCareInstructions;
    @JsonProperty("product_highlights")
    private List<ProductHighlights> productHighlights;
    @JsonProperty("description_assets")
    private DescriptionAssets descriptionAssets;

}
