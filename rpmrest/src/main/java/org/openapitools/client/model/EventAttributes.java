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
 * Gets or Sets EventAttributes
 */
@JsonAdapter(EventAttributes.Adapter.class)
public enum EventAttributes {
  
  NUMBER_0(0),
  
  NUMBER_512(512),
  
  NUMBER_1024(1024);

  private Integer value;

  EventAttributes(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static EventAttributes fromValue(Integer value) {
    for (EventAttributes b : EventAttributes.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<EventAttributes> {
    @Override
    public void write(final JsonWriter jsonWriter, final EventAttributes enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public EventAttributes read(final JsonReader jsonReader) throws IOException {
      Integer value = jsonReader.nextInt();
      return EventAttributes.fromValue(value);
    }
  }
}

