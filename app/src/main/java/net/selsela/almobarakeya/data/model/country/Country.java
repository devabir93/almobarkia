
package net.selsela.almobarakeya.data.model.country;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Country implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("prefix")
    @Expose
    private Integer prefix;
    @SerializedName("mobile_digits")
    @Expose
    private Integer mobileDigits;
    @SerializedName("tax")
    @Expose
    private Integer tax;
    @SerializedName("conversion_factor")
    @Expose
    private Double conversionFactor;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("curreny")
    @Expose
    private String curreny;

    /**
     * No args constructor for use in serialization
     *
     */
    public Country() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param conversionFactor
     * @param flag
     * @param tax
     * @param createdAt
     * @param name
     * @param prefix
     * @param mobileDigits
     * @param curreny
     */
    public Country(Integer id, String flag, Integer prefix, Integer mobileDigits, Integer tax, Double conversionFactor, String createdAt, String updatedAt, String name, String curreny) {
        super();
        this.id = id;
        this.flag = flag;
        this.prefix = prefix;
        this.mobileDigits = mobileDigits;
        this.tax = tax;
        this.conversionFactor = conversionFactor;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.curreny = curreny;
    }

    protected Country(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        flag = in.readString();
        if (in.readByte() == 0) {
            prefix = null;
        } else {
            prefix = in.readInt();
        }
        if (in.readByte() == 0) {
            mobileDigits = null;
        } else {
            mobileDigits = in.readInt();
        }
        if (in.readByte() == 0) {
            tax = null;
        } else {
            tax = in.readInt();
        }
        if (in.readByte() == 0) {
            conversionFactor = null;
        } else {
            conversionFactor = in.readDouble();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
        name = in.readString();
        curreny = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(flag);
        if (prefix == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(prefix);
        }
        if (mobileDigits == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mobileDigits);
        }
        if (tax == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(tax);
        }
        if (conversionFactor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(conversionFactor);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(name);
        dest.writeString(curreny);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getPrefix() {
        return prefix;
    }

    public void setPrefix(Integer prefix) {
        this.prefix = prefix;
    }

    public Integer getMobileDigits() {
        return mobileDigits;
    }

    public void setMobileDigits(Integer mobileDigits) {
        this.mobileDigits = mobileDigits;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurreny() {
        return curreny;
    }

    public void setCurreny(String curreny) {
        this.curreny = curreny;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("flag", flag).append("prefix", prefix).append("mobileDigits", mobileDigits).append("tax", tax).append("conversionFactor", conversionFactor).append("createdAt", createdAt).append("updatedAt", updatedAt).append("name", name).append("curreny", curreny).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(updatedAt).append(id).append(conversionFactor).append(flag).append(tax).append(createdAt).append(name).append(prefix).append(mobileDigits).append(curreny).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Country) == false) {
            return false;
        }
        Country rhs = ((Country) other);
        return new EqualsBuilder().append(updatedAt, rhs.updatedAt).append(id, rhs.id).append(conversionFactor, rhs.conversionFactor).append(flag, rhs.flag).append(tax, rhs.tax).append(createdAt, rhs.createdAt).append(name, rhs.name).append(prefix, rhs.prefix).append(mobileDigits, rhs.mobileDigits).append(curreny, rhs.curreny).isEquals();
    }

}
