package com.example.docmanagement.models.responses;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

  @SerializedName("id")
  private final String userId;
  @SerializedName("role")
  private final String userRole;

  public AuthResponse(String userId, String role) {
    this.userId = userId;
    this.userRole = role;
  }

  public String getUserId() {
    return userId;
  }

  public String getUserRole() {
    return userRole;
  }

  @Override
  public String toString() {
    return "AuthResponse{" +
      "userId='" + userId + '\'' +
      ", role='" + userRole + '\'' +
      '}';
  }
}
