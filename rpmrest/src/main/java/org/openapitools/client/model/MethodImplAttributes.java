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
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Gets or Sets MethodImplAttributes
 */
@JsonAdapter(MethodImplAttributes.Adapter.class)
public enum MethodImplAttributes {
  
  NUMBER_0(0),
  
  NUMBER_1(1),
  
  NUMBER_2(2),
  
  NUMBER_3(3),
  
  NUMBER_4(4),
  
  NUMBER_8(8),
  
  NUMBER_16(16),
  
  NUMBER_32(32),
  
  NUMBER_64(64),
  
  NUMBER_128(128),
  
  NUMBER_256(256),
  
  NUMBER_512(512),
  
  NUMBER_4096(4096),
  
  NUMBER_65535(65535);

  private Integer value;

  MethodImplAttributes(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static MethodImplAttributes fromValue(Integer value) {
    for (MethodImplAttributes b : MethodImplAttributes.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<MethodImplAttributes> {
    @Override
    public void write(final JsonWriter jsonWriter, final MethodImplAttributes enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public MethodImplAttributes read(final JsonReader jsonReader) throws IOException {
      Integer value = jsonReader.nextInt();
      return MethodImplAttributes.fromValue(value);
    }
  }
}

