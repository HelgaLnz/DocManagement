package com.example.docmanagement.models.responses;

import com.google.gson.annotations.SerializedName;

public class DealStatusResponse {

  @SerializedName("id")
  private int id;
  @SerializedName("deal_id")
  private int dealId;
  @SerializedName("status")
  private String status;
  @SerializedName("time_created")
  private String timeCreated;
  @SerializedName("status_name")
  private String statusName;

  public DealStatusResponse(
    int id,
    int dealId,
    String status,
    String timeCreated,
    String statusName
  ) {
    this.id = id;
    this.dealId = dealId;
    this.status = status;
    this.timeCreated = timeCreated;
    this.statusName = statusName;
  }

  public int getId() {
    return id;
  }

  public int getDealId() {
    return dealId;
  }

  public String getStatus() {
    return status;
  }

  public String getTimeCreated() {
    return timeCreated;
  }

  public String getStatusName() {
    return statusName;
  }
}
