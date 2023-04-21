package com.example.docmanagement.models.responses;

import com.google.gson.annotations.SerializedName;

public class Error {
  @SerializedName("message")
  private final String message;
  @SerializedName("code")
  private final String code;

  public Error(String message, String code) {
    this.message = message;
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String toString() {
    return "Error{" +
      "message='" + message + '\'' +
      ", code='" + code + '\'' +
      '}';
  }
}
