package com.example.docmanagement.models.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DocumentResponse {

  @SerializedName("id")
  private int id;
  @SerializedName("deal_id")
  private int dealId;
  @SerializedName("user_id")
  private int userId;
  @SerializedName("to_user_id")
  private int toUserId;
  @SerializedName("url")
  private String url;
  @SerializedName("name")
  private String name;
  @SerializedName("time_created")
  private String timeCreated;
  @SerializedName("document_statuses")
  private List<DocumentStatusResponse> documentStatuses;

  public DocumentResponse(
    int id,
    int dealId,
    int userId,
    int toUserId,
    String url,
    String name,
    String timeCreated,
    List<DocumentStatusResponse> documentStatuses
  ) {
    this.id = id;
    this.dealId = dealId;
    this.userId = userId;
    this.toUserId = toUserId;
    this.url = url;
    this.name = name;
    this.timeCreated = timeCreated;
    this.documentStatuses = documentStatuses;
  }

  public int getId() {
    return id;
  }

  public int getDealId() {
    return dealId;
  }

  public int getUserId() {
    return userId;
  }

  public int getToUserId() {
    return toUserId;
  }

  public String getUrl() {
    return url;
  }

  public String getName() {
    return name;
  }

  public String getTimeCreated() {
    return timeCreated;
  }

  public void setDealId(int dealId) {
    this.dealId = dealId;
  }

  public List<DocumentStatusResponse> getDocumentStatuses() {
    return documentStatuses;
  }
}
