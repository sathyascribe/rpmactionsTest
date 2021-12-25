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
 * ModuleHandle
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-06T18:15:43.848127100+05:30[Asia/Calcutta]")
public class ModuleHandle implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_MD_STREAM_VERSION = "mdStreamVersion";
  @SerializedName(SERIALIZED_NAME_MD_STREAM_VERSION)
  private Integer mdStreamVersion;


   /**
   * Get mdStreamVersion
   * @return mdStreamVersion
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getMdStreamVersion() {
    return mdStreamVersion;
  }




  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModuleHandle moduleHandle = (ModuleHandle) o;
    return Objects.equals(this.mdStreamVersion, moduleHandle.mdStreamVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mdStreamVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModuleHandle {\n");
    sb.append("    mdStreamVersion: ").append(toIndentedString(mdStreamVersion)).append("\n");
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
