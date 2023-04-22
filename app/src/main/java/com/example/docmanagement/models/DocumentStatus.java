package com.example.docmanagement.models;

import com.google.gson.annotations.SerializedName;

public class DocumentStatus {
  @SerializedName("id")
  private int id;
  @SerializedName("document_id")
  private int documentId;
  @SerializedName("status")
  private String status;
  @SerializedName("time_created")
  private int timeCreated;
}
