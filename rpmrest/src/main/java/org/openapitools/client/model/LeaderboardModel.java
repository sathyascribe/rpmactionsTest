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
 * LeaderboardModel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-12-24T12:51:03.671750900+05:30[Asia/Calcutta]")
public class LeaderboardModel implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_PARTICIPANT_ID = "participantId";
  @SerializedName(SERIALIZED_NAME_PARTICIPANT_ID)
  private Long participantId;

  public static final String SERIALIZED_NAME_PARTICIPANT_NAME = "participantName";
  @SerializedName(SERIALIZED_NAME_PARTICIPANT_NAME)
  private String participantName;

  public static final String SERIALIZED_NAME_DISTANCE = "distance";
  @SerializedName(SERIALIZED_NAME_DISTANCE)
  private Double distance;

  public static final String SERIALIZED_NAME_LEADERBOARD_TIME = "leaderboardTime";
  @SerializedName(SERIALIZED_NAME_LEADERBOARD_TIME)
  private Date leaderboardTime;


  public LeaderboardModel participantId(Long participantId) {
    
    this.participantId = participantId;
    return this;
  }

   /**
   * Get participantId
   * @return participantId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getParticipantId() {
    return participantId;
  }


  public void setParticipantId(Long participantId) {
    this.participantId = participantId;
  }


  public LeaderboardModel participantName(String participantName) {
    
    this.participantName = participantName;
    return this;
  }

   /**
   * Get participantName
   * @return participantName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getParticipantName() {
    return participantName;
  }


  public void setParticipantName(String participantName) {
    this.participantName = participantName;
  }


  public LeaderboardModel distance(Double distance) {
    
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


  public LeaderboardModel leaderboardTime(Date leaderboardTime) {
    
    this.leaderboardTime = leaderboardTime;
    return this;
  }

   /**
   * Get leaderboardTime
   * @return leaderboardTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Date getLeaderboardTime() {
    return leaderboardTime;
  }


  public void setLeaderboardTime(Date leaderboardTime) {
    this.leaderboardTime = leaderboardTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaderboardModel leaderboardModel = (LeaderboardModel) o;
    return Objects.equals(this.participantId, leaderboardModel.participantId) &&
        Objects.equals(this.participantName, leaderboardModel.participantName) &&
        Objects.equals(this.distance, leaderboardModel.distance) &&
        Objects.equals(this.leaderboardTime, leaderboardModel.leaderboardTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(participantId, participantName, distance, leaderboardTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaderboardModel {\n");
    sb.append("    participantId: ").append(toIndentedString(participantId)).append("\n");
    sb.append("    participantName: ").append(toIndentedString(participantName)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    leaderboardTime: ").append(toIndentedString(leaderboardTime)).append("\n");
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
