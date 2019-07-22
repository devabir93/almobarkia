
package net.selsela.almobarakeya.data.model.boxes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class BoxsData implements Parcelable
{

    @SerializedName("boxes")
    @Expose
    private List<Box> boxs = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BoxsData() {
    }

    /**
     * 
     * @param boxs
     */
    public BoxsData(List<Box> boxs) {
        super();
        this.boxs = boxs;
    }

    protected BoxsData(Parcel in) {
        boxs = in.createTypedArrayList(Box.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(boxs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BoxsData> CREATOR = new Creator<BoxsData>() {
        @Override
        public BoxsData createFromParcel(Parcel in) {
            return new BoxsData(in);
        }

        @Override
        public BoxsData[] newArray(int size) {
            return new BoxsData[size];
        }
    };

    public List<Box> getBoxs() {
        return boxs;
    }

    public void setBoxs(List<Box> boxs) {
        this.boxs = boxs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("boxs", boxs).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(boxs).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BoxsData) == false) {
            return false;
        }
        BoxsData rhs = ((BoxsData) other);
        return new EqualsBuilder().append(boxs, rhs.boxs).isEquals();
    }

}
