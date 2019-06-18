package com.selsela.example.data.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserData implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private long mobile;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("activation_code")
    @Expose
    private Integer activationCode;
    @SerializedName("password_reset_code")
    @Expose
    private Object passwordResetCode;
    @SerializedName("login_times")
    @Expose
    private Integer loginTimes;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("token_expiration")
    @Expose
    private String tokenExpiration;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("remember_token")
    @Expose
    private Object rememberToken;
    @SerializedName("see_notifications")
    @Expose
    private Integer seeNotifications;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     */
    public UserData() {
    }

    public UserData(Integer id, String name, String email, long mobile, Integer status, Object avatar, Integer activationCode, Object passwordResetCode, Integer loginTimes, String token, String tokenExpiration, String lastLogin, Object rememberToken, Integer seeNotifications, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
        this.avatar = avatar;
        this.activationCode = activationCode;
        this.passwordResetCode = passwordResetCode;
        this.loginTimes = loginTimes;
        this.token = token;
        this.tokenExpiration = tokenExpiration;
        this.lastLogin = lastLogin;
        this.rememberToken = rememberToken;
        this.seeNotifications = seeNotifications;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected UserData(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        email = in.readString();
        mobile = in.readLong();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            activationCode = null;
        } else {
            activationCode = in.readInt();
        }
        if (in.readByte() == 0) {
            loginTimes = null;
        } else {
            loginTimes = in.readInt();
        }
        token = in.readString();
        tokenExpiration = in.readString();
        lastLogin = in.readString();
        if (in.readByte() == 0) {
            seeNotifications = null;
        } else {
            seeNotifications = in.readInt();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(email);
        dest.writeLong(mobile);
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        if (activationCode == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(activationCode);
        }
        if (loginTimes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(loginTimes);
        }
        dest.writeString(token);
        dest.writeString(tokenExpiration);
        dest.writeString(lastLogin);
        if (seeNotifications == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(seeNotifications);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Integer getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(Integer activationCode) {
        this.activationCode = activationCode;
    }

    public Object getPasswordResetCode() {
        return passwordResetCode;
    }

    public void setPasswordResetCode(Object passwordResetCode) {
        this.passwordResetCode = passwordResetCode;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(String tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Object getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(Object rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Integer getSeeNotifications() {
        return seeNotifications;
    }

    public void setSeeNotifications(Integer seeNotifications) {
        this.seeNotifications = seeNotifications;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                ", status=" + status +
                ", avatar=" + avatar +
                ", activationCode=" + activationCode +
                ", passwordResetCode=" + passwordResetCode +
                ", loginTimes=" + loginTimes +
                ", token='" + token + '\'' +
                ", tokenExpiration='" + tokenExpiration + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", rememberToken=" + rememberToken +
                ", seeNotifications=" + seeNotifications +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserData)) return false;

        UserData userData = (UserData) o;

        if (getMobile() != userData.getMobile()) return false;
        if (getId() != null ? !getId().equals(userData.getId()) : userData.getId() != null)
            return false;
        if (getName() != null ? !getName().equals(userData.getName()) : userData.getName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(userData.getEmail()) : userData.getEmail() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(userData.getStatus()) : userData.getStatus() != null)
            return false;
        if (getAvatar() != null ? !getAvatar().equals(userData.getAvatar()) : userData.getAvatar() != null)
            return false;
        if (getActivationCode() != null ? !getActivationCode().equals(userData.getActivationCode()) : userData.getActivationCode() != null)
            return false;
        if (getPasswordResetCode() != null ? !getPasswordResetCode().equals(userData.getPasswordResetCode()) : userData.getPasswordResetCode() != null)
            return false;
        if (getLoginTimes() != null ? !getLoginTimes().equals(userData.getLoginTimes()) : userData.getLoginTimes() != null)
            return false;
        if (getToken() != null ? !getToken().equals(userData.getToken()) : userData.getToken() != null)
            return false;
        if (getTokenExpiration() != null ? !getTokenExpiration().equals(userData.getTokenExpiration()) : userData.getTokenExpiration() != null)
            return false;
        if (getLastLogin() != null ? !getLastLogin().equals(userData.getLastLogin()) : userData.getLastLogin() != null)
            return false;
        if (getRememberToken() != null ? !getRememberToken().equals(userData.getRememberToken()) : userData.getRememberToken() != null)
            return false;
        if (getSeeNotifications() != null ? !getSeeNotifications().equals(userData.getSeeNotifications()) : userData.getSeeNotifications() != null)
            return false;
        if (getCreatedAt() != null ? !getCreatedAt().equals(userData.getCreatedAt()) : userData.getCreatedAt() != null)
            return false;
        return getUpdatedAt() != null ? getUpdatedAt().equals(userData.getUpdatedAt()) : userData.getUpdatedAt() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (int) (getMobile() ^ (getMobile() >>> 32));
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getAvatar() != null ? getAvatar().hashCode() : 0);
        result = 31 * result + (getActivationCode() != null ? getActivationCode().hashCode() : 0);
        result = 31 * result + (getPasswordResetCode() != null ? getPasswordResetCode().hashCode() : 0);
        result = 31 * result + (getLoginTimes() != null ? getLoginTimes().hashCode() : 0);
        result = 31 * result + (getToken() != null ? getToken().hashCode() : 0);
        result = 31 * result + (getTokenExpiration() != null ? getTokenExpiration().hashCode() : 0);
        result = 31 * result + (getLastLogin() != null ? getLastLogin().hashCode() : 0);
        result = 31 * result + (getRememberToken() != null ? getRememberToken().hashCode() : 0);
        result = 31 * result + (getSeeNotifications() != null ? getSeeNotifications().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        result = 31 * result + (getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0);
        return result;
    }
}

