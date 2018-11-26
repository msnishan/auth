package com.msnishan.auth.base.rest.message.request;

import java.util.Objects;

public class RequestContext {
    private String requestId;
    private String userId;
    private String clientId;

    public RequestContext() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        RequestContext that = (RequestContext) o;
        return Objects.equals(requestId, that.requestId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(requestId, userId, clientId);
    }

    @Override
    public String toString() {
        return "RequestContext{" +
                "requestId='" + requestId + '\'' +
                ", userId='" + userId + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
