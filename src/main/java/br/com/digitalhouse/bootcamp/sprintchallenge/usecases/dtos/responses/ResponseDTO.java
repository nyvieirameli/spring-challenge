package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.responses;

import org.springframework.http.HttpStatus;

public class ResponseDTO<T> {

    private HttpStatus httpStatus;
    private boolean hasError;
    private String errorMessage;
    private T data;

    public ResponseDTO(HttpStatus httpStatus, T data) {
        this.httpStatus = httpStatus;
        this.hasError = false;
        this.data = data;
    }

    public ResponseDTO(HttpStatus httpStatus, String errorMessage, T data) {
        this.httpStatus = httpStatus;
        this.hasError = true;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
