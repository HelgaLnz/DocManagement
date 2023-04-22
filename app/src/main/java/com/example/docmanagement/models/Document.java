package com.example.docmanagement.models;

import com.example.docmanagement.models.responses.DocumentResponse;

import java.text.DateFormat;
import java.util.Date;

public class Document {

  private String title;
  private String number;
  private int dealId;
  private String type;
  private String state;
  private String date;
  private String status;

  public Document(
    String title,
    String number,
    int dealId,
    String type,
    String state,
    String date,
    String status
  ) {
    this.title = title;
    this.number = number;
    this.dealId = dealId;
    this.type = type;
    this.state = state;
    this.date = date;
    this.status = status;
  }

  public Document(DocumentResponse documentResponse, String userId) {
    this.title = documentResponse.getName() != null ? documentResponse.getName() : "Мой документ";
    this.number = documentResponse.getId() + "";
    this.type = documentResponse.getUserId() == Integer.parseInt(userId) ? "Исходящий" : "Входящий";
    this.state = "В архиве";
    if (documentResponse.getDocumentStatuses() != null) {
      this.date = documentResponse.getDocumentStatuses().get(0).getTimeCreated();
    }
    if (documentResponse.getDocumentStatuses() != null) {
      this.status = documentResponse.getDocumentStatuses().get(0).getStatus();
    }
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public int getDealId() {
    return dealId;
  }

  public void setDealId(int dealId) {
    this.dealId = dealId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Document{" +
      "title='" + title + '\'' +
      ", number='" + number + '\'' +
      ", dealId=" + dealId +
      ", type='" + type + '\'' +
      ", state='" + state + '\'' +
      ", date='" + date + '\'' +
      ", status='" + status + '\'' +
      '}';
  }
}
