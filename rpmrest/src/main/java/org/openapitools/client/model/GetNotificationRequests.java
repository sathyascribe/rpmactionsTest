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
 * GetNotificationRequests
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-24T12:51:03.671750900+05:30[Asia/Calcutta]")
public class GetNotificationRequests implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_NOTIFICATION_ID = "notificationId";
  @SerializedName(SERIALIZED_NAME_NOTIFICATION_ID)
  private Long notificationId;

  public static final String SERIALIZED_NAME_REQUEST_BY_USER_ID = "requestByUserId";
  @SerializedName(SERIALIZED_NAME_REQUEST_BY_USER_ID)
  private Long requestByUserId;

  public static final String SERIALIZED_NAME_USER_NAME = "userName";
  @SerializedName(SERIALIZED_NAME_USER_NAME)
  private String userName;

  public static final String SERIALIZED_NAME_USER_CONTACT_NUMBER = "userContactNumber";
  @SerializedName(SERIALIZED_NAME_USER_CONTACT_NUMBER)
  private String userContactNumber;

  public static final String SERIALIZED_NAME_USER_EMAIL_ID = "userEmailId";
  @SerializedName(SERIALIZED_NAME_USER_EMAIL_ID)
  private String userEmailId;

  public static final String SERIALIZED_NAME_IS_ACCEPTED = "isAccepted";
  @SerializedName(SERIALIZED_NAME_IS_ACCEPTED)
  private Boolean isAccepted;

  public static final String SERIALIZED_NAME_REQUEST_TIME = "requestTime";
  @SerializedName(SERIALIZED_NAME_REQUEST_TIME)
  private Date requestTime;


  public GetNotificationRequests notificationId(Long notificationId) {
    
    this.notificationId = notificationId;
    return this;
  }

   /**
   * Get notificationId
   * @return notificationId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getNotificationId() {
    return notificationId;
  }


  public void setNotificationId(Long notificationId) {
    this.notificationId = notificationId;
  }


  public GetNotificationRequests requestByUserId(Long requestByUserId) {
    
    this.requestByUserId = requestByUserId;
    return this;
  }

   /**
   * Get requestByUserId
   * @return requestByUserId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getRequestByUserId() {
    return requestByUserId;
  }


  public void setRequestByUserId(Long requestByUserId) {
    this.requestByUserId = requestByUserId;
  }


  public GetNotificationRequests userName(String userName) {
    
    this.userName = userName;
    return this;
  }

   /**
   * Get userName
   * @return userName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getUserName() {
    return userName;
  }


  public void setUserName(String userName) {
    this.userName = userName;
  }


  public GetNotificationRequests userContactNumber(String userContactNumber) {
    
    this.userContactNumber = userContactNumber;
    return this;
  }

   /**
   * Get userContactNumber
   * @return userContactNumber
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getUserContactNumber() {
    return userContactNumber;
  }


  public void setUserContactNumber(String userContactNumber) {
    this.userContactNumber = userContactNumber;
  }


  public GetNotificationRequests userEmailId(String userEmailId) {
    
    this.userEmailId = userEmailId;
    return this;
  }

   /**
   * Get userEmailId
   * @return userEmailId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getUserEmailId() {
    return userEmailId;
  }


  public void setUserEmailId(String userEmailId) {
    this.userEmailId = userEmailId;
  }


  public GetNotificationRequests isAccepted(Boolean isAccepted) {
    
    this.isAccepted = isAccepted;
    return this;
  }

   /**
   * Get isAccepted
   * @return isAccepted
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsAccepted() {
    return isAccepted;
  }


  public void setIsAccepted(Boolean isAccepted) {
    this.isAccepted = isAccepted;
  }


  public GetNotificationRequests requestTime(Date requestTime) {
    
    this.requestTime = requestTime;
    return this;
  }

   /**
   * Get requestTime
   * @return requestTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Date getRequestTime() {
    return requestTime;
  }


  public void setRequestTime(Date requestTime) {
    this.requestTime = requestTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetNotificationRequests getNotificationRequests = (GetNotificationRequests) o;
    return Objects.equals(this.notificationId, getNotificationRequests.notificationId) &&
        Objects.equals(this.requestByUserId, getNotificationRequests.requestByUserId) &&
        Objects.equals(this.userName, getNotificationRequests.userName) &&
        Objects.equals(this.userContactNumber, getNotificationRequests.userContactNumber) &&
        Objects.equals(this.userEmailId, getNotificationRequests.userEmailId) &&
        Objects.equals(this.isAccepted, getNotificationRequests.isAccepted) &&
        Objects.equals(this.requestTime, getNotificationRequests.requestTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationId, requestByUserId, userName, userContactNumber, userEmailId, isAccepted, requestTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetNotificationRequests {\n");
    sb.append("    notificationId: ").append(toIndentedString(notificationId)).append("\n");
    sb.append("    requestByUserId: ").append(toIndentedString(requestByUserId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    userContactNumber: ").append(toIndentedString(userContactNumber)).append("\n");
    sb.append("    userEmailId: ").append(toIndentedString(userEmailId)).append("\n");
    sb.append("    isAccepted: ").append(toIndentedString(isAccepted)).append("\n");
    sb.append("    requestTime: ").append(toIndentedString(requestTime)).append("\n");
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

