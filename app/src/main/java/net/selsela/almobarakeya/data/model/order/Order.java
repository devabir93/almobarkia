
package net.selsela.almobarakeya.data.model.order;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.selsela.almobarakeya.data.model.address.Address;
import net.selsela.almobarakeya.data.model.country.Country;
import net.selsela.almobarakeya.data.model.user.UserData;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Order implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("order_number")
    @Expose
    private String orderNumber;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("status_id")
    @Expose
    private Integer statusId;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("coupon_ratio")
    @Expose
    private String couponRatio;
    @SerializedName("payment_type")
    @Expose
    private Integer paymentType;
    @SerializedName("is_knet")
    @Expose
    private Integer isKnet;
    @SerializedName("knet_token")
    @Expose
    private String knetToken;
    @SerializedName("knet_response_id")
    @Expose
    private Integer knetResponseId;
    @SerializedName("is_payed")
    @Expose
    private Integer isPayed;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("transport_cost")
    @Expose
    private Double transportCost;
    @SerializedName("service_cost")
    @Expose
    private Double serviceCost;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("conversion_factor")
    @Expose
    private Double conversionFactor;
    @SerializedName("shpping_box_id")
    @Expose
    private Integer shppingBoxId;
    @SerializedName("tracking_id")
    @Expose
    private String trackingId;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("admin_price")
    @Expose
    private Double adminPrice;
    @SerializedName("can_reson")
    @Expose
    private String canReson;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at_text")
    @Expose
    private String createdAtText;
    @SerializedName("knet_url")
    @Expose
    private String knetUrl;
    @SerializedName("tracking_url")
    @Expose
    private String trackingUrl;
    @SerializedName("user")
    @Expose
    private UserData user;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("payment")
    @Expose
    private Payment payment;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("products")
    @Expose
    private List<ProductData> products = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Order() {
    }

    /**
     *
     * @param knetToken
     * @param addressId
     * @param statusId
     * @param id
     * @param adminPrice
     * @param createdAt
     * @param knetUrl
     * @param userId
     * @param isPayed
     * @param products
     * @param knetResponseId
     * @param countryId
     * @param isKnet
     * @param conversionFactor
     * @param serviceCost
     * @param payment
     * @param status
     * @param paymentType
     * @param trackingId
     * @param couponCode
     * @param canReson
     * @param transportCost
     * @param country
     * @param updatedAt
     * @param createdAtText
     * @param couponRatio
     * @param trackingUrl
     * @param price
     * @param address
     * @param orderNumber
     * @param shppingBoxId
     * @param user
     * @param storeId
     */
    public Order(Integer id, Integer userId, String orderNumber, Double price, Integer statusId, String couponCode, String couponRatio, Integer paymentType, Integer isKnet, String knetToken, Integer knetResponseId, Integer isPayed, Integer addressId, Double transportCost, Double serviceCost, Integer countryId, Double conversionFactor, Integer shppingBoxId, String trackingId, Integer storeId, Double adminPrice, String canReson, String createdAt, String updatedAt, String createdAtText, String knetUrl, String trackingUrl, UserData user, Status status, Address address, Payment payment, Country country, List<ProductData> products) {
        super();
        this.id = id;
        this.userId = userId;
        this.orderNumber = orderNumber;
        this.price = price;
        this.statusId = statusId;
        this.couponCode = couponCode;
        this.couponRatio = couponRatio;
        this.paymentType = paymentType;
        this.isKnet = isKnet;
        this.knetToken = knetToken;
        this.knetResponseId = knetResponseId;
        this.isPayed = isPayed;
        this.addressId = addressId;
        this.transportCost = transportCost;
        this.serviceCost = serviceCost;
        this.countryId = countryId;
        this.conversionFactor = conversionFactor;
        this.shppingBoxId = shppingBoxId;
        this.trackingId = trackingId;
        this.storeId = storeId;
        this.adminPrice = adminPrice;
        this.canReson = canReson;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdAtText = createdAtText;
        this.knetUrl = knetUrl;
        this.trackingUrl = trackingUrl;
        this.user = user;
        this.status = status;
        this.address = address;
        this.payment = payment;
        this.country = country;
        this.products = products;
    }

    protected Order(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        orderNumber = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        if (in.readByte() == 0) {
            statusId = null;
        } else {
            statusId = in.readInt();
        }
        couponCode = in.readString();
        couponRatio = in.readString();
        if (in.readByte() == 0) {
            paymentType = null;
        } else {
            paymentType = in.readInt();
        }
        if (in.readByte() == 0) {
            isKnet = null;
        } else {
            isKnet = in.readInt();
        }
        knetToken = in.readString();
        if (in.readByte() == 0) {
            knetResponseId = null;
        } else {
            knetResponseId = in.readInt();
        }
        if (in.readByte() == 0) {
            isPayed = null;
        } else {
            isPayed = in.readInt();
        }
        if (in.readByte() == 0) {
            addressId = null;
        } else {
            addressId = in.readInt();
        }
        if (in.readByte() == 0) {
            transportCost = null;
        } else {
            transportCost = in.readDouble();
        }
        if (in.readByte() == 0) {
            serviceCost = null;
        } else {
            serviceCost = in.readDouble();
        }
        if (in.readByte() == 0) {
            countryId = null;
        } else {
            countryId = in.readInt();
        }
        if (in.readByte() == 0) {
            conversionFactor = null;
        } else {
            conversionFactor = in.readDouble();
        }
        if (in.readByte() == 0) {
            shppingBoxId = null;
        } else {
            shppingBoxId = in.readInt();
        }
        trackingId = in.readString();
        if (in.readByte() == 0) {
            storeId = null;
        } else {
            storeId = in.readInt();
        }
        if (in.readByte() == 0) {
            adminPrice = null;
        } else {
            adminPrice = in.readDouble();
        }
        canReson = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        createdAtText = in.readString();
        knetUrl = in.readString();
        trackingUrl = in.readString();
        user = in.readParcelable(UserData.class.getClassLoader());
        status = in.readParcelable(Status.class.getClassLoader());
        address = in.readParcelable(Address.class.getClassLoader());
        payment = in.readParcelable(Payment.class.getClassLoader());
        country = in.readParcelable(Country.class.getClassLoader());
        products = in.createTypedArrayList(ProductData.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeString(orderNumber);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
        if (statusId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(statusId);
        }
        dest.writeString(couponCode);
        dest.writeString(couponRatio);
        if (paymentType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(paymentType);
        }
        if (isKnet == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isKnet);
        }
        dest.writeString(knetToken);
        if (knetResponseId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(knetResponseId);
        }
        if (isPayed == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isPayed);
        }
        if (addressId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(addressId);
        }
        if (transportCost == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(transportCost);
        }
        if (serviceCost == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(serviceCost);
        }
        if (countryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(countryId);
        }
        if (conversionFactor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(conversionFactor);
        }
        if (shppingBoxId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(shppingBoxId);
        }
        dest.writeString(trackingId);
        if (storeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(storeId);
        }
        if (adminPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(adminPrice);
        }
        dest.writeString(canReson);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(createdAtText);
        dest.writeString(knetUrl);
        dest.writeString(trackingUrl);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(status, flags);
        dest.writeParcelable(address, flags);
        dest.writeParcelable(payment, flags);
        dest.writeParcelable(country, flags);
        dest.writeTypedList(products);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponRatio() {
        return couponRatio;
    }

    public void setCouponRatio(String couponRatio) {
        this.couponRatio = couponRatio;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getIsKnet() {
        return isKnet;
    }

    public void setIsKnet(Integer isKnet) {
        this.isKnet = isKnet;
    }

    public String getKnetToken() {
        return knetToken;
    }

    public void setKnetToken(String knetToken) {
        this.knetToken = knetToken;
    }

    public Integer getKnetResponseId() {
        return knetResponseId;
    }

    public void setKnetResponseId(Integer knetResponseId) {
        this.knetResponseId = knetResponseId;
    }

    public Integer getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Integer isPayed) {
        this.isPayed = isPayed;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(Double transportCost) {
        this.transportCost = transportCost;
    }

    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public Integer getShppingBoxId() {
        return shppingBoxId;
    }

    public void setShppingBoxId(Integer shppingBoxId) {
        this.shppingBoxId = shppingBoxId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Double getAdminPrice() {
        return adminPrice;
    }

    public void setAdminPrice(Double adminPrice) {
        this.adminPrice = adminPrice;
    }

    public String getCanReson() {
        return canReson;
    }

    public void setCanReson(String canReson) {
        this.canReson = canReson;
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

    public String getCreatedAtText() {
        return createdAtText;
    }

    public void setCreatedAtText(String createdAtText) {
        this.createdAtText = createdAtText;
    }

    public String getKnetUrl() {
        return knetUrl;
    }

    public void setKnetUrl(String knetUrl) {
        this.knetUrl = knetUrl;
    }

    public String getTrackingUrl() {
        return trackingUrl;
    }

    public void setTrackingUrl(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<ProductData> getProducts() {
        return products;
    }

    public void setProducts(List<ProductData> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("userId", userId).append("orderNumber", orderNumber).append("price", price).append("statusId", statusId).append("couponCode", couponCode).append("couponRatio", couponRatio).append("paymentType", paymentType).append("isKnet", isKnet).append("knetToken", knetToken).append("knetResponseId", knetResponseId).append("isPayed", isPayed).append("addressId", addressId).append("transportCost", transportCost).append("serviceCost", serviceCost).append("countryId", countryId).append("conversionFactor", conversionFactor).append("shppingBoxId", shppingBoxId).append("trackingId", trackingId).append("storeId", storeId).append("adminPrice", adminPrice).append("canReson", canReson).append("createdAt", createdAt).append("updatedAt", updatedAt).append("createdAtText", createdAtText).append("knetUrl", knetUrl).append("trackingUrl", trackingUrl).append("user", user).append("status", status).append("address", address).append("payment", payment).append("country", country).append("products", products).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(knetToken).append(addressId).append(statusId).append(id).append(adminPrice).append(createdAt).append(knetUrl).append(userId).append(isPayed).append(products).append(knetResponseId).append(countryId).append(conversionFactor).append(isKnet).append(serviceCost).append(payment).append(status).append(paymentType).append(trackingId).append(couponCode).append(canReson).append(transportCost).append(country).append(updatedAt).append(createdAtText).append(couponRatio).append(trackingUrl).append(price).append(address).append(orderNumber).append(shppingBoxId).append(user).append(storeId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Order) == false) {
            return false;
        }
        Order rhs = ((Order) other);
        return new EqualsBuilder().append(knetToken, rhs.knetToken).append(addressId, rhs.addressId).append(statusId, rhs.statusId).append(id, rhs.id).append(adminPrice, rhs.adminPrice).append(createdAt, rhs.createdAt).append(knetUrl, rhs.knetUrl).append(userId, rhs.userId).append(isPayed, rhs.isPayed).append(products, rhs.products).append(knetResponseId, rhs.knetResponseId).append(countryId, rhs.countryId).append(conversionFactor, rhs.conversionFactor).append(isKnet, rhs.isKnet).append(serviceCost, rhs.serviceCost).append(payment, rhs.payment).append(status, rhs.status).append(paymentType, rhs.paymentType).append(trackingId, rhs.trackingId).append(couponCode, rhs.couponCode).append(canReson, rhs.canReson).append(transportCost, rhs.transportCost).append(country, rhs.country).append(updatedAt, rhs.updatedAt).append(createdAtText, rhs.createdAtText).append(couponRatio, rhs.couponRatio).append(trackingUrl, rhs.trackingUrl).append(price, rhs.price).append(address, rhs.address).append(orderNumber, rhs.orderNumber).append(shppingBoxId, rhs.shppingBoxId).append(user, rhs.user).append(storeId, rhs.storeId).isEquals();
    }

}
