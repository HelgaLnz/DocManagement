package com.example.docmanagement.models.responses;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
  @SerializedName("error")
  private final Error error;

  public ErrorResponse(Error error) {
    this.error = error;
  }

  public Error getError() {
    return error;
  }

  @Override
  public String toString() {
    return "ErrorResponse{" +
      "error=" + error +
      '}';
  }
}
