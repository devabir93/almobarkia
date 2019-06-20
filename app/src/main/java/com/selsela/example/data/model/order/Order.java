
package com.selsela.example.data.model.order;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selsela.example.data.model.address.Address;
import com.selsela.example.data.model.country.Country;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.model.user.UserData;

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
    private Integer price;
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
    private Integer transportCost;
    @SerializedName("service_cost")
    @Expose
    private Integer serviceCost;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("conversion_factor")
    @Expose
    private Integer conversionFactor;
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
    private Integer adminPrice;
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
    public final static Parcelable.Creator<Order> CREATOR = new Creator<Order>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return (new Order[size]);
        }

    }
            ;

    protected Order(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.couponCode = ((String) in.readValue((String.class.getClassLoader())));
        this.couponRatio = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentType = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.isKnet = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.knetToken = ((String) in.readValue((String.class.getClassLoader())));
        this.knetResponseId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.isPayed = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.addressId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.transportCost = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.serviceCost = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.countryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.conversionFactor = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.shppingBoxId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.trackingId = ((String) in.readValue((String.class.getClassLoader())));
        this.storeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.adminPrice = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.canReson = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAtText = ((String) in.readValue((String.class.getClassLoader())));
        this.knetUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.trackingUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.user = ((UserData) in.readValue((UserData.class.getClassLoader())));
        this.status = ((Status) in.readValue((Status.class.getClassLoader())));
        this.address = ((Address) in.readValue((Address.class.getClassLoader())));
        this.payment = ((Payment) in.readValue((Payment.class.getClassLoader())));
        this.country = ((Country) in.readValue((Country.class.getClassLoader())));
        in.readList(this.products, (ProductData.class.getClassLoader()));
    }

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
    public Order(Integer id, Integer userId, String orderNumber, Integer price, Integer statusId, String couponCode, String couponRatio, Integer paymentType, Integer isKnet, String knetToken, Integer knetResponseId, Integer isPayed, Integer addressId, Integer transportCost, Integer serviceCost, Integer countryId, Integer conversionFactor, Integer shppingBoxId, String trackingId, Integer storeId, Integer adminPrice, String canReson, String createdAt, String updatedAt, String createdAtText, String knetUrl, String trackingUrl, UserData user, Status status, Address address, Payment payment, Country country, List<ProductData> products) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    public Integer getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(Integer transportCost) {
        this.transportCost = transportCost;
    }

    public Integer getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Integer serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Integer conversionFactor) {
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

    public Integer getAdminPrice() {
        return adminPrice;
    }

    public void setAdminPrice(Integer adminPrice) {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(userId);
        dest.writeValue(orderNumber);
        dest.writeValue(price);
        dest.writeValue(statusId);
        dest.writeValue(couponCode);
        dest.writeValue(couponRatio);
        dest.writeValue(paymentType);
        dest.writeValue(isKnet);
        dest.writeValue(knetToken);
        dest.writeValue(knetResponseId);
        dest.writeValue(isPayed);
        dest.writeValue(addressId);
        dest.writeValue(transportCost);
        dest.writeValue(serviceCost);
        dest.writeValue(countryId);
        dest.writeValue(conversionFactor);
        dest.writeValue(shppingBoxId);
        dest.writeValue(trackingId);
        dest.writeValue(storeId);
        dest.writeValue(adminPrice);
        dest.writeValue(canReson);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(createdAtText);
        dest.writeValue(knetUrl);
        dest.writeValue(trackingUrl);
        dest.writeValue(user);
        dest.writeValue(status);
        dest.writeValue(address);
        dest.writeValue(payment);
        dest.writeValue(country);
        dest.writeList(products);
    }

    public int describeContents() {
        return  0;
    }

}
