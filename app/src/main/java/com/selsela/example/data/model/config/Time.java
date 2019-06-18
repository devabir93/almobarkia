
package com.selsela.example.data.model.config;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Time implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("time")
    @Expose
    private String time;
    public final static Creator<Time> CREATOR = new Creator<Time>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        public Time[] newArray(int size) {
            return (new Time[size]);
        }

    }
    ;

    protected Time(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Time() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("time", time).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(time).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Time) == false) {
            return false;
        }
        Time rhs = ((Time) other);
        return new EqualsBuilder().append(id, rhs.id).append(time, rhs.time).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(time);
    }

    public int describeContents() {
        return  0;
    }

}
