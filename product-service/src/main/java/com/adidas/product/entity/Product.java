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
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {

    private String id;
    @JsonProperty("product_type")
    private String productType;
    @JsonProperty("model_number")
    private String modelNumber;
    private String name;
    @JsonProperty("meta_data")
    private MetaData metaData;
    @JsonProperty("view_list")
    private List<View> viewList;
    @JsonProperty("attribute_list")
    private Attribute attributeList;
    @JsonProperty("breadcrumb_list")
    private List<BreadCrumb> breadCrumbList;
    @JsonProperty("pricing_information")
    private PricingInformation pricingInformation;
    @JsonProperty("product_description")
    private ProductDescription productDescription;
    private Boolean recommendationsEnabled;
    @JsonProperty("product_link_list")
    private List<ProductLink> productLinkList;
    private Embellishment embellishment;
}
