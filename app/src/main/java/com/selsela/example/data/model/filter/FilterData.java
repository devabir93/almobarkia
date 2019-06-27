
package com.selsela.example.data.model.filter;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Filterdata implements Parcelable
{

    @SerializedName("colors")
    @Expose
    private List<Color> colors = null;
    @SerializedName("sizes")
    @Expose
    private List<Size> sizes = null;
    public final static Creator<Filterdata> CREATOR = new Creator<Filterdata>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Filterdata createFromParcel(Parcel in) {
            return new Filterdata(in);
        }

        public Filterdata[] newArray(int size) {
            return (new Filterdata[size]);
        }

    }
    ;

    protected Filterdata(Parcel in) {
        in.readList(this.colors, (com.selsela.example.data.model.filter.Color.class.getClassLoader()));
        in.readList(this.sizes, (com.selsela.example.data.model.filter.Size.class.getClassLoader()));
    }


    public Filterdata() {
    }

    /**
     * 
     * @param sizes
     * @param colors
     */
    public Filterdata(List<Color> colors, List<Size> sizes) {
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
        if ((other instanceof Filterdata) == false) {
            return false;
        }
        Filterdata rhs = ((Filterdata) other);
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
