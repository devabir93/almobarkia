
package com.selsela.example.data.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Pivot extends SugarRecord implements Parcelable {

    @SerializedName("size_id")
    @Expose
    private Integer size_id;
    @SerializedName("product_color_id")
    @Expose
    private Integer colorId;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    /**
     * No args constructor for use in serialization
     */
    public Pivot() {
    }


    protected Pivot(Parcel in) {
        if (in.readByte() == 0) {
            size_id = null;
        } else {
            size_id = in.readInt();
        }
        if (in.readByte() == 0) {
            colorId = null;
        } else {
            colorId = in.readInt();
        }
        if (in.readByte() == 0) {
            amount = null;
        } else {
            amount = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (size_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(size_id);
        }
        if (colorId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(colorId);
        }
        if (amount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(amount);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pivot> CREATOR = new Creator<Pivot>() {
        @Override
        public Pivot createFromParcel(Parcel in) {
            return new Pivot(in);
        }

        @Override
        public Pivot[] newArray(int size) {
            return new Pivot[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pivot)) return false;

        Pivot pivot = (Pivot) o;

        if (getSize_id() != null ? !getSize_id().equals(pivot.getSize_id()) : pivot.getSize_id() != null)
            return false;
        if (getColorId() != null ? !getColorId().equals(pivot.getColorId()) : pivot.getColorId() != null)
            return false;
        return getAmount() != null ? getAmount().equals(pivot.getAmount()) : pivot.getAmount() == null;
    }

    @Override
    public int hashCode() {
        int result = getSize_id() != null ? getSize_id().hashCode() : 0;
        result = 31 * result + (getColorId() != null ? getColorId().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        return result;
    }

    public Integer getSize_id() {
        return size_id;
    }

    public void setSize_id(Integer size_id) {
        this.size_id = size_id;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
