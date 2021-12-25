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
import org.openapitools.client.model.CallingConventions;
import org.openapitools.client.model.CustomAttributeData;
import org.openapitools.client.model.MemberTypes;
import org.openapitools.client.model.MethodAttributes;
import org.openapitools.client.model.MethodImplAttributes;
import org.openapitools.client.model.Module;
import org.openapitools.client.model.RuntimeMethodHandle;
import org.openapitools.client.model.Type;
import java.io.Serializable;

/**
 * ConstructorInfo
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-06T18:15:43.848127100+05:30[Asia/Calcutta]")
public class ConstructorInfo implements Serializable {
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

  public static final String SERIALIZED_NAME_ATTRIBUTES = "attributes";
  @SerializedName(SERIALIZED_NAME_ATTRIBUTES)
  private MethodAttributes attributes;

  public static final String SERIALIZED_NAME_METHOD_IMPLEMENTATION_FLAGS = "methodImplementationFlags";
  @SerializedName(SERIALIZED_NAME_METHOD_IMPLEMENTATION_FLAGS)
  private MethodImplAttributes methodImplementationFlags;

  public static final String SERIALIZED_NAME_CALLING_CONVENTION = "callingConvention";
  @SerializedName(SERIALIZED_NAME_CALLING_CONVENTION)
  private CallingConventions callingConvention;

  public static final String SERIALIZED_NAME_IS_ABSTRACT = "isAbstract";
  @SerializedName(SERIALIZED_NAME_IS_ABSTRACT)
  private Boolean isAbstract;

  public static final String SERIALIZED_NAME_IS_CONSTRUCTOR = "isConstructor";
  @SerializedName(SERIALIZED_NAME_IS_CONSTRUCTOR)
  private Boolean isConstructor;

  public static final String SERIALIZED_NAME_IS_FINAL = "isFinal";
  @SerializedName(SERIALIZED_NAME_IS_FINAL)
  private Boolean isFinal;

  public static final String SERIALIZED_NAME_IS_HIDE_BY_SIG = "isHideBySig";
  @SerializedName(SERIALIZED_NAME_IS_HIDE_BY_SIG)
  private Boolean isHideBySig;

  public static final String SERIALIZED_NAME_IS_SPECIAL_NAME = "isSpecialName";
  @SerializedName(SERIALIZED_NAME_IS_SPECIAL_NAME)
  private Boolean isSpecialName;

  public static final String SERIALIZED_NAME_IS_STATIC = "isStatic";
  @SerializedName(SERIALIZED_NAME_IS_STATIC)
  private Boolean isStatic;

  public static final String SERIALIZED_NAME_IS_VIRTUAL = "isVirtual";
  @SerializedName(SERIALIZED_NAME_IS_VIRTUAL)
  private Boolean isVirtual;

  public static final String SERIALIZED_NAME_IS_ASSEMBLY = "isAssembly";
  @SerializedName(SERIALIZED_NAME_IS_ASSEMBLY)
  private Boolean isAssembly;

  public static final String SERIALIZED_NAME_IS_FAMILY = "isFamily";
  @SerializedName(SERIALIZED_NAME_IS_FAMILY)
  private Boolean isFamily;

  public static final String SERIALIZED_NAME_IS_FAMILY_AND_ASSEMBLY = "isFamilyAndAssembly";
  @SerializedName(SERIALIZED_NAME_IS_FAMILY_AND_ASSEMBLY)
  private Boolean isFamilyAndAssembly;

  public static final String SERIALIZED_NAME_IS_FAMILY_OR_ASSEMBLY = "isFamilyOrAssembly";
  @SerializedName(SERIALIZED_NAME_IS_FAMILY_OR_ASSEMBLY)
  private Boolean isFamilyOrAssembly;

  public static final String SERIALIZED_NAME_IS_PRIVATE = "isPrivate";
  @SerializedName(SERIALIZED_NAME_IS_PRIVATE)
  private Boolean isPrivate;

  public static final String SERIALIZED_NAME_IS_PUBLIC = "isPublic";
  @SerializedName(SERIALIZED_NAME_IS_PUBLIC)
  private Boolean isPublic;

  public static final String SERIALIZED_NAME_IS_CONSTRUCTED_GENERIC_METHOD = "isConstructedGenericMethod";
  @SerializedName(SERIALIZED_NAME_IS_CONSTRUCTED_GENERIC_METHOD)
  private Boolean isConstructedGenericMethod;

  public static final String SERIALIZED_NAME_IS_GENERIC_METHOD = "isGenericMethod";
  @SerializedName(SERIALIZED_NAME_IS_GENERIC_METHOD)
  private Boolean isGenericMethod;

  public static final String SERIALIZED_NAME_IS_GENERIC_METHOD_DEFINITION = "isGenericMethodDefinition";
  @SerializedName(SERIALIZED_NAME_IS_GENERIC_METHOD_DEFINITION)
  private Boolean isGenericMethodDefinition;

  public static final String SERIALIZED_NAME_CONTAINS_GENERIC_PARAMETERS = "containsGenericParameters";
  @SerializedName(SERIALIZED_NAME_CONTAINS_GENERIC_PARAMETERS)
  private Boolean containsGenericParameters;

  public static final String SERIALIZED_NAME_METHOD_HANDLE = "methodHandle";
  @SerializedName(SERIALIZED_NAME_METHOD_HANDLE)
  private RuntimeMethodHandle methodHandle;

  public static final String SERIALIZED_NAME_IS_SECURITY_CRITICAL = "isSecurityCritical";
  @SerializedName(SERIALIZED_NAME_IS_SECURITY_CRITICAL)
  private Boolean isSecurityCritical;

  public static final String SERIALIZED_NAME_IS_SECURITY_SAFE_CRITICAL = "isSecuritySafeCritical";
  @SerializedName(SERIALIZED_NAME_IS_SECURITY_SAFE_CRITICAL)
  private Boolean isSecuritySafeCritical;

  public static final String SERIALIZED_NAME_IS_SECURITY_TRANSPARENT = "isSecurityTransparent";
  @SerializedName(SERIALIZED_NAME_IS_SECURITY_TRANSPARENT)
  private Boolean isSecurityTransparent;

  public static final String SERIALIZED_NAME_MEMBER_TYPE = "memberType";
  @SerializedName(SERIALIZED_NAME_MEMBER_TYPE)
  private MemberTypes memberType;


   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }




  public ConstructorInfo declaringType(Type declaringType) {
    
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


  public ConstructorInfo reflectedType(Type reflectedType) {
    
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


  public ConstructorInfo module(Module module) {
    
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




  public ConstructorInfo attributes(MethodAttributes attributes) {
    
    this.attributes = attributes;
    return this;
  }

   /**
   * Get attributes
   * @return attributes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public MethodAttributes getAttributes() {
    return attributes;
  }


  public void setAttributes(MethodAttributes attributes) {
    this.attributes = attributes;
  }


  public ConstructorInfo methodImplementationFlags(MethodImplAttributes methodImplementationFlags) {
    
    this.methodImplementationFlags = methodImplementationFlags;
    return this;
  }

   /**
   * Get methodImplementationFlags
   * @return methodImplementationFlags
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public MethodImplAttributes getMethodImplementationFlags() {
    return methodImplementationFlags;
  }


  public void setMethodImplementationFlags(MethodImplAttributes methodImplementationFlags) {
    this.methodImplementationFlags = methodImplementationFlags;
  }


  public ConstructorInfo callingConvention(CallingConventions callingConvention) {
    
    this.callingConvention = callingConvention;
    return this;
  }

   /**
   * Get callingConvention
   * @return callingConvention
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public CallingConventions getCallingConvention() {
    return callingConvention;
  }


  public void setCallingConvention(CallingConventions callingConvention) {
    this.callingConvention = callingConvention;
  }


   /**
   * Get isAbstract
   * @return isAbstract
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsAbstract() {
    return isAbstract;
  }




   /**
   * Get isConstructor
   * @return isConstructor
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsConstructor() {
    return isConstructor;
  }




   /**
   * Get isFinal
   * @return isFinal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsFinal() {
    return isFinal;
  }




   /**
   * Get isHideBySig
   * @return isHideBySig
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsHideBySig() {
    return isHideBySig;
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




   /**
   * Get isStatic
   * @return isStatic
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsStatic() {
    return isStatic;
  }




   /**
   * Get isVirtual
   * @return isVirtual
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsVirtual() {
    return isVirtual;
  }




   /**
   * Get isAssembly
   * @return isAssembly
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsAssembly() {
    return isAssembly;
  }




   /**
   * Get isFamily
   * @return isFamily
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsFamily() {
    return isFamily;
  }




   /**
   * Get isFamilyAndAssembly
   * @return isFamilyAndAssembly
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsFamilyAndAssembly() {
    return isFamilyAndAssembly;
  }




   /**
   * Get isFamilyOrAssembly
   * @return isFamilyOrAssembly
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsFamilyOrAssembly() {
    return isFamilyOrAssembly;
  }




   /**
   * Get isPrivate
   * @return isPrivate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsPrivate() {
    return isPrivate;
  }




   /**
   * Get isPublic
   * @return isPublic
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsPublic() {
    return isPublic;
  }




   /**
   * Get isConstructedGenericMethod
   * @return isConstructedGenericMethod
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsConstructedGenericMethod() {
    return isConstructedGenericMethod;
  }




   /**
   * Get isGenericMethod
   * @return isGenericMethod
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsGenericMethod() {
    return isGenericMethod;
  }




   /**
   * Get isGenericMethodDefinition
   * @return isGenericMethodDefinition
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsGenericMethodDefinition() {
    return isGenericMethodDefinition;
  }




   /**
   * Get containsGenericParameters
   * @return containsGenericParameters
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getContainsGenericParameters() {
    return containsGenericParameters;
  }




  public ConstructorInfo methodHandle(RuntimeMethodHandle methodHandle) {
    
    this.methodHandle = methodHandle;
    return this;
  }

   /**
   * Get methodHandle
   * @return methodHandle
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public RuntimeMethodHandle getMethodHandle() {
    return methodHandle;
  }


  public void setMethodHandle(RuntimeMethodHandle methodHandle) {
    this.methodHandle = methodHandle;
  }


   /**
   * Get isSecurityCritical
   * @return isSecurityCritical
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsSecurityCritical() {
    return isSecurityCritical;
  }




   /**
   * Get isSecuritySafeCritical
   * @return isSecuritySafeCritical
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsSecuritySafeCritical() {
    return isSecuritySafeCritical;
  }




   /**
   * Get isSecurityTransparent
   * @return isSecurityTransparent
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsSecurityTransparent() {
    return isSecurityTransparent;
  }




  public ConstructorInfo memberType(MemberTypes memberType) {
    
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstructorInfo constructorInfo = (ConstructorInfo) o;
    return Objects.equals(this.name, constructorInfo.name) &&
        Objects.equals(this.declaringType, constructorInfo.declaringType) &&
        Objects.equals(this.reflectedType, constructorInfo.reflectedType) &&
        Objects.equals(this.module, constructorInfo.module) &&
        Objects.equals(this.customAttributes, constructorInfo.customAttributes) &&
        Objects.equals(this.isCollectible, constructorInfo.isCollectible) &&
        Objects.equals(this.metadataToken, constructorInfo.metadataToken) &&
        Objects.equals(this.attributes, constructorInfo.attributes) &&
        Objects.equals(this.methodImplementationFlags, constructorInfo.methodImplementationFlags) &&
        Objects.equals(this.callingConvention, constructorInfo.callingConvention) &&
        Objects.equals(this.isAbstract, constructorInfo.isAbstract) &&
        Objects.equals(this.isConstructor, constructorInfo.isConstructor) &&
        Objects.equals(this.isFinal, constructorInfo.isFinal) &&
        Objects.equals(this.isHideBySig, constructorInfo.isHideBySig) &&
        Objects.equals(this.isSpecialName, constructorInfo.isSpecialName) &&
        Objects.equals(this.isStatic, constructorInfo.isStatic) &&
        Objects.equals(this.isVirtual, constructorInfo.isVirtual) &&
        Objects.equals(this.isAssembly, constructorInfo.isAssembly) &&
        Objects.equals(this.isFamily, constructorInfo.isFamily) &&
        Objects.equals(this.isFamilyAndAssembly, constructorInfo.isFamilyAndAssembly) &&
        Objects.equals(this.isFamilyOrAssembly, constructorInfo.isFamilyOrAssembly) &&
        Objects.equals(this.isPrivate, constructorInfo.isPrivate) &&
        Objects.equals(this.isPublic, constructorInfo.isPublic) &&
        Objects.equals(this.isConstructedGenericMethod, constructorInfo.isConstructedGenericMethod) &&
        Objects.equals(this.isGenericMethod, constructorInfo.isGenericMethod) &&
        Objects.equals(this.isGenericMethodDefinition, constructorInfo.isGenericMethodDefinition) &&
        Objects.equals(this.containsGenericParameters, constructorInfo.containsGenericParameters) &&
        Objects.equals(this.methodHandle, constructorInfo.methodHandle) &&
        Objects.equals(this.isSecurityCritical, constructorInfo.isSecurityCritical) &&
        Objects.equals(this.isSecuritySafeCritical, constructorInfo.isSecuritySafeCritical) &&
        Objects.equals(this.isSecurityTransparent, constructorInfo.isSecurityTransparent) &&
        Objects.equals(this.memberType, constructorInfo.memberType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, declaringType, reflectedType, module, customAttributes, isCollectible, metadataToken, attributes, methodImplementationFlags, callingConvention, isAbstract, isConstructor, isFinal, isHideBySig, isSpecialName, isStatic, isVirtual, isAssembly, isFamily, isFamilyAndAssembly, isFamilyOrAssembly, isPrivate, isPublic, isConstructedGenericMethod, isGenericMethod, isGenericMethodDefinition, containsGenericParameters, methodHandle, isSecurityCritical, isSecuritySafeCritical, isSecurityTransparent, memberType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstructorInfo {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    declaringType: ").append(toIndentedString(declaringType)).append("\n");
    sb.append("    reflectedType: ").append(toIndentedString(reflectedType)).append("\n");
    sb.append("    module: ").append(toIndentedString(module)).append("\n");
    sb.append("    customAttributes: ").append(toIndentedString(customAttributes)).append("\n");
    sb.append("    isCollectible: ").append(toIndentedString(isCollectible)).append("\n");
    sb.append("    metadataToken: ").append(toIndentedString(metadataToken)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    methodImplementationFlags: ").append(toIndentedString(methodImplementationFlags)).append("\n");
    sb.append("    callingConvention: ").append(toIndentedString(callingConvention)).append("\n");
    sb.append("    isAbstract: ").append(toIndentedString(isAbstract)).append("\n");
    sb.append("    isConstructor: ").append(toIndentedString(isConstructor)).append("\n");
    sb.append("    isFinal: ").append(toIndentedString(isFinal)).append("\n");
    sb.append("    isHideBySig: ").append(toIndentedString(isHideBySig)).append("\n");
    sb.append("    isSpecialName: ").append(toIndentedString(isSpecialName)).append("\n");
    sb.append("    isStatic: ").append(toIndentedString(isStatic)).append("\n");
    sb.append("    isVirtual: ").append(toIndentedString(isVirtual)).append("\n");
    sb.append("    isAssembly: ").append(toIndentedString(isAssembly)).append("\n");
    sb.append("    isFamily: ").append(toIndentedString(isFamily)).append("\n");
    sb.append("    isFamilyAndAssembly: ").append(toIndentedString(isFamilyAndAssembly)).append("\n");
    sb.append("    isFamilyOrAssembly: ").append(toIndentedString(isFamilyOrAssembly)).append("\n");
    sb.append("    isPrivate: ").append(toIndentedString(isPrivate)).append("\n");
    sb.append("    isPublic: ").append(toIndentedString(isPublic)).append("\n");
    sb.append("    isConstructedGenericMethod: ").append(toIndentedString(isConstructedGenericMethod)).append("\n");
    sb.append("    isGenericMethod: ").append(toIndentedString(isGenericMethod)).append("\n");
    sb.append("    isGenericMethodDefinition: ").append(toIndentedString(isGenericMethodDefinition)).append("\n");
    sb.append("    containsGenericParameters: ").append(toIndentedString(containsGenericParameters)).append("\n");
    sb.append("    methodHandle: ").append(toIndentedString(methodHandle)).append("\n");
    sb.append("    isSecurityCritical: ").append(toIndentedString(isSecurityCritical)).append("\n");
    sb.append("    isSecuritySafeCritical: ").append(toIndentedString(isSecuritySafeCritical)).append("\n");
    sb.append("    isSecurityTransparent: ").append(toIndentedString(isSecurityTransparent)).append("\n");
    sb.append("    memberType: ").append(toIndentedString(memberType)).append("\n");
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

