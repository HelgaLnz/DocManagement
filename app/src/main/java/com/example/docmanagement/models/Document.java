package com.example.docmanagement.models;

public class Document {

  private String title;
  private String number;
  private String type;
  private String state;
  private String date;
  private String status;

  public Document(
    String title,
    String number,
    String type,
    String state,
    String date,
    String status
  ) {
    this.title = title;
    this.number = number;
    this.type = type;
    this.state = state;
    this.date = date;
    this.status = status;
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
}
