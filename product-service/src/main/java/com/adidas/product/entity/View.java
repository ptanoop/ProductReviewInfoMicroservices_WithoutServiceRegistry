package com.adidas.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreType
@JsonIgnoreProperties(ignoreUnknown = true)
public class View implements Serializable {

    private String type;
    private String source;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("metadata")
    private ViewMetaData metaData;

}
