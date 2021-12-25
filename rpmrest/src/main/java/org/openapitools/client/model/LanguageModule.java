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

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * LanguageModel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-02T12:44:20.356130700+05:30[Asia/Calcutta]")
public class LanguageModule implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_LANGUAGE_NAME = "languageName";
  @SerializedName(SERIALIZED_NAME_LANGUAGE_NAME)
  private String languageName;


  public LanguageModule id(Long id) {
    
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


  public LanguageModule languageName(String languageName) {
    
    this.languageName = languageName;
    return this;
  }

   /**
   * Get languageName
   * @return languageName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getLanguageName() {
    return languageName;
  }


  public void setLanguageName(String languageName) {
    this.languageName = languageName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LanguageModule languageModel = (LanguageModule) o;
    return Objects.equals(this.id, languageModel.id) &&
        Objects.equals(this.languageName, languageModel.languageName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, languageName);
  }

 /* @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LanguageModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    languageName: ").append(toIndentedString(languageName)).append("\n");
    sb.append("}");
    return sb.toString();
  }*/
 @Override
 public String toString() {
   return languageName;
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
