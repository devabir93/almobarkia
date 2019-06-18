
package com.selsela.example.data.model.config;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaymentType implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("icon")
    @Expose
    private String icon;
    public final static Creator<PaymentType> CREATOR = new Creator<PaymentType>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PaymentType createFromParcel(Parcel in) {
            return new PaymentType(in);
        }

        public PaymentType[] newArray(int size) {
            return (new PaymentType[size]);
        }

    }
    ;

    protected PaymentType(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.nameEn = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PaymentType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("nameEn", nameEn).append("icon", icon).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(icon).append(nameEn).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentType) == false) {
            return false;
        }
        PaymentType rhs = ((PaymentType) other);
        return new EqualsBuilder().append(id, rhs.id).append(icon, rhs.icon).append(nameEn, rhs.nameEn).append(name, rhs.name).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(nameEn);
        dest.writeValue(icon);
    }

    public int describeContents() {
        return  0;
    }

}
