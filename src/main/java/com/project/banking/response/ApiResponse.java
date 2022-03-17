package com.project.banking.response;

public class ApiResponse {
    private Integer code;
    private String message;
    private String status;

    public ApiResponse(Integer code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public ApiResponse(){

    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
