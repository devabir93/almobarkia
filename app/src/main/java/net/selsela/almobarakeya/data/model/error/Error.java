
package net.selsela.almobarakeya.data.model.error;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Error implements Parcelable
{

    @SerializedName("field")
    @Expose
    private String field;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<Error> CREATOR = new Creator<Error>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        public Error[] newArray(int size) {
            return (new Error[size]);
        }

    }
    ;

    protected Error(Parcel in) {
        this.field = ((String) in.readValue((String.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Error() {
    }

    /**
     * 
     * @param field
     * @param error
     */
    public Error(String field, String error) {
        super();
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("field", field).append("error", error).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(field).append(error).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Error) == false) {
            return false;
        }
        Error rhs = ((Error) other);
        return new EqualsBuilder().append(field, rhs.field).append(error, rhs.error).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(field);
        dest.writeValue(error);
    }

    public int describeContents() {
        return  0;
    }

}
