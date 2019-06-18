
package com.selsela.example.data.model.about;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AboutData implements Parcelable
{

    @SerializedName("page")
    @Expose

    private Page page = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AboutData() {
    }

    /**
     * 
     * @param page
     */
    public AboutData(Page page) {
        super();
        this.page = page;
    }

    protected AboutData(Parcel in) {
        page = in.readParcelable(Page.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(page, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AboutData> CREATOR = new Creator<AboutData>() {
        @Override
        public AboutData createFromParcel(Parcel in) {
            return new AboutData(in);
        }

        @Override
        public AboutData[] newArray(int size) {
            return new AboutData[size];
        }
    };

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this).append("page", page).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(page).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AboutData) == false) {
            return false;
        }
        AboutData rhs = ((AboutData) other);
        return new EqualsBuilder().append(page, rhs.page).isEquals();
    }

}
