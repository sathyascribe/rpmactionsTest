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
 * ChangePasswordModel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-24T12:51:03.671750900+05:30[Asia/Calcutta]")
public class ChangePasswordModel implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_NEW_PASSWORD = "newPassword";
  @SerializedName(SERIALIZED_NAME_NEW_PASSWORD)
  private String newPassword;

  public static final String SERIALIZED_NAME_OLD_PASSWORD = "oldPassword";
  @SerializedName(SERIALIZED_NAME_OLD_PASSWORD)
  private String oldPassword;


  public ChangePasswordModel newPassword(String newPassword) {
    
    this.newPassword = newPassword;
    return this;
  }

   /**
   * Get newPassword
   * @return newPassword
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getNewPassword() {
    return newPassword;
  }


  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }


  public ChangePasswordModel oldPassword(String oldPassword) {
    
    this.oldPassword = oldPassword;
    return this;
  }

   /**
   * Get oldPassword
   * @return oldPassword
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getOldPassword() {
    return oldPassword;
  }


  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangePasswordModel changePasswordModel = (ChangePasswordModel) o;
    return Objects.equals(this.newPassword, changePasswordModel.newPassword) &&
        Objects.equals(this.oldPassword, changePasswordModel.oldPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newPassword, oldPassword);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangePasswordModel {\n");
    sb.append("    newPassword: ").append(toIndentedString(newPassword)).append("\n");
    sb.append("    oldPassword: ").append(toIndentedString(oldPassword)).append("\n");
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

