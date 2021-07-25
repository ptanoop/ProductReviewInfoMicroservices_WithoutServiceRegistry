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
public class PricingInformation implements Serializable {

    private Double currentPrice;
    @JsonProperty("standard_price")
    private Double standardPrice;
    @JsonProperty("standard_price_no_vat")
    private Double standardPriceNoVat;
    private Double salePrice;

}
