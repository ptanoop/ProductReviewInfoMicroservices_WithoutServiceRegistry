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
public class Attribute implements Serializable {

    private Boolean sale;
    private String brand;
    private String color;
    private String gender;
    private Boolean outlet;
    private List<String> sport;
    private List<String> surface;
    private String category;
    private List<String> functions;
    private List<String> sportSub;
    @JsonProperty("size_fit_bar")
    private SizeFitBar sizeFitBar;
    private List<String> collection;
    @JsonProperty("search_color")
    private String searchColor;
    private List<String> productType;
    private Boolean personalizable;
    private Boolean isCnCRestricted;
    private List<String> productLineStyle;
    private List<String> genericProductType;
    @JsonProperty("mandatory_personalization")
    private Boolean mandatoryPersonalization;
    private Boolean customizable;
    @JsonProperty("search_color_raw")
    private String searchColorRaw;
    @JsonProperty("is_orderable")
    private Boolean isOrderable;
    private Boolean isWaitingRoomProduct;
    private Boolean isInPreview;
    private Boolean specialLaunch;
    @JsonProperty("special_launch_type")
    private String specialLaunchType;
    private Object sizeTypes;
    @JsonProperty("is_flash")
    private Boolean isFlash;
    @JsonProperty("size_chart_link")
    private String sizeChartLink;

}
