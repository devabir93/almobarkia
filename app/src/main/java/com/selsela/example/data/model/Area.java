
package com.selsela.example.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Area implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("quick_price")
    @Expose
    private double quickPrice;
    @SerializedName("gov_id")
    @Expose
    private Integer govId;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Area() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param price
     * @param nameEn
     * @param createdAt
     * @param govId
     * @param name
     * @param deletedAt
     * @param quickPrice
     */
    public Area(Integer id, String name, String createdAt, String updatedAt, String nameEn, Integer price, Integer quickPrice, Integer govId, Object deletedAt) {
        super();
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nameEn = nameEn;
        this.price = price;
        this.quickPrice = quickPrice;
        this.govId = govId;
        this.deletedAt = deletedAt;
    }

    protected Area(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        nameEn = in.readString();
        price = in.readDouble();
        quickPrice = in.readDouble();
        if (in.readByte() == 0) {
            govId = null;
        } else {
            govId = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(nameEn);
        dest.writeDouble(price);
        dest.writeDouble(quickPrice);
        if (govId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(govId);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Area> CREATOR = new Creator<Area>() {
        @Override
        public Area createFromParcel(Parcel in) {
            return new Area(in);
        }

        @Override
        public Area[] newArray(int size) {
            return new Area[size];
        }
    };

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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuickPrice() {
        return quickPrice;
    }

    public void setQuickPrice(double quickPrice) {
        this.quickPrice = quickPrice;
    }

    public Integer getGovId() {
        return govId;
    }

    public void setGovId(Integer govId) {
        this.govId = govId;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("createdAt", createdAt).append("updatedAt", updatedAt).append("nameEn", nameEn).append("price", price).append("quickPrice", quickPrice).append("govId", govId).append("deletedAt", deletedAt).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(updatedAt).append(id).append(price).append(nameEn).append(createdAt).append(govId).append(name).append(deletedAt).append(quickPrice).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Area) == false) {
            return false;
        }
        Area rhs = ((Area) other);
        return new EqualsBuilder().append(updatedAt, rhs.updatedAt).append(id, rhs.id).append(price, rhs.price).append(nameEn, rhs.nameEn).append(createdAt, rhs.createdAt).append(govId, rhs.govId).append(name, rhs.name).append(deletedAt, rhs.deletedAt).append(quickPrice, rhs.quickPrice).isEquals();
    }

}
