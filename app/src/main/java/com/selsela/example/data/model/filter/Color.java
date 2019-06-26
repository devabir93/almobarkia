
package com.selsela.example.data.model.filter;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Color implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("color_hexa")
    @Expose
    private String colorHexa;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<Color> CREATOR = new Creator<Color>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Color createFromParcel(Parcel in) {
            return new Color(in);
        }

        public Color[] newArray(int size) {
            return (new Color[size]);
        }

    }
    ;

    protected Color(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.colorHexa = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Color() {
    }

    /**
     * 
     * @param id
     * @param colorHexa
     * @param name
     */
    public Color(Integer id, String colorHexa, String name) {
        super();
        this.id = id;
        this.colorHexa = colorHexa;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("colorHexa", colorHexa).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(colorHexa).append(name).toHashCode();
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
        return new EqualsBuilder().append(id, rhs.id).append(colorHexa, rhs.colorHexa).append(name, rhs.name).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(colorHexa);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
