
package com.selsela.example.data.model.home;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class HomeData implements Parcelable
{

    @SerializedName("slider")
    @Expose
    private List<Product> slider = null;
    @SerializedName("main_categories")
    @Expose
    private List<MainCategory> mainCategories = null;
    @SerializedName("last_products")
    @Expose
    private List<Product> lastProducts = null;
    @SerializedName("most_popular")
    @Expose
    private List<Product> mostPopular = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HomeData() {
    }

    /**
     * 
     * @param slider
     * @param lastProducts
     * @param mostPopular
     * @param mainCategories
     */
    public HomeData(List<Product> slider, List<MainCategory> mainCategories, List<Product> lastProducts, List<Product> mostPopular) {
        super();
        this.slider = slider;
        this.mainCategories = mainCategories;
        this.lastProducts = lastProducts;
        this.mostPopular = mostPopular;
    }

    protected HomeData(Parcel in) {
        slider = in.createTypedArrayList(Product.CREATOR);
        mainCategories = in.createTypedArrayList(MainCategory.CREATOR);
        lastProducts = in.createTypedArrayList(Product.CREATOR);
        mostPopular = in.createTypedArrayList(Product.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(slider);
        dest.writeTypedList(mainCategories);
        dest.writeTypedList(lastProducts);
        dest.writeTypedList(mostPopular);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HomeData> CREATOR = new Creator<HomeData>() {
        @Override
        public HomeData createFromParcel(Parcel in) {
            return new HomeData(in);
        }

        @Override
        public HomeData[] newArray(int size) {
            return new HomeData[size];
        }
    };

    public List<Product> getSlider() {
        return slider;
    }

    public void setSlider(List<Product> slider) {
        this.slider = slider;
    }

    public List<MainCategory> getMainCategories() {
        return mainCategories;
    }

    public void setMainCategories(List<MainCategory> mainCategories) {
        this.mainCategories = mainCategories;
    }

    public List<Product> getLastProducts() {
        return lastProducts;
    }

    public void setLastProducts(List<Product> lastProducts) {
        this.lastProducts = lastProducts;
    }

    public List<Product> getMostPopular() {
        return mostPopular;
    }

    public void setMostPopular(List<Product> mostPopular) {
        this.mostPopular = mostPopular;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("slider", slider).append("mainCategories", mainCategories).append("lastProducts", lastProducts).append("mostPopular", mostPopular).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(slider).append(lastProducts).append(mostPopular).append(mainCategories).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HomeData) == false) {
            return false;
        }
        HomeData rhs = ((HomeData) other);
        return new EqualsBuilder().append(slider, rhs.slider).append(lastProducts, rhs.lastProducts).append(mostPopular, rhs.mostPopular).append(mainCategories, rhs.mainCategories).isEquals();
    }

}
