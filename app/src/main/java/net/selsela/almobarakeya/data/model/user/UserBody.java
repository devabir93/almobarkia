package net.selsela.almobarakeya.data.model.user;

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
    @SerializedName("old_password")
    @Expose
    private String old_password;
    @SerializedName("new_password")
    @Expose
    private String new_password;
    @SerializedName("key")
    @Expose
    private String deviceKey;

    @SerializedName("country_id")
    @Expose
    private int countryId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("products")
    @Expose
    private String products;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_details")
    @Expose
    private String productDetails;

    @SerializedName("uploaded_file")
    @Expose
    private String uploaded_file;

    public UserBody() {
    }


    protected UserBody(Parcel in) {
        user_id = in.readInt();
        token = in.readString();
        password = in.readString();
        emailL = in.readString();
        mobile = in.readString();
        confirm_password = in.readString();
        old_password = in.readString();
        new_password = in.readString();
        deviceKey = in.readString();
        countryId = in.readInt();
        message = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            orderId = null;
        } else {
            orderId = in.readInt();
        }
        products = in.readString();
        productName = in.readString();
        productDetails = in.readString();
        uploaded_file = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(user_id);
        dest.writeString(token);
        dest.writeString(password);
        dest.writeString(emailL);
        dest.writeString(mobile);
        dest.writeString(confirm_password);
        dest.writeString(old_password);
        dest.writeString(new_password);
        dest.writeString(deviceKey);
        dest.writeInt(countryId);
        dest.writeString(message);
        dest.writeString(name);
        if (orderId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(orderId);
        }
        dest.writeString(products);
        dest.writeString(productName);
        dest.writeString(productDetails);
        dest.writeString(uploaded_file);
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

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "UserBody{" +
                "user_id=" + user_id +
                ", token='" + token + '\'' +
                ", password='" + password + '\'' +
                ", emailL='" + emailL + '\'' +
                ", mobile='" + mobile + '\'' +
                ", confirm_password='" + confirm_password + '\'' +
                ", old_password='" + old_password + '\'' +
                ", new_password='" + new_password + '\'' +
                ", deviceKey='" + deviceKey + '\'' +
                ", countryId=" + countryId +
                ", message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", products='" + products + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBody)) return false;

        UserBody userBody = (UserBody) o;

        if (getUser_id() != userBody.getUser_id()) return false;
        if (getCountryId() != userBody.getCountryId()) return false;
        if (getToken() != null ? !getToken().equals(userBody.getToken()) : userBody.getToken() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(userBody.getPassword()) : userBody.getPassword() != null)
            return false;
        if (getEmailL() != null ? !getEmailL().equals(userBody.getEmailL()) : userBody.getEmailL() != null)
            return false;
        if (getMobile() != null ? !getMobile().equals(userBody.getMobile()) : userBody.getMobile() != null)
            return false;
        if (getConfirm_password() != null ? !getConfirm_password().equals(userBody.getConfirm_password()) : userBody.getConfirm_password() != null)
            return false;
        if (getOld_password() != null ? !getOld_password().equals(userBody.getOld_password()) : userBody.getOld_password() != null)
            return false;
        if (getNew_password() != null ? !getNew_password().equals(userBody.getNew_password()) : userBody.getNew_password() != null)
            return false;
        if (getDeviceKey() != null ? !getDeviceKey().equals(userBody.getDeviceKey()) : userBody.getDeviceKey() != null)
            return false;
        if (getMessage() != null ? !getMessage().equals(userBody.getMessage()) : userBody.getMessage() != null)
            return false;
        if (getName() != null ? !getName().equals(userBody.getName()) : userBody.getName() != null)
            return false;
        if (getOrderId() != null ? !getOrderId().equals(userBody.getOrderId()) : userBody.getOrderId() != null)
            return false;
        if (getProducts() != null ? !getProducts().equals(userBody.getProducts()) : userBody.getProducts() != null)
            return false;
        if (getProductName() != null ? !getProductName().equals(userBody.getProductName()) : userBody.getProductName() != null)
            return false;
        if (getProductDetails() != null ? !getProductDetails().equals(userBody.getProductDetails()) : userBody.getProductDetails() != null)
            return false;
        return getUploaded_file() != null ? getUploaded_file().equals(userBody.getUploaded_file()) : userBody.getUploaded_file() == null;
    }

    @Override
    public int hashCode() {
        int result = getUser_id();
        result = 31 * result + (getToken() != null ? getToken().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getEmailL() != null ? getEmailL().hashCode() : 0);
        result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
        result = 31 * result + (getConfirm_password() != null ? getConfirm_password().hashCode() : 0);
        result = 31 * result + (getOld_password() != null ? getOld_password().hashCode() : 0);
        result = 31 * result + (getNew_password() != null ? getNew_password().hashCode() : 0);
        result = 31 * result + (getDeviceKey() != null ? getDeviceKey().hashCode() : 0);
        result = 31 * result + getCountryId();
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        result = 31 * result + (getProductName() != null ? getProductName().hashCode() : 0);
        result = 31 * result + (getProductDetails() != null ? getProductDetails().hashCode() : 0);
        result = 31 * result + (getUploaded_file() != null ? getUploaded_file().hashCode() : 0);
        return result;
    }

    public String getUploaded_file() {
        return uploaded_file;
    }

    public void setUploaded_file(String uploaded_file) {
        this.uploaded_file = uploaded_file;
    }
}
