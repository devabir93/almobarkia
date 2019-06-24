package com.selsela.example.data.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginData implements Parcelable {

    @SerializedName("user_data")
    @Expose
    private UserData userData;

    /**
     * No args constructor for use in serialization
     */
    public LoginData() {
    }

    /**
     * @param userData
     */
    public LoginData(UserData userData) {
        super();
        this.userData = userData;
    }

    protected LoginData(Parcel in) {
        userData = in.readParcelable(UserData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(userData, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginData> CREATOR = new Creator<LoginData>() {
        @Override
        public LoginData createFromParcel(Parcel in) {
            return new LoginData(in);
        }

        @Override
        public LoginData[] newArray(int size) {
            return new LoginData[size];
        }
    };

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof LoginData)) return false;

        LoginData loginData = (LoginData) o;

        return getUserData() != null ? getUserData().equals(loginData.getUserData()) : loginData.getUserData() == null;
    }

    @Override
    public int hashCode() {
        return getUserData() != null ? getUserData().hashCode() : 0;
    }
}
