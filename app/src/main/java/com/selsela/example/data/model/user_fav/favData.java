package com.selsela.example.data.model.user_fav;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selsela.example.data.model.home.MainCategory;
import com.selsela.example.data.model.home.Product;

import java.util.List;

/**
 * Created by Dev_Abir on 19,يونيو,2019
 */
public class favData implements Parcelable {

    @SerializedName("favorites")
    @Expose
    private List<Product> favorites = null;


    protected favData(Parcel in) {
        favorites = in.createTypedArrayList(Product.CREATOR);
    }

    public static final Creator<favData> CREATOR = new Creator<favData>() {
        @Override
        public favData createFromParcel(Parcel in) {
            return new favData(in);
        }

        @Override
        public favData[] newArray(int size) {
            return new favData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(favorites);
    }

    public List<Product> get_user_favorites() {

        return favorites;
    }

    public void setuser_favorites(List<Product> favorites) {
        this.favorites = favorites;
    }
}
