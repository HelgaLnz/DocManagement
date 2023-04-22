package com.example.docmanagement.models.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DealResponse {

  @SerializedName("id")
  private int id;
  @SerializedName("user_id")
  private int userId;
  @SerializedName("to_user_id")
  private int toUserId;
  @SerializedName("name")
  private String name;
  @SerializedName("time_created")
  private String timeCreated;
  @SerializedName("deal_statuses")
  private List<DealStatusResponse> dealStatuses;
  @SerializedName("documents")
  private List<DocumentResponse> documents;

  public DealResponse(
    int id,
    int userId,
    int toUserId,
    String name,
    String timeCreated,
    List<DealStatusResponse> dealStatuses,
    List<DocumentResponse> documents
  ) {
    this.id = id;
    this.userId = userId;
    this.toUserId = toUserId;
    this.name = name;
    this.timeCreated = timeCreated;
    this.dealStatuses = dealStatuses;
    this.documents = documents;
  }

  public int getId() {
    return id;
  }

  public int getUserId() {
    return userId;
  }

  public int getToUserId() {
    return toUserId;
  }

  public String getName() {
    return name;
  }

  public String getTimeCreated() {
    return timeCreated;
  }

  public List<DealStatusResponse> getDealStatuses() {
    return dealStatuses;
  }

  public List<DocumentResponse> getDocuments() {
    return documents;
  }

  @Override
  public String toString() {
    return "DealResponse{" +
      "id=" + id +
      ", userId=" + userId +
      ", toUserId=" + toUserId +
      ", name='" + name + '\'' +
      ", timeCreated='" + timeCreated + '\'' +
      ", dealStatuses=" + dealStatuses +
      ", documents=" + documents +
      '}';
  }
}
