
package net.selsela.almobarakeya.data.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Size extends SugarRecord implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer sizeId;
    @SerializedName("parent_category_id")
    @Expose
    private Integer parent_category_id;
    @SerializedName("category_id")
    @Expose
    private Integer category_id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Size() {
    }

    /**
     * 
     * @param sizeId
     * @param pivot
     * @param name
     */
    public Size(Integer sizeId, String name, Pivot pivot) {
        super();
        this.sizeId = sizeId;
        this.name = name;
        this.pivot = pivot;
    }

    protected Size(Parcel in) {
        if (in.readByte() == 0) {
            sizeId = null;
        } else {
            sizeId = in.readInt();
        }
        if (in.readByte() == 0) {
            parent_category_id = null;
        } else {
            parent_category_id = in.readInt();
        }
        if (in.readByte() == 0) {
            category_id = null;
        } else {
            category_id = in.readInt();
        }
        name = in.readString();
        pivot = in.readParcelable(Pivot.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (sizeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sizeId);
        }
        if (parent_category_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(parent_category_id);
        }
        if (category_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(category_id);
        }
        dest.writeString(name);
        dest.writeParcelable(pivot, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Size> CREATOR = new Creator<Size>() {
        @Override
        public Size createFromParcel(Parcel in) {
            return new Size(in);
        }

        @Override
        public Size[] newArray(int size) {
            return new Size[size];
        }
    };

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
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
        return new ToStringBuilder(this).append("sizeId", sizeId).append("name", name).append("pivot", pivot).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sizeId).append(pivot).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Size) == false) {
            return false;
        }
        Size rhs = ((Size) other);
        return new EqualsBuilder().append(sizeId, rhs.sizeId).append(pivot, rhs.pivot).append(name, rhs.name).isEquals();
    }

    public Integer getParent_category_id() {
        return parent_category_id;
    }

    public void setParent_category_id(Integer parent_category_id) {
        this.parent_category_id = parent_category_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
}
