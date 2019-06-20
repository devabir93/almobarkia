
package com.selsela.example.data.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Color extends SugarRecord implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer colorId;
    @SerializedName("color_hexa")
    @Expose
    private String colorHexa;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot;

    /**
     * No args constructor for use in serialization
     */
    public Color() {
    }

    /**
     * @param colorId
     * @param pivot
     * @param colorHexa
     * @param name
     */
    public Color(Integer colorId, String colorHexa, String name, Pivot pivot) {
        super();
        this.colorId = colorId;
        this.colorHexa = colorHexa;
        this.name = name;
        this.pivot = pivot;
    }

    protected Color(Parcel in) {
        if (in.readByte() == 0) {
            colorId = null;
        } else {
            colorId = in.readInt();
        }
        colorHexa = in.readString();
        name = in.readString();
        pivot = in.readParcelable(Pivot.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (colorId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(colorId);
        }
        dest.writeString(colorHexa);
        dest.writeString(name);
        dest.writeParcelable(pivot, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Color> CREATOR = new Creator<Color>() {
        @Override
        public Color createFromParcel(Parcel in) {
            return new Color(in);
        }

        @Override
        public Color[] newArray(int size) {
            return new Color[size];
        }
    };

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColorHexa() {
        return colorHexa;
    }

    public void setColorHexa(String colorHexa) {
        this.colorHexa = colorHexa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("colorId", colorId).append("colorHexa", colorHexa).append("name", name).append("pivot", pivot).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(colorId).append(pivot).append(colorHexa).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Color) == false) {
            return false;
        }
        Color rhs = ((Color) other);
        return new EqualsBuilder().append(colorId, rhs.colorId).append(pivot, rhs.pivot).append(colorHexa, rhs.colorHexa).append(name, rhs.name).isEquals();
    }

}
