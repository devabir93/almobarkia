
package com.selsela.example.data.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Color extends SugarRecord implements Parcelable {

    @SerializedName("color_id")
    @Expose
    private Integer colorId;
    @SerializedName("color_hexa")
    @Expose
    private String colorHexa;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("product_image_id")
    @Expose
    private int productImageId;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot;
    @SerializedName("sizes")
    @Expose
    private List<Size> sizes = null;

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
        productImageId = in.readInt();
        pivot = in.readParcelable(Pivot.class.getClassLoader());
        sizes = in.createTypedArrayList(Size.CREATOR);
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
        dest.writeInt(productImageId);
        dest.writeParcelable(pivot, flags);
        dest.writeTypedList(sizes);
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

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public int getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(int productImageId) {
        this.productImageId = productImageId;
    }

    @Override
    public String toString() {
        return "Color{" +
                "colorId=" + colorId +
                ", colorHexa='" + colorHexa + '\'' +
                ", name='" + name + '\'' +
                ", productImageId='" + productImageId + '\'' +
                ", pivot=" + pivot +
                ", sizes=" + sizes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Color)) return false;

        Color color = (Color) o;

        if (getProductImageId() != color.getProductImageId()) return false;
        if (getColorId() != null ? !getColorId().equals(color.getColorId()) : color.getColorId() != null)
            return false;
        if (getColorHexa() != null ? !getColorHexa().equals(color.getColorHexa()) : color.getColorHexa() != null)
            return false;
        if (getName() != null ? !getName().equals(color.getName()) : color.getName() != null)
            return false;
        if (getPivot() != null ? !getPivot().equals(color.getPivot()) : color.getPivot() != null)
            return false;
        return getSizes() != null ? getSizes().equals(color.getSizes()) : color.getSizes() == null;
    }

    @Override
    public int hashCode() {
        int result = getColorId() != null ? getColorId().hashCode() : 0;
        result = 31 * result + (getColorHexa() != null ? getColorHexa().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getProductImageId();
        result = 31 * result + (getPivot() != null ? getPivot().hashCode() : 0);
        result = 31 * result + (getSizes() != null ? getSizes().hashCode() : 0);
        return result;
    }


}
