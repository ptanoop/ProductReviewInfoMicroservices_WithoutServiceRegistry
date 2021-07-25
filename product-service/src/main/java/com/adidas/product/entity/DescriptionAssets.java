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
public class DescriptionAssets implements Serializable {
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("video_url")
    private String videoUrl;
    @JsonProperty("poster_url")
    private String posterUrl;

}
