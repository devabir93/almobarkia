
package com.selsela.example.data.model.coupon;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CheckCoponData implements Parcelable
{

    @SerializedName("copone")
    @Expose
    private Copone copone;
    public final static Creator<CheckCoponData> CREATOR = new Creator<CheckCoponData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CheckCoponData createFromParcel(Parcel in) {
            return new CheckCoponData(in);
        }

        public CheckCoponData[] newArray(int size) {
            return (new CheckCoponData[size]);
        }

    }
    ;

    protected CheckCoponData(Parcel in) {
        this.copone = ((Copone) in.readValue((Copone.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CheckCoponData() {
    }

    /**
     * 
     * @param copone
     */
    public CheckCoponData(Copone copone) {
        super();
        this.copone = copone;
    }

    public Copone getCopone() {
        return copone;
    }

    public void setCopone(Copone copone) {
        this.copone = copone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("copone", copone).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(copone).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CheckCoponData) == false) {
            return false;
        }
        CheckCoponData rhs = ((CheckCoponData) other);
        return new EqualsBuilder().append(copone, rhs.copone).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(copone);
    }

    public int describeContents() {
        return  0;
    }

}
