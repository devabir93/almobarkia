package net.selsela.almobarakeya.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.selsela.almobarakeya.data.model.error.Error;

import java.util.List;

public class BaseResponse<T> {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    @SerializedName("data")
    @Expose
    private T data;

    @SerializedName("errors")
    @Expose
    private List<Error> errors = null;
    private int code;

    @SerializedName("message")
    @Expose
    private String message;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return getStatus();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", responseMessage='" + responseMessage + '\'' +
                ", data=" + data +
                ", errors=" + errors +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}

