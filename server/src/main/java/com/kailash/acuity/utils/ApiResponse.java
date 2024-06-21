package com.kailash.acuity.utils;

public class ApiResponse<T> {

  private int statusCode;
  private String message;
  private Boolean success;
  private T data;

  public ApiResponse() {}

  public ApiResponse(int statusCode, String message, Boolean success, T data) {
    this.statusCode = statusCode;
    this.message = message;
    this.success = success;
    this.data = data;
  }

   public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
