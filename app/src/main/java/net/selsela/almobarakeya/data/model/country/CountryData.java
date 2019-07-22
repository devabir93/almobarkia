package net.selsela.almobarakeya.data.model.country;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CountryData implements Parcelable
{

    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public CountryData() {
    }

    /**
     *
     * @param countries
     */
    public CountryData(List<Country> countries) {
        super();
        this.countries = countries;
    }

    protected CountryData(Parcel in) {
        countries = in.createTypedArrayList(Country.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(countries);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CountryData> CREATOR = new Creator<CountryData>() {
        @Override
        public CountryData createFromParcel(Parcel in) {
            return new CountryData(in);
        }

        @Override
        public CountryData[] newArray(int size) {
            return new CountryData[size];
        }
    };

    public List<Country> getCountry() {
        return countries;
    }

    public void setCountry(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("countries", countries).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(countries).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CountryData) == false) {
            return false;
        }
        CountryData rhs = ((CountryData) other);
        return new EqualsBuilder().append(countries, rhs.countries).isEquals();
    }

}
