package com.selsela.example.data.model.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selsela.example.data.model.home.MainCategory;

import java.util.List;

/**
 * Created by Dev_Abir on 19,يونيو,2019
 */
public class CategoriesData implements Parcelable {

    @SerializedName("categories")
    @Expose
    private List<MainCategory> mainCategories = null;


    protected CategoriesData(Parcel in) {
        mainCategories = in.createTypedArrayList(MainCategory.CREATOR);
    }

    public static final Creator<CategoriesData> CREATOR = new Creator<CategoriesData>() {
        @Override
        public CategoriesData createFromParcel(Parcel in) {
            return new CategoriesData(in);
        }

        @Override
        public CategoriesData[] newArray(int size) {
            return new CategoriesData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mainCategories);
    }

    public List<MainCategory> getMainCategories() {
        return mainCategories;
    }

    public void setMainCategories(List<MainCategory> mainCategories) {
        this.mainCategories = mainCategories;
    }
}
