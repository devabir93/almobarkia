package com.selsela.example.data.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserBody implements Parcelable {


    // all update require these two fields
    @SerializedName("user_id")
    @Expose
    private int user_id;
    //////////////reg
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("name")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String emailL;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("confirm_password")
    @Expose
    private String confirm_password;
    @SerializedName("device_key")
    @Expose
    private String deviceKey;

    @SerializedName("country_id")
    @Expose
    private int countryId;

    public UserBody() {
    }


    protected UserBody(Parcel in) {
        user_id = in.readInt();
        token = in.readString();
        username = in.readString();
        password = in.readString();
        emailL = in.readString();
        mobile = in.readString();
        confirm_password = in.readString();
        deviceKey = in.readString();
        countryId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(user_id);
        dest.writeString(token);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(emailL);
        dest.writeString(mobile);
        dest.writeString(confirm_password);
        dest.writeString(deviceKey);
        dest.writeInt(countryId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserBody> CREATOR = new Creator<UserBody>() {
        @Override
        public UserBody createFromParcel(Parcel in) {
            return new UserBody(in);
        }

        @Override
        public UserBody[] newArray(int size) {
            return new UserBody[size];
        }
    };

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailL() {
        return emailL;
    }

    public void setEmailL(String emailL) {
        this.emailL = emailL;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "UserBody{" +
                "user_id=" + user_id +
                ", token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailL='" + emailL + '\'' +
                ", mobile='" + mobile + '\'' +
                ", confirm_password='" + confirm_password + '\'' +
                ", deviceKey='" + deviceKey + '\'' +
                ", countryId=" + countryId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBody userBody = (UserBody) o;

        if (user_id != userBody.user_id) return false;
        if (countryId != userBody.countryId) return false;
        if (token != null ? !token.equals(userBody.token) : userBody.token != null) return false;
        if (username != null ? !username.equals(userBody.username) : userBody.username != null)
            return false;
        if (password != null ? !password.equals(userBody.password) : userBody.password != null)
            return false;
        if (emailL != null ? !emailL.equals(userBody.emailL) : userBody.emailL != null)
            return false;
        if (mobile != null ? !mobile.equals(userBody.mobile) : userBody.mobile != null)
            return false;
        if (confirm_password != null ? !confirm_password.equals(userBody.confirm_password) : userBody.confirm_password != null)
            return false;
        return deviceKey != null ? deviceKey.equals(userBody.deviceKey) : userBody.deviceKey == null;
    }

    @Override
    public int hashCode() {
        int result = user_id;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (emailL != null ? emailL.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (confirm_password != null ? confirm_password.hashCode() : 0);
        result = 31 * result + (deviceKey != null ? deviceKey.hashCode() : 0);
        result = 31 * result + countryId;
        return result;
    }
}
