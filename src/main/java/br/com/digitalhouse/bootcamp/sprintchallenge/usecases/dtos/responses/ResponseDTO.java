package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.responses;

import org.springframework.http.HttpStatus;

public class ResponseDTO<T> {

    private HttpStatus httpStatus;
    private boolean hasError;
    private String errorMessage;
    private T data;

    public ResponseDTO() {
        this.hasError = false;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setError(String errorMessage) {
        this.errorMessage = errorMessage;
        this.hasError = true;
    }

    public boolean isHasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
