package com.example.demo.model;

import org.springframework.http.HttpStatus;

public class JSONResponse {
    private String response;
    private String message;
    private HttpStatus status;
    private String error;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
