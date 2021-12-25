/*
 * RPM Mobile
 * RPM Mobile
 *
 * The version of the OpenAPI document: Phase 1
 * Contact: karthikchiru@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.io.Serializable;

/**
 * WeightModel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-24T12:51:03.671750900+05:30[Asia/Calcutta]")
public class WeightModel implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_WEIGHT_MEASUREMENT_NAME = "weightMeasurementName";
  @SerializedName(SERIALIZED_NAME_WEIGHT_MEASUREMENT_NAME)
  private String weightMeasurementName;

  public static final String SERIALIZED_NAME_UNIT = "unit";
  @SerializedName(SERIALIZED_NAME_UNIT)
  private String unit;


  public WeightModel id(Long id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public WeightModel weightMeasurementName(String weightMeasurementName) {
    
    this.weightMeasurementName = weightMeasurementName;
    return this;
  }

   /**
   * Get weightMeasurementName
   * @return weightMeasurementName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getWeightMeasurementName() {
    return weightMeasurementName;
  }


  public void setWeightMeasurementName(String weightMeasurementName) {
    this.weightMeasurementName = weightMeasurementName;
  }


  public WeightModel unit(String unit) {
    
    this.unit = unit;
    return this;
  }

   /**
   * Get unit
   * @return unit
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getUnit() {
    return unit;
  }


  public void setUnit(String unit) {
    this.unit = unit;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WeightModel weightModel = (WeightModel) o;
    return Objects.equals(this.id, weightModel.id) &&
        Objects.equals(this.weightMeasurementName, weightModel.weightMeasurementName) &&
        Objects.equals(this.unit, weightModel.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, weightMeasurementName, unit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WeightModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    weightMeasurementName: ").append(toIndentedString(weightMeasurementName)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
