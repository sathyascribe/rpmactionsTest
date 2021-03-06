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
 * UserResponseModel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-24T12:51:03.671750900+05:30[Asia/Calcutta]")
public class UserResponseModel implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_FIRST_NAME = "firstName";
  @SerializedName(SERIALIZED_NAME_FIRST_NAME)
  private String firstName;

  public static final String SERIALIZED_NAME_LAST_NAME = "lastName";
  @SerializedName(SERIALIZED_NAME_LAST_NAME)
  private String lastName;

  public static final String SERIALIZED_NAME_FULL_NAME = "fullName";
  @SerializedName(SERIALIZED_NAME_FULL_NAME)
  private String fullName;

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_CONTACT_NUMBER = "contactNumber";
  @SerializedName(SERIALIZED_NAME_CONTACT_NUMBER)
  private String contactNumber;

  public static final String SERIALIZED_NAME_GENDER = "gender";
  @SerializedName(SERIALIZED_NAME_GENDER)
  private String gender;

  public static final String SERIALIZED_NAME_HEIGHT = "height";
  @SerializedName(SERIALIZED_NAME_HEIGHT)
  private Double height;

  public static final String SERIALIZED_NAME_HEIGHT_MEASUREMENT_NAME = "heightMeasurementName";
  @SerializedName(SERIALIZED_NAME_HEIGHT_MEASUREMENT_NAME)
  private String heightMeasurementName;

  public static final String SERIALIZED_NAME_WEIGHT = "weight";
  @SerializedName(SERIALIZED_NAME_WEIGHT)
  private Double weight;

  public static final String SERIALIZED_NAME_WEIGHT_MEASUREMENT_NAME = "weightMeasurementName";
  @SerializedName(SERIALIZED_NAME_WEIGHT_MEASUREMENT_NAME)
  private String weightMeasurementName;

  public static final String SERIALIZED_NAME_PROFILE_PHOTO = "profilePhoto";
  @SerializedName(SERIALIZED_NAME_PROFILE_PHOTO)
  private String profilePhoto;

  public static final String SERIALIZED_NAME_AGE = "age";
  @SerializedName(SERIALIZED_NAME_AGE)
  private Long age;

  public static final String SERIALIZED_NAME_GROUP = "group";
  @SerializedName(SERIALIZED_NAME_GROUP)
  private String group;

  public static final String SERIALIZED_NAME_COUNTRY_I_D = "countryID";
  @SerializedName(SERIALIZED_NAME_COUNTRY_I_D)
  private Long countryID;

  public static final String SERIALIZED_NAME_COUNTRY_NAME = "countryName";
  @SerializedName(SERIALIZED_NAME_COUNTRY_NAME)
  private String countryName;

  public static final String SERIALIZED_NAME_CITY_ID = "cityId";
  @SerializedName(SERIALIZED_NAME_CITY_ID)
  private Long cityId;

  public static final String SERIALIZED_NAME_CITY_NAME = "cityName";
  @SerializedName(SERIALIZED_NAME_CITY_NAME)
  private String cityName;

  public static final String SERIALIZED_NAME_PROVINCE_STATE_ID = "provinceStateId";
  @SerializedName(SERIALIZED_NAME_PROVINCE_STATE_ID)
  private Long provinceStateId;

  public static final String SERIALIZED_NAME_PROVINCE_STATE_NAME = "provinceStateName";
  @SerializedName(SERIALIZED_NAME_PROVINCE_STATE_NAME)
  private String provinceStateName;

  public static final String SERIALIZED_NAME_LANGUAGE_ID = "languageId";
  @SerializedName(SERIALIZED_NAME_LANGUAGE_ID)
  private Long languageId;

  public static final String SERIALIZED_NAME_LANGUAGE_NAME = "languageName";
  @SerializedName(SERIALIZED_NAME_LANGUAGE_NAME)
  private String languageName;

  public static final String SERIALIZED_NAME_DATE_OF_BIRTH = "dateOfBirth";
  @SerializedName(SERIALIZED_NAME_DATE_OF_BIRTH)
  private Date dateOfBirth;


  public UserResponseModel firstName(String firstName) {
    
    this.firstName = firstName;
    return this;
  }

   /**
   * Get firstName
   * @return firstName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getFirstName() {
    return firstName;
  }


  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public UserResponseModel lastName(String lastName) {
    
    this.lastName = lastName;
    return this;
  }

   /**
   * Get lastName
   * @return lastName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getLastName() {
    return lastName;
  }


  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public UserResponseModel fullName(String fullName) {
    
    this.fullName = fullName;
    return this;
  }

   /**
   * Get fullName
   * @return fullName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getFullName() {
    return fullName;
  }


  public void setFullName(String fullName) {
    this.fullName = fullName;
  }


  public UserResponseModel email(String email) {
    
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public UserResponseModel contactNumber(String contactNumber) {
    
    this.contactNumber = contactNumber;
    return this;
  }

   /**
   * Get contactNumber
   * @return contactNumber
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getContactNumber() {
    return contactNumber;
  }


  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }


  public UserResponseModel gender(String gender) {
    
    this.gender = gender;
    return this;
  }

   /**
   * Get gender
   * @return gender
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getGender() {
    return gender;
  }


  public void setGender(String gender) {
    this.gender = gender;
  }


  public UserResponseModel height(Double height) {
    
    this.height = height;
    return this;
  }

   /**
   * Get height
   * @return height
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Double getHeight() {
    return height;
  }


  public void setHeight(Double height) {
    this.height = height;
  }


  public UserResponseModel heightMeasurementName(String heightMeasurementName) {
    
    this.heightMeasurementName = heightMeasurementName;
    return this;
  }

   /**
   * Get heightMeasurementName
   * @return heightMeasurementName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getHeightMeasurementName() {
    return heightMeasurementName;
  }


  public void setHeightMeasurementName(String heightMeasurementName) {
    this.heightMeasurementName = heightMeasurementName;
  }


  public UserResponseModel weight(Double weight) {
    
    this.weight = weight;
    return this;
  }

   /**
   * Get weight
   * @return weight
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Double getWeight() {
    return weight;
  }


  public void setWeight(Double weight) {
    this.weight = weight;
  }


  public UserResponseModel weightMeasurementName(String weightMeasurementName) {
    
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


  public UserResponseModel profilePhoto(String profilePhoto) {
    
    this.profilePhoto = profilePhoto;
    return this;
  }

   /**
   * Get profilePhoto
   * @return profilePhoto
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getProfilePhoto() {
    return profilePhoto;
  }


  public void setProfilePhoto(String profilePhoto) {
    this.profilePhoto = profilePhoto;
  }


  public UserResponseModel age(Long age) {
    
    this.age = age;
    return this;
  }

   /**
   * Get age
   * @return age
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getAge() {
    return age;
  }


  public void setAge(Long age) {
    this.age = age;
  }


  public UserResponseModel group(String group) {
    
    this.group = group;
    return this;
  }

   /**
   * Get group
   * @return group
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getGroup() {
    return group;
  }


  public void setGroup(String group) {
    this.group = group;
  }


  public UserResponseModel countryID(Long countryID) {
    
    this.countryID = countryID;
    return this;
  }

   /**
   * Get countryID
   * @return countryID
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getCountryID() {
    return countryID;
  }


  public void setCountryID(Long countryID) {
    this.countryID = countryID;
  }


  public UserResponseModel countryName(String countryName) {
    
    this.countryName = countryName;
    return this;
  }

   /**
   * Get countryName
   * @return countryName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getCountryName() {
    return countryName;
  }


  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }


  public UserResponseModel cityId(Long cityId) {
    
    this.cityId = cityId;
    return this;
  }

   /**
   * Get cityId
   * @return cityId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getCityId() {
    return cityId;
  }


  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }


  public UserResponseModel cityName(String cityName) {
    
    this.cityName = cityName;
    return this;
  }

   /**
   * Get cityName
   * @return cityName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getCityName() {
    return cityName;
  }


  public void setCityName(String cityName) {
    this.cityName = cityName;
  }


  public UserResponseModel provinceStateId(Long provinceStateId) {
    
    this.provinceStateId = provinceStateId;
    return this;
  }

   /**
   * Get provinceStateId
   * @return provinceStateId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getProvinceStateId() {
    return provinceStateId;
  }


  public void setProvinceStateId(Long provinceStateId) {
    this.provinceStateId = provinceStateId;
  }


  public UserResponseModel provinceStateName(String provinceStateName) {
    
    this.provinceStateName = provinceStateName;
    return this;
  }

   /**
   * Get provinceStateName
   * @return provinceStateName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getProvinceStateName() {
    return provinceStateName;
  }


  public void setProvinceStateName(String provinceStateName) {
    this.provinceStateName = provinceStateName;
  }


  public UserResponseModel languageId(Long languageId) {
    
    this.languageId = languageId;
    return this;
  }

   /**
   * Get languageId
   * @return languageId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getLanguageId() {
    return languageId;
  }


  public void setLanguageId(Long languageId) {
    this.languageId = languageId;
  }


  public UserResponseModel languageName(String languageName) {
    
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


  public UserResponseModel dateOfBirth(Date dateOfBirth) {
    
    this.dateOfBirth = dateOfBirth;
    return this;
  }

   /**
   * Get dateOfBirth
   * @return dateOfBirth
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Date getDateOfBirth() {
    return dateOfBirth;
  }


  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserResponseModel userResponseModel = (UserResponseModel) o;
    return Objects.equals(this.firstName, userResponseModel.firstName) &&
        Objects.equals(this.lastName, userResponseModel.lastName) &&
        Objects.equals(this.fullName, userResponseModel.fullName) &&
        Objects.equals(this.email, userResponseModel.email) &&
        Objects.equals(this.contactNumber, userResponseModel.contactNumber) &&
        Objects.equals(this.gender, userResponseModel.gender) &&
        Objects.equals(this.height, userResponseModel.height) &&
        Objects.equals(this.heightMeasurementName, userResponseModel.heightMeasurementName) &&
        Objects.equals(this.weight, userResponseModel.weight) &&
        Objects.equals(this.weightMeasurementName, userResponseModel.weightMeasurementName) &&
        Objects.equals(this.profilePhoto, userResponseModel.profilePhoto) &&
        Objects.equals(this.age, userResponseModel.age) &&
        Objects.equals(this.group, userResponseModel.group) &&
        Objects.equals(this.countryID, userResponseModel.countryID) &&
        Objects.equals(this.countryName, userResponseModel.countryName) &&
        Objects.equals(this.cityId, userResponseModel.cityId) &&
        Objects.equals(this.cityName, userResponseModel.cityName) &&
        Objects.equals(this.provinceStateId, userResponseModel.provinceStateId) &&
        Objects.equals(this.provinceStateName, userResponseModel.provinceStateName) &&
        Objects.equals(this.languageId, userResponseModel.languageId) &&
        Objects.equals(this.languageName, userResponseModel.languageName) &&
        Objects.equals(this.dateOfBirth, userResponseModel.dateOfBirth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, fullName, email, contactNumber, gender, height, heightMeasurementName, weight, weightMeasurementName, profilePhoto, age, group, countryID, countryName, cityId, cityName, provinceStateId, provinceStateName, languageId, languageName, dateOfBirth);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResponseModel {\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    contactNumber: ").append(toIndentedString(contactNumber)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    heightMeasurementName: ").append(toIndentedString(heightMeasurementName)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
    sb.append("    weightMeasurementName: ").append(toIndentedString(weightMeasurementName)).append("\n");
    sb.append("    profilePhoto: ").append(toIndentedString(profilePhoto)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    group: ").append(toIndentedString(group)).append("\n");
    sb.append("    countryID: ").append(toIndentedString(countryID)).append("\n");
    sb.append("    countryName: ").append(toIndentedString(countryName)).append("\n");
    sb.append("    cityId: ").append(toIndentedString(cityId)).append("\n");
    sb.append("    cityName: ").append(toIndentedString(cityName)).append("\n");
    sb.append("    provinceStateId: ").append(toIndentedString(provinceStateId)).append("\n");
    sb.append("    provinceStateName: ").append(toIndentedString(provinceStateName)).append("\n");
    sb.append("    languageId: ").append(toIndentedString(languageId)).append("\n");
    sb.append("    languageName: ").append(toIndentedString(languageName)).append("\n");
    sb.append("    dateOfBirth: ").append(toIndentedString(dateOfBirth)).append("\n");
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

