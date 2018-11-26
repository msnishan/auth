package com.msnishan.auth.base.rest.message.response;

import java.util.Objects;

public class ResponseError {

    private String errorCode;
    private String errorMessage;

    public ResponseError() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseError that = (ResponseError) o;
        return Objects.equals(errorCode, that.errorCode) &&
                Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(errorCode, errorMessage);
    }

    @Override
    public String toString() {
        return "ResponseError{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
