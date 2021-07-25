package com.adidas.product.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreType
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field implements Serializable {

    private String type;
    private String key;
    private Integer maxLength;
    private Integer minLength;
    private String validation;
    private String textColor;
    private Boolean usesStock;
    private List<String> stockCollection;

}
