package net.selsela.almobarakeya.data.model.user_fav;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.selsela.almobarakeya.data.model.home.Product;

/**
 * Created by Dev_Abir on 19,يونيو,2019
 */
public class favProduct implements Parcelable {

    @SerializedName("product")
    @Expose
    private Product favorites = null;


    protected favProduct(Parcel in) {
        favorites = in.readParcelable(Product.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(favorites, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<favProduct> CREATOR = new Creator<favProduct>() {
        @Override
        public favProduct createFromParcel(Parcel in) {
            return new favProduct(in);
        }

        @Override
        public favProduct[] newArray(int size) {
            return new favProduct[size];
        }
    };

    public Product get_user_favorites() {

        return favorites;
    }

    public void setuser_favorites(Product favorites) {
        this.favorites = favorites;
    }
}
