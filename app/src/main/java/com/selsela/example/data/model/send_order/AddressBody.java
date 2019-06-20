
package com.selsela.example.data.model.send_order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selsela.example.data.model.Area;
import com.selsela.example.data.model.coupon.Copone;

import java.util.Arrays;

public class AddressBody implements Parcelable {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("gov_id")
    @Expose
    private int gov_id;
    @SerializedName("area_id")
    @Expose
    private int area_id;
    @SerializedName("gov")
    @Expose
    private String gov;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("sub_street")
    @Expose
    private String sub_street;
    @SerializedName("build_house")
    @Expose
    private String build_house;
    @SerializedName("home_number")
    @Expose
    private String home_number;

    @SerializedName("country_id")
    @Expose
    private int country_id;

    @SerializedName("payment_type")
    @Expose
    private int payment_type;

    @SerializedName("transport_cost")
    @Expose
    private double transport_cost;

    @SerializedName("transport_speed")
    @Expose
    private int transport_speed;

    @SerializedName("coupon_id")
    @Expose
    private int coupon_id;

    @SerializedName("old_address")
    @Expose
    private int old_address;

    @SerializedName("save_address")
    @Expose
    private int save_address;

    @SerializedName("products_")
    @Expose
    private String[][] products;
    @SerializedName("full_address")
    @Expose
    String addressText;
    Copone copone;
    Area areaObj;
    @SerializedName("products")
    @Expose
    private String productString;
    @SerializedName("total_order_price")
    @Expose
    private String total_order_price;
    @SerializedName("box_id")
    @Expose
    private int boxId;
    @SerializedName("floor")
    @Expose
    private String floor;
    @SerializedName("flat")
    @Expose
    private String flat;
    @SerializedName("block")
    @Expose
    private String block;

    /**
     * No args constructor for use in serialization
     */
    public AddressBody() {
    }


