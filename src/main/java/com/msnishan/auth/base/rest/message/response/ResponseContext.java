package com.msnishan.auth.base.rest.message.response;

import java.util.Objects;

public class ResponseContext {
    private String responseId;
    private String clientId;

    public ResponseContext() {
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseContext that = (ResponseContext) o;
        return Objects.equals(responseId, that.responseId) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(responseId, clientId);
    }

    @Override
    public String toString() {
        return "ResponseContext{" +
                "responseId='" + responseId + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
