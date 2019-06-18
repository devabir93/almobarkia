
package com.selsela.example.data.model.home;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Pivot implements Parcelable
{

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("color_id")
    @Expose
    private Integer colorId;
    public final static Creator<Pivot> CREATOR = new Creator<Pivot>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Pivot createFromParcel(Parcel in) {
            return new Pivot(in);
        }

        public Pivot[] newArray(int size) {
            return (new Pivot[size]);
        }

    }
    ;

    protected Pivot(Parcel in) {
        this.productId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.colorId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Pivot() {
    }

    /**
     * 
     * @param colorId
     * @param productId
     */
    public Pivot(Integer productId, Integer colorId) {
        super();
        this.productId = productId;
        this.colorId = colorId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("productId", productId).append("colorId", colorId).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(colorId).append(productId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Pivot) == false) {
            return false;
        }
        Pivot rhs = ((Pivot) other);
        return new EqualsBuilder().append(colorId, rhs.colorId).append(productId, rhs.productId).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productId);
        dest.writeValue(colorId);
    }

    public int describeContents() {
        return  0;
    }

}
