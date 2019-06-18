package com.selsela.example.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selsela.example.data.model.config.ConfigData;

public class BaseResponse<T> {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    @SerializedName("response_message_en")
    @Expose
    private String responseMessageEn;
    @SerializedName("data")
    @Expose
    private T data;
    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    public String getResponseMessageEn() {
        return responseMessageEn;
    }

    public void setResponseMessageEn(String responseMessageEn) {
        this.responseMessageEn = responseMessageEn;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