    protected AddressBody(Parcel in) {
        userId = in.readString();
        token = in.readString();
        lat = in.readString();
        lng = in.readString();
        gov_id = in.readInt();
        area_id = in.readInt();
        gov = in.readString();
        area = in.readString();
        street = in.readString();
        sub_street = in.readString();
        build_house = in.readString();
        home_number = in.readString();
        country_id = in.readInt();
        payment_type = in.readInt();
        transport_cost = in.readDouble();
        transport_speed = in.readInt();
        coupon_id = in.readInt();
        old_address = in.readInt();
        save_address = in.readInt();
        addressText = in.readString();
        copone = in.readParcelable(Copone.class.getClassLoader());
        areaObj = in.readParcelable(Area.class.getClassLoader());
        productString = in.readString();
        total_order_price = in.readString();
        boxId = in.readInt();
        floor = in.readString();
        flat = in.readString();
        block = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(token);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeInt(gov_id);
        dest.writeInt(area_id);
        dest.writeString(gov);
        dest.writeString(area);
        dest.writeString(street);
        dest.writeString(sub_street);
        dest.writeString(build_house);
        dest.writeString(home_number);
        dest.writeInt(country_id);
        dest.writeInt(payment_type);
        dest.writeDouble(transport_cost);
        dest.writeInt(transport_speed);
        dest.writeInt(coupon_id);
        dest.writeInt(old_address);
        dest.writeInt(save_address);
        dest.writeString(addressText);
        dest.writeParcelable(copone, flags);
        dest.writeParcelable(areaObj, flags);
        dest.writeString(productString);
        dest.writeString(total_order_price);
        dest.writeInt(boxId);
        dest.writeString(floor);
        dest.writeString(flat);
        dest.writeString(block);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddressBody> CREATOR = new Creator<AddressBody>() {
        @Override
        public AddressBody createFromParcel(Parcel in) {
            return new AddressBody(in);
        }

        @Override
        public AddressBody[] newArray(int size) {
            return new AddressBody[size];
        }
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getGov_id() {
        return gov_id;
    }

    public void setGov_id(int gov_id) {
        this.gov_id = gov_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getGov() {
        return gov;
    }

    public void setGov(String gov) {
        this.gov = gov;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSub_street() {
        return sub_street;
    }

    public void setSub_street(String sub_street) {
        this.sub_street = sub_street;
    }

    public String getBuild_house() {
        return build_house;
    }

    public void setBuild_house(String build_house) {
        this.build_house = build_house;
    }

    public String getHome_number() {
        return home_number;
    }

    public void setHome_number(String home_number) {
        this.home_number = home_number;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(int payment_type) {
        this.payment_type = payment_type;
    }

    public double getTransport_cost() {
        return transport_cost;
    }

    public void setTransport_cost(double transport_cost) {
        this.transport_cost = transport_cost;
    }

    public int getTransport_speed() {
        return transport_speed;
    }

    public void setTransport_speed(int transport_speed) {
        this.transport_speed = transport_speed;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public int getOld_address() {
        return old_address;
    }

    public void setOld_address(int old_address) {
        this.old_address = old_address;
    }

    public int getSave_address() {
        return save_address;
    }

    public void setSave_address(int save_address) {
        this.save_address = save_address;
    }

    public String[][] getProducts() {
        return products;
    }

    public void setProducts(String[][] products) {
        this.products = products;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return lng;
    }

    public void setLong(String lng) {
        this.lng = lng;
    }


    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    public Copone getCopone() {
        return copone;
    }

    public void setCopone(Copone copone) {
        this.copone = copone;
    }

    public Area getAreaObj() {
        return areaObj;
    }

    public void setAreaObj(Area areaObj) {
        this.areaObj = areaObj;
    }


    @Override
    public String toString() {
        return "AddressBody{" +
                "userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", gov_id=" + gov_id +
                ", area_id=" + area_id +
                ", gov='" + gov + '\'' +
                ", area='" + area + '\'' +
                ", street='" + street + '\'' +
                ", sub_street='" + sub_street + '\'' +
                ", build_house='" + build_house + '\'' +
                ", home_number='" + home_number + '\'' +
                ", country_id=" + country_id +
                ", payment_type=" + payment_type +
                ", transport_cost=" + transport_cost +
                ", transport_speed=" + transport_speed +
                ", coupon_id=" + coupon_id +
                ", old_address=" + old_address +
                ", save_address=" + save_address +
                ", products=" + Arrays.toString(products) +
                ", addressText='" + addressText + '\'' +
                ", copone=" + copone +
                ", areaObj=" + areaObj +
                ", productString='" + productString + '\'' +
                ", total_order_price=" + total_order_price +
                ", boxId=" + boxId +
                '}';
    }

    public String getTotal_order_price() {
        return total_order_price;
    }

    public void setTotal_order_price(String total_order_price) {
        this.total_order_price = total_order_price;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public void setProducts(String s) {
        this.productString = s;
    }

    public String getProductString() {
        return productString;
    }

    public void setProductString(String productString) {
        this.productString = productString;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getFlat() {
        return flat;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getBlock() {
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressBody)) return false;

        AddressBody that = (AddressBody) o;

        if (getGov_id() != that.getGov_id()) return false;
        if (getArea_id() != that.getArea_id()) return false;
        if (getCountry_id() != that.getCountry_id()) return false;
        if (getPayment_type() != that.getPayment_type()) return false;
        if (Double.compare(that.getTransport_cost(), getTransport_cost()) != 0) return false;
        if (getTransport_speed() != that.getTransport_speed()) return false;
        if (getCoupon_id() != that.getCoupon_id()) return false;
        if (getOld_address() != that.getOld_address()) return false;
        if (getSave_address() != that.getSave_address()) return false;
        if (getBoxId() != that.getBoxId()) return false;
        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null)
            return false;
        if (getToken() != null ? !getToken().equals(that.getToken()) : that.getToken() != null)
            return false;
        if (getLat() != null ? !getLat().equals(that.getLat()) : that.getLat() != null)
            return false;
        if (getLng() != null ? !getLng().equals(that.getLng()) : that.getLng() != null)
            return false;
        if (getGov() != null ? !getGov().equals(that.getGov()) : that.getGov() != null)
            return false;
        if (getArea() != null ? !getArea().equals(that.getArea()) : that.getArea() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(that.getStreet()) : that.getStreet() != null)
            return false;
        if (getSub_street() != null ? !getSub_street().equals(that.getSub_street()) : that.getSub_street() != null)
            return false;
        if (getBuild_house() != null ? !getBuild_house().equals(that.getBuild_house()) : that.getBuild_house() != null)
            return false;
        if (getHome_number() != null ? !getHome_number().equals(that.getHome_number()) : that.getHome_number() != null)
            return false;
        if (!Arrays.deepEquals(getProducts(), that.getProducts())) return false;
        if (getAddressText() != null ? !getAddressText().equals(that.getAddressText()) : that.getAddressText() != null)
            return false;
        if (getCopone() != null ? !getCopone().equals(that.getCopone()) : that.getCopone() != null)
            return false;
        if (getAreaObj() != null ? !getAreaObj().equals(that.getAreaObj()) : that.getAreaObj() != null)
            return false;
        if (getProductString() != null ? !getProductString().equals(that.getProductString()) : that.getProductString() != null)
            return false;
        if (getTotal_order_price() != null ? !getTotal_order_price().equals(that.getTotal_order_price()) : that.getTotal_order_price() != null)
            return false;
        if (getFloor() != null ? !getFloor().equals(that.getFloor()) : that.getFloor() != null)
            return false;
        if (getFlat() != null ? !getFlat().equals(that.getFlat()) : that.getFlat() != null)
            return false;
        return getBlock() != null ? getBlock().equals(that.getBlock()) : that.getBlock() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getToken() != null ? getToken().hashCode() : 0);
        result = 31 * result + (getLat() != null ? getLat().hashCode() : 0);
        result = 31 * result + (getLng() != null ? getLng().hashCode() : 0);
        result = 31 * result + getGov_id();
        result = 31 * result + getArea_id();
        result = 31 * result + (getGov() != null ? getGov().hashCode() : 0);
        result = 31 * result + (getArea() != null ? getArea().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getSub_street() != null ? getSub_street().hashCode() : 0);
        result = 31 * result + (getBuild_house() != null ? getBuild_house().hashCode() : 0);
        result = 31 * result + (getHome_number() != null ? getHome_number().hashCode() : 0);
        result = 31 * result + getCountry_id();
        result = 31 * result + getPayment_type();
        temp = Double.doubleToLongBits(getTransport_cost());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getTransport_speed();
        result = 31 * result + getCoupon_id();
        result = 31 * result + getOld_address();
        result = 31 * result + getSave_address();
        result = 31 * result + Arrays.deepHashCode(getProducts());
        result = 31 * result + (getAddressText() != null ? getAddressText().hashCode() : 0);
        result = 31 * result + (getCopone() != null ? getCopone().hashCode() : 0);
        result = 31 * result + (getAreaObj() != null ? getAreaObj().hashCode() : 0);
        result = 31 * result + (getProductString() != null ? getProductString().hashCode() : 0);
        result = 31 * result + (getTotal_order_price() != null ? getTotal_order_price().hashCode() : 0);
        result = 31 * result + getBoxId();
        result = 31 * result + (getFloor() != null ? getFloor().hashCode() : 0);
        result = 31 * result + (getFlat() != null ? getFlat().hashCode() : 0);
        result = 31 * result + (getBlock() != null ? getBlock().hashCode() : 0);
        return result;
    }
}
