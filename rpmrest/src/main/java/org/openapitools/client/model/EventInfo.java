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
import java.util.ArrayList;
import java.util.List;
import org.openapitools.client.model.CustomAttributeData;
import org.openapitools.client.model.EventAttributes;
import org.openapitools.client.model.MemberTypes;
import org.openapitools.client.model.MethodInfo;
import org.openapitools.client.model.Module;
import org.openapitools.client.model.Type;
import java.io.Serializable;

/**
 * EventInfo
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-06T18:15:43.848127100+05:30[Asia/Calcutta]")
public class EventInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_DECLARING_TYPE = "declaringType";
  @SerializedName(SERIALIZED_NAME_DECLARING_TYPE)
  private Type declaringType;

  public static final String SERIALIZED_NAME_REFLECTED_TYPE = "reflectedType";
  @SerializedName(SERIALIZED_NAME_REFLECTED_TYPE)
  private Type reflectedType;

  public static final String SERIALIZED_NAME_MODULE = "module";
  @SerializedName(SERIALIZED_NAME_MODULE)
  private Module module;

  public static final String SERIALIZED_NAME_CUSTOM_ATTRIBUTES = "customAttributes";
  @SerializedName(SERIALIZED_NAME_CUSTOM_ATTRIBUTES)
  private List<CustomAttributeData> customAttributes = null;

  public static final String SERIALIZED_NAME_IS_COLLECTIBLE = "isCollectible";
  @SerializedName(SERIALIZED_NAME_IS_COLLECTIBLE)
  private Boolean isCollectible;

  public static final String SERIALIZED_NAME_METADATA_TOKEN = "metadataToken";
  @SerializedName(SERIALIZED_NAME_METADATA_TOKEN)
  private Integer metadataToken;

  public static final String SERIALIZED_NAME_MEMBER_TYPE = "memberType";
  @SerializedName(SERIALIZED_NAME_MEMBER_TYPE)
  private MemberTypes memberType;

  public static final String SERIALIZED_NAME_ATTRIBUTES = "attributes";
  @SerializedName(SERIALIZED_NAME_ATTRIBUTES)
  private EventAttributes attributes;

  public static final String SERIALIZED_NAME_IS_SPECIAL_NAME = "isSpecialName";
  @SerializedName(SERIALIZED_NAME_IS_SPECIAL_NAME)
  private Boolean isSpecialName;

  public static final String SERIALIZED_NAME_ADD_METHOD = "addMethod";
  @SerializedName(SERIALIZED_NAME_ADD_METHOD)
  private MethodInfo addMethod;

  public static final String SERIALIZED_NAME_REMOVE_METHOD = "removeMethod";
  @SerializedName(SERIALIZED_NAME_REMOVE_METHOD)
  private MethodInfo removeMethod;

  public static final String SERIALIZED_NAME_RAISE_METHOD = "raiseMethod";
  @SerializedName(SERIALIZED_NAME_RAISE_METHOD)
  private MethodInfo raiseMethod;

  public static final String SERIALIZED_NAME_IS_MULTICAST = "isMulticast";
  @SerializedName(SERIALIZED_NAME_IS_MULTICAST)
  private Boolean isMulticast;

  public static final String SERIALIZED_NAME_EVENT_HANDLER_TYPE = "eventHandlerType";
  @SerializedName(SERIALIZED_NAME_EVENT_HANDLER_TYPE)
  private Type eventHandlerType;


   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }




  public EventInfo declaringType(Type declaringType) {
    
    this.declaringType = declaringType;
    return this;
  }

   /**
   * Get declaringType
   * @return declaringType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Type getDeclaringType() {
    return declaringType;
  }


  public void setDeclaringType(Type declaringType) {
    this.declaringType = declaringType;
  }


  public EventInfo reflectedType(Type reflectedType) {
    
    this.reflectedType = reflectedType;
    return this;
  }

   /**
   * Get reflectedType
   * @return reflectedType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Type getReflectedType() {
    return reflectedType;
  }


  public void setReflectedType(Type reflectedType) {
    this.reflectedType = reflectedType;
  }


  public EventInfo module(Module module) {
    
    this.module = module;
    return this;
  }

   /**
   * Get module
   * @return module
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Module getModule() {
    return module;
  }


  public void setModule(Module module) {
    this.module = module;
  }


   /**
   * Get customAttributes
   * @return customAttributes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<CustomAttributeData> getCustomAttributes() {
    return customAttributes;
  }




   /**
   * Get isCollectible
   * @return isCollectible
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsCollectible() {
    return isCollectible;
  }




   /**
   * Get metadataToken
   * @return metadataToken
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getMetadataToken() {
    return metadataToken;
  }




  public EventInfo memberType(MemberTypes memberType) {
    
    this.memberType = memberType;
    return this;
  }

   /**
   * Get memberType
   * @return memberType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public MemberTypes getMemberType() {
    return memberType;
  }


  public void setMemberType(MemberTypes memberType) {
    this.memberType = memberType;
  }


  public EventInfo attributes(EventAttributes attributes) {
    
    this.attributes = attributes;
    return this;
  }

   /**
   * Get attributes
   * @return attributes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public EventAttributes getAttributes() {
    return attributes;
  }


  public void setAttributes(EventAttributes attributes) {
    this.attributes = attributes;
  }


   /**
   * Get isSpecialName
   * @return isSpecialName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsSpecialName() {
    return isSpecialName;
  }




  public EventInfo addMethod(MethodInfo addMethod) {
    
    this.addMethod = addMethod;
    return this;
  }

   /**
   * Get addMethod
   * @return addMethod
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public MethodInfo getAddMethod() {
    return addMethod;
  }


  public void setAddMethod(MethodInfo addMethod) {
    this.addMethod = addMethod;
  }


  public EventInfo removeMethod(MethodInfo removeMethod) {
    
    this.removeMethod = removeMethod;
    return this;
  }

   /**
   * Get removeMethod
   * @return removeMethod
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public MethodInfo getRemoveMethod() {
    return removeMethod;
  }


  public void setRemoveMethod(MethodInfo removeMethod) {
    this.removeMethod = removeMethod;
  }


  public EventInfo raiseMethod(MethodInfo raiseMethod) {
    
    this.raiseMethod = raiseMethod;
    return this;
  }

   /**
   * Get raiseMethod
   * @return raiseMethod
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public MethodInfo getRaiseMethod() {
    return raiseMethod;
  }


  public void setRaiseMethod(MethodInfo raiseMethod) {
    this.raiseMethod = raiseMethod;
  }


   /**
   * Get isMulticast
   * @return isMulticast
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsMulticast() {
    return isMulticast;
  }




  public EventInfo eventHandlerType(Type eventHandlerType) {
    
    this.eventHandlerType = eventHandlerType;
    return this;
  }

   /**
   * Get eventHandlerType
   * @return eventHandlerType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Type getEventHandlerType() {
    return eventHandlerType;
  }


  public void setEventHandlerType(Type eventHandlerType) {
    this.eventHandlerType = eventHandlerType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventInfo eventInfo = (EventInfo) o;
    return Objects.equals(this.name, eventInfo.name) &&
        Objects.equals(this.declaringType, eventInfo.declaringType) &&
        Objects.equals(this.reflectedType, eventInfo.reflectedType) &&
        Objects.equals(this.module, eventInfo.module) &&
        Objects.equals(this.customAttributes, eventInfo.customAttributes) &&
        Objects.equals(this.isCollectible, eventInfo.isCollectible) &&
        Objects.equals(this.metadataToken, eventInfo.metadataToken) &&
        Objects.equals(this.memberType, eventInfo.memberType) &&
        Objects.equals(this.attributes, eventInfo.attributes) &&
        Objects.equals(this.isSpecialName, eventInfo.isSpecialName) &&
        Objects.equals(this.addMethod, eventInfo.addMethod) &&
        Objects.equals(this.removeMethod, eventInfo.removeMethod) &&
        Objects.equals(this.raiseMethod, eventInfo.raiseMethod) &&
        Objects.equals(this.isMulticast, eventInfo.isMulticast) &&
        Objects.equals(this.eventHandlerType, eventInfo.eventHandlerType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, declaringType, reflectedType, module, customAttributes, isCollectible, metadataToken, memberType, attributes, isSpecialName, addMethod, removeMethod, raiseMethod, isMulticast, eventHandlerType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventInfo {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    declaringType: ").append(toIndentedString(declaringType)).append("\n");
    sb.append("    reflectedType: ").append(toIndentedString(reflectedType)).append("\n");
    sb.append("    module: ").append(toIndentedString(module)).append("\n");
    sb.append("    customAttributes: ").append(toIndentedString(customAttributes)).append("\n");
    sb.append("    isCollectible: ").append(toIndentedString(isCollectible)).append("\n");
    sb.append("    metadataToken: ").append(toIndentedString(metadataToken)).append("\n");
    sb.append("    memberType: ").append(toIndentedString(memberType)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    isSpecialName: ").append(toIndentedString(isSpecialName)).append("\n");
    sb.append("    addMethod: ").append(toIndentedString(addMethod)).append("\n");
    sb.append("    removeMethod: ").append(toIndentedString(removeMethod)).append("\n");
    sb.append("    raiseMethod: ").append(toIndentedString(raiseMethod)).append("\n");
    sb.append("    isMulticast: ").append(toIndentedString(isMulticast)).append("\n");
    sb.append("    eventHandlerType: ").append(toIndentedString(eventHandlerType)).append("\n");
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
