package com.example.docmanagement.models.responses;

import com.google.gson.annotations.SerializedName;

public class DocumentStatusResponse {

  @SerializedName("id")
  private int id;
  @SerializedName("document_id")
  private int documentId;
  @SerializedName("status")
  private String status;
  @SerializedName("time_created")
  private String timeCreated;
  @SerializedName("status_name")
  private String statusName;

  public DocumentStatusResponse(
    int id,
    int documentId,
    String status,
    String timeCreated,
    String statusName
  ) {
    this.id = id;
    this.documentId = documentId;
    this.status = status;
    this.timeCreated = timeCreated;
    this.statusName = statusName;
  }

  public int getId() {
    return id;
  }

  public int getDocumentId() {
    return documentId;
  }

  public String getStatusName() {
    return statusName;
  }

  public String getTimeCreated() {
    return timeCreated;
  }
}
