package com.example.docmanagement.models.responses;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

  @SerializedName("id")
  private final String userId;
  @SerializedName("role")
  private final String userRole;
  @SerializedName("name")
  private final String userName;

  public AuthResponse(String userId, String role, String userName) {
    this.userId = userId;
    this.userRole = role;
    this.userName = userName;
  }

  public String getUserId() {
    return userId;
  }

  public String getUserRole() {
    return userRole;
  }

  public String getUserName() {
    return userName;
  }

  @Override
  public String toString() {
    return "AuthResponse{" +
      "userId='" + userId + '\'' +
      ", role='" + userRole + '\'' +
      '}';
  }
}
