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

  public DealStatusResponse(int id, int dealId, String status, String timeCreated) {
    this.id = id;
    this.dealId = dealId;
    this.status = status;
    this.timeCreated = timeCreated;
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
}
