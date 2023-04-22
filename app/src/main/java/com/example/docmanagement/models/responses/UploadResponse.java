package com.example.docmanagement.models.responses;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {

  @SerializedName("result")
  private String result;
  @SerializedName("error")
  private String error;

  public UploadResponse(String result, String error) {
    this.result = result;
    this.error = error;
  }

  public String getResult() {
    return result;
  }

  public String getError() {
    return error;
  }

  @Override
  public String toString() {
    return "UploadResponse{" +
      "result='" + result + '\'' +
      ", error='" + error + '\'' +
      '}';
  }
}
