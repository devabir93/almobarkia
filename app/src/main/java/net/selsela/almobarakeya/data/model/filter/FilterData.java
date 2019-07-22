
package net.selsela.almobarakeya.data.model.filter;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.selsela.almobarakeya.data.model.home.Color;
import net.selsela.almobarakeya.data.model.home.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FilterData implements Parcelable
{

    @SerializedName("colors")
    @Expose
    private List<Color> colors = null;
    @SerializedName("sizes")
    @Expose
    private List<Size> sizes = null;
    public final static Creator<FilterData> CREATOR = new Creator<FilterData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public FilterData createFromParcel(Parcel in) {
            return new FilterData(in);
        }

        public FilterData[] newArray(int size) {
            return (new FilterData[size]);
        }

    }
    ;

    protected FilterData(Parcel in) {
        in.readList(this.colors, (Color.class.getClassLoader()));
        in.readList(this.sizes, (Size.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public FilterData() {
    }

    /**
     * 
     * @param sizes
     * @param colors
     */
    public FilterData(List<Color> colors, List<Size> sizes) {
        super();
        this.colors = colors;
        this.sizes = sizes;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("colors", colors).append("sizes", sizes).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sizes).append(colors).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FilterData) == false) {
            return false;
        }
        FilterData rhs = ((FilterData) other);
        return new EqualsBuilder().append(sizes, rhs.sizes).append(colors, rhs.colors).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(colors);
        dest.writeList(sizes);
    }

    public int describeContents() {
        return  0;
    }

}
