
package net.selsela.almobarakeya.data.model.error;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ErrorResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    @SerializedName("errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("data")
    @Expose
    private Object data;
    public final static Parcelable.Creator<ErrorResponse> CREATOR = new Creator<ErrorResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ErrorResponse createFromParcel(Parcel in) {
            return new ErrorResponse(in);
        }

        public ErrorResponse[] newArray(int size) {
            return (new ErrorResponse[size]);
        }

    }
    ;

    protected ErrorResponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.responseMessage = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.errors, (Error.class.getClassLoader()));
        this.data = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ErrorResponse() {
    }

    /**
     * 
     * @param responseMessage
     * @param errors
     * @param status
     * @param data
     */
    public ErrorResponse(Boolean status, String responseMessage, List<Error> errors, Object data) {
        super();
        this.status = status;
        this.responseMessage = responseMessage;
        this.errors = errors;
        this.data = data;
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

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("responseMessage", responseMessage).append("errors", errors).append("data", data).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(responseMessage).append(errors).append(status).append(data).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ErrorResponse) == false) {
            return false;
        }
        ErrorResponse rhs = ((ErrorResponse) other);
        return new EqualsBuilder().append(responseMessage, rhs.responseMessage).append(errors, rhs.errors).append(status, rhs.status).append(data, rhs.data).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(responseMessage);
        dest.writeList(errors);
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }

}
