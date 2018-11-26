package com.msnishan.auth.base.rest.message.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class BaseResponse {
    private ResponseContext context;
    private LocalDateTime responseDateTime;
    private List<ResponseError> errors;
    private String responseCode;

    public BaseResponse() {
    }

    public ResponseContext getContext() {
        return context;
    }

    public void setContext(ResponseContext context) {
        this.context = context;
    }

    public LocalDateTime getResponseDateTime() {
        return responseDateTime;
    }

    public void setResponseDateTime(LocalDateTime responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseResponse that = (BaseResponse) o;
        return Objects.equals(context, that.context) &&
                Objects.equals(responseDateTime, that.responseDateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(context, responseDateTime);
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "context=" + context +
                ", responseDateTime=" + responseDateTime +
                '}';
    }
}
