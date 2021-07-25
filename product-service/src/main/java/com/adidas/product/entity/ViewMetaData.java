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
@JsonIgnoreType
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewMetaData implements Serializable {

    @JsonProperty("asset_usage")
    private List<String> assetUsage;
    private String imageStyle;
    private String view;
    private String usageTerms;
    private String sortOrder;
    private List<Object> subjects;

}
