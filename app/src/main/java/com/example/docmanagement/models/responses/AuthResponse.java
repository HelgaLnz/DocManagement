package com.example.docmanagement.models.responses;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

  @SerializedName("id")
  private final String userId;
  @SerializedName("role")
  private final String role;

  public AuthResponse(String userId, String role) {
    this.userId = userId;
    this.role = role;
  }

  public String getUserId() {
    return userId;
  }

  public String getRole() {
    return role;
  }

  @Override
  public String toString() {
    return "AuthResponse{" +
      "userId='" + userId + '\'' +
      ", role='" + role + '\'' +
      '}';
  }
}
