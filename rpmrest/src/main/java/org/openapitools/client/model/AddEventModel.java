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
import java.util.Date;
import java.io.Serializable;

/**
 * AddEventModel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-24T12:51:03.671750900+05:30[Asia/Calcutta]")
public class AddEventModel implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_EVENT_NAME = "eventName";
  @SerializedName(SERIALIZED_NAME_EVENT_NAME)
  private String eventName;

  public static final String SERIALIZED_NAME_DISTANCE = "distance";
  @SerializedName(SERIALIZED_NAME_DISTANCE)
  private Double distance;

  public static final String SERIALIZED_NAME_DISTANCE_MEASUREMENT_ID = "distanceMeasurementId";
  @SerializedName(SERIALIZED_NAME_DISTANCE_MEASUREMENT_ID)
  private Long distanceMeasurementId;

  public static final String SERIALIZED_NAME_EVENT_START_TIME = "eventStartTime";
  @SerializedName(SERIALIZED_NAME_EVENT_START_TIME)
  private Date eventStartTime;

  public static final String SERIALIZED_NAME_EVENT_END_TIME = "eventEndTime";
  @SerializedName(SERIALIZED_NAME_EVENT_END_TIME)
  private Date eventEndTime;

  public static final String SERIALIZED_NAME_EVENT_START_DATE = "eventStartDate";
  @SerializedName(SERIALIZED_NAME_EVENT_START_DATE)
  private Date eventStartDate;

  public static final String SERIALIZED_NAME_EVENT_END_DATE = "eventEndDate";
  @SerializedName(SERIALIZED_NAME_EVENT_END_DATE)
  private Date eventEndDate;


  public AddEventModel eventName(String eventName) {
    
    this.eventName = eventName;
    return this;
  }

   /**
   * Get eventName
   * @return eventName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getEventName() {
    return eventName;
  }


  public void setEventName(String eventName) {
    this.eventName = eventName;
  }


  public AddEventModel distance(Double distance) {
    
    this.distance = distance;
    return this;
  }

   /**
   * Get distance
   * @return distance
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Double getDistance() {
    return distance;
  }


  public void setDistance(Double distance) {
    this.distance = distance;
  }


  public AddEventModel distanceMeasurementId(Long distanceMeasurementId) {
    
    this.distanceMeasurementId = distanceMeasurementId;
    return this;
  }

   /**
   * Get distanceMeasurementId
   * @return distanceMeasurementId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getDistanceMeasurementId() {
    return distanceMeasurementId;
  }


  public void setDistanceMeasurementId(Long distanceMeasurementId) {
    this.distanceMeasurementId = distanceMeasurementId;
  }


  public AddEventModel eventStartTime(Date eventStartTime) {
    
    this.eventStartTime = eventStartTime;
    return this;
  }

   /**
   * Get eventStartTime
   * @return eventStartTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Date getEventStartTime() {
    return eventStartTime;
  }


  public void setEventStartTime(Date eventStartTime) {
    this.eventStartTime = eventStartTime;
  }


  public AddEventModel eventEndTime(Date eventEndTime) {
    
    this.eventEndTime = eventEndTime;
    return this;
  }

   /**
   * Get eventEndTime
   * @return eventEndTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Date getEventEndTime() {
    return eventEndTime;
  }


  public void setEventEndTime(Date eventEndTime) {
    this.eventEndTime = eventEndTime;
  }


  public AddEventModel eventStartDate(Date eventStartDate) {
    
    this.eventStartDate = eventStartDate;
    return this;
  }

   /**
   * Get eventStartDate
   * @return eventStartDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Date getEventStartDate() {
    return eventStartDate;
  }


  public void setEventStartDate(Date eventStartDate) {
    this.eventStartDate = eventStartDate;
  }


  public AddEventModel eventEndDate(Date eventEndDate) {
    
    this.eventEndDate = eventEndDate;
    return this;
  }

   /**
   * Get eventEndDate
   * @return eventEndDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Date getEventEndDate() {
    return eventEndDate;
  }


  public void setEventEndDate(Date eventEndDate) {
    this.eventEndDate = eventEndDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddEventModel addEventModel = (AddEventModel) o;
    return Objects.equals(this.eventName, addEventModel.eventName) &&
        Objects.equals(this.distance, addEventModel.distance) &&
        Objects.equals(this.distanceMeasurementId, addEventModel.distanceMeasurementId) &&
        Objects.equals(this.eventStartTime, addEventModel.eventStartTime) &&
        Objects.equals(this.eventEndTime, addEventModel.eventEndTime) &&
        Objects.equals(this.eventStartDate, addEventModel.eventStartDate) &&
        Objects.equals(this.eventEndDate, addEventModel.eventEndDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventName, distance, distanceMeasurementId, eventStartTime, eventEndTime, eventStartDate, eventEndDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddEventModel {\n");
    sb.append("    eventName: ").append(toIndentedString(eventName)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    distanceMeasurementId: ").append(toIndentedString(distanceMeasurementId)).append("\n");
    sb.append("    eventStartTime: ").append(toIndentedString(eventStartTime)).append("\n");
    sb.append("    eventEndTime: ").append(toIndentedString(eventEndTime)).append("\n");
    sb.append("    eventStartDate: ").append(toIndentedString(eventStartDate)).append("\n");
    sb.append("    eventEndDate: ").append(toIndentedString(eventEndDate)).append("\n");
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

