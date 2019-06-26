package com.selsela.example.data.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.selsela.example.data.model.send_order.ProductOrderBody;

import java.util.Arrays;
import java.util.List;

public class OrderBody extends SugarRecord implements Parcelable {


    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("payment_type")
    @Expose
    private int payment_type;

    @SerializedName("transport_cost")
    @Expose
    private double transport_cost;

    @SerializedName("coupon_id")
    @Expose
    private int coupon_id;


    @SerializedName("total_order_price")
    @Expose
    private double price;
    @SerializedName("persons_count")
    @Expose
    private int persons_count;
    @SerializedName("save_address")
    @Expose
    private int save_address;
    @SerializedName("gov_id")
    @Expose
    private int gov_id;
    @SerializedName("area_id")
    @Expose
    private int area_id;
    @SerializedName("block_numer")
    @Expose
    private String block;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("sub_street")
    @Expose
    private String sub_street;
    @SerializedName("building_number")
    @Expose
    private String building_number;

    @SerializedName("floor_number")
    @Expose
    private String floor;
    @SerializedName("flat_number")
    @Expose
    private String flat;
    @SerializedName("full_address")
    @Expose
    private String full_address;
    @SerializedName("old_address")
    @Expose
    private int old_address;
    @SerializedName("building_or_house")
    @Expose
    private int building_or_house;
    @SerializedName("products")
    @Expose
    private String productsStrings;
    @SerializedName("box_id")
    @Expose
    private int box_id;
    private List<ProductOrderBody> productList;
    private Integer[][] productArray;
    private double pricePerPiece;

    public OrderBody() {
    }


    protected OrderBody(Parcel in) {
        userId = in.readString();
        token = in.readString();
        name = in.readString();
        mobile = in.readString();
        payment_type = in.readInt();
        transport_cost = in.readDouble();
        coupon_id = in.readInt();
        price = in.readDouble();
        persons_count = in.readInt();
        save_address = in.readInt();
        gov_id = in.readInt();
        area_id = in.readInt();
        block = in.readString();
        street = in.readString();
        sub_street = in.readString();
        building_number = in.readString();
        floor = in.readString();
        flat = in.readString();
        full_address = in.readString();
        old_address = in.readInt();
        building_or_house = in.readInt();
        productsStrings = in.readString();
        box_id = in.readInt();
        productList = in.createTypedArrayList(ProductOrderBody.CREATOR);
        pricePerPiece = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(token);
        dest.writeString(name);
        dest.writeString(mobile);
        dest.writeInt(payment_type);
        dest.writeDouble(transport_cost);
        dest.writeInt(coupon_id);
        dest.writeDouble(price);
        dest.writeInt(persons_count);
        dest.writeInt(save_address);
        dest.writeInt(gov_id);
        dest.writeInt(area_id);
        dest.writeString(block);
        dest.writeString(street);
        dest.writeString(sub_street);
        dest.writeString(building_number);
        dest.writeString(floor);
        dest.writeString(flat);
        dest.writeString(full_address);
        dest.writeInt(old_address);
        dest.writeInt(building_or_house);
        dest.writeString(productsStrings);
        dest.writeInt(box_id);
        dest.writeTypedList(productList);
        dest.writeDouble(pricePerPiece);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderBody> CREATOR = new Creator<OrderBody>() {
        @Override
        public OrderBody createFromParcel(Parcel in) {
            return new OrderBody(in);
        }

        @Override
        public OrderBody[] newArray(int size) {
            return new OrderBody[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(int payment_type) {
        this.payment_type = payment_type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPersons_count() {
        return persons_count;
    }

    public void setPersons_count(int persons_count) {
        this.persons_count = persons_count;
    }

    public int getSave_address() {
        return save_address;
    }

    public void setSave_address(int save_address) {
        this.save_address = save_address;
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

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
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

    public String getBuilding_number() {
        return building_number;
    }

    public void setBuilding_number(String building_number) {
        this.building_number = building_number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public int getOld_address() {
        return old_address;
    }

    public void setOld_address(int old_address) {
        this.old_address = old_address;
    }

    public int getBuilding_or_house() {
        return building_or_house;
    }

    public void setBuilding_or_house(int building_or_house) {
        this.building_or_house = building_or_house;
    }

    public String getProductsStrings() {
        return productsStrings;
    }

    public void setProductsStrings(String productsStrings) {
        this.productsStrings = productsStrings;
    }

    public List<ProductOrderBody> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductOrderBody> productList) {
        this.productList = productList;
    }

    public Integer[][] getProductArray() {
        return productArray;
    }

    public void setProductArray(Integer[][] productArray) {
        this.productArray = productArray;
    }

    public double getPricePerPiece() {
        return pricePerPiece;
    }

    public void setPricePerPiece(double pricePerPiece) {
        this.pricePerPiece = pricePerPiece;
    }

    @Override
    public String toString() {
        return "OrderBody{" +
                "userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", payment_type=" + payment_type +
                ", transport_cost=" + transport_cost +
                ", coupon_id=" + coupon_id +
                ", price=" + price +
                ", persons_count=" + persons_count +
                ", save_address=" + save_address +
                ", gov_id=" + gov_id +
                ", area_id=" + area_id +
                ", block='" + block + '\'' +
                ", street='" + street + '\'' +
                ", sub_street='" + sub_street + '\'' +
                ", building_number='" + building_number + '\'' +
                ", floor='" + floor + '\'' +
                ", flat='" + flat + '\'' +
                ", full_address='" + full_address + '\'' +
                ", old_address=" + old_address +
                ", building_or_house=" + building_or_house +
                ", productsStrings='" + productsStrings + '\'' +
                ", box_id=" + box_id +
                ", productList=" + productList +
                ", productArray=" + Arrays.toString(productArray) +
                ", pricePerPiece=" + pricePerPiece +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderBody)) return false;

        OrderBody orderBody = (OrderBody) o;

        if (getPayment_type() != orderBody.getPayment_type()) return false;
        if (Double.compare(orderBody.getTransport_cost(), getTransport_cost()) != 0) return false;
        if (getCoupon_id() != orderBody.getCoupon_id()) return false;
        if (Double.compare(orderBody.getPrice(), getPrice()) != 0) return false;
        if (getPersons_count() != orderBody.getPersons_count()) return false;
        if (getSave_address() != orderBody.getSave_address()) return false;
        if (getGov_id() != orderBody.getGov_id()) return false;
        if (getArea_id() != orderBody.getArea_id()) return false;
        if (getOld_address() != orderBody.getOld_address()) return false;
        if (getBuilding_or_house() != orderBody.getBuilding_or_house()) return false;
        if (getBox_id() != orderBody.getBox_id()) return false;
        if (Double.compare(orderBody.getPricePerPiece(), getPricePerPiece()) != 0) return false;
        if (getUserId() != null ? !getUserId().equals(orderBody.getUserId()) : orderBody.getUserId() != null)
            return false;
        if (getToken() != null ? !getToken().equals(orderBody.getToken()) : orderBody.getToken() != null)
            return false;
        if (getName() != null ? !getName().equals(orderBody.getName()) : orderBody.getName() != null)
            return false;
        if (getMobile() != null ? !getMobile().equals(orderBody.getMobile()) : orderBody.getMobile() != null)
            return false;
        if (getBlock() != null ? !getBlock().equals(orderBody.getBlock()) : orderBody.getBlock() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(orderBody.getStreet()) : orderBody.getStreet() != null)
            return false;
        if (getSub_street() != null ? !getSub_street().equals(orderBody.getSub_street()) : orderBody.getSub_street() != null)
            return false;
        if (getBuilding_number() != null ? !getBuilding_number().equals(orderBody.getBuilding_number()) : orderBody.getBuilding_number() != null)
            return false;
        if (getFloor() != null ? !getFloor().equals(orderBody.getFloor()) : orderBody.getFloor() != null)
            return false;
        if (getFlat() != null ? !getFlat().equals(orderBody.getFlat()) : orderBody.getFlat() != null)
            return false;
        if (getFull_address() != null ? !getFull_address().equals(orderBody.getFull_address()) : orderBody.getFull_address() != null)
            return false;
        if (getProductsStrings() != null ? !getProductsStrings().equals(orderBody.getProductsStrings()) : orderBody.getProductsStrings() != null)
            return false;
        if (getProductList() != null ? !getProductList().equals(orderBody.getProductList()) : orderBody.getProductList() != null)
            return false;
        return Arrays.deepEquals(getProductArray(), orderBody.getProductArray());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getToken() != null ? getToken().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
        result = 31 * result + getPayment_type();
        temp = Double.doubleToLongBits(getTransport_cost());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getCoupon_id();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getPersons_count();
        result = 31 * result + getSave_address();
        result = 31 * result + getGov_id();
        result = 31 * result + getArea_id();
        result = 31 * result + (getBlock() != null ? getBlock().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getSub_street() != null ? getSub_street().hashCode() : 0);
        result = 31 * result + (getBuilding_number() != null ? getBuilding_number().hashCode() : 0);
        result = 31 * result + (getFloor() != null ? getFloor().hashCode() : 0);
        result = 31 * result + (getFlat() != null ? getFlat().hashCode() : 0);
        result = 31 * result + (getFull_address() != null ? getFull_address().hashCode() : 0);
        result = 31 * result + getOld_address();
        result = 31 * result + getBuilding_or_house();
        result = 31 * result + (getProductsStrings() != null ? getProductsStrings().hashCode() : 0);
        result = 31 * result + getBox_id();
        result = 31 * result + (getProductList() != null ? getProductList().hashCode() : 0);
        result = 31 * result + Arrays.deepHashCode(getProductArray());
        temp = Double.doubleToLongBits(getPricePerPiece());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double getTransport_cost() {
        return transport_cost;
    }

    public void setTransport_cost(double transport_cost) {
        this.transport_cost = transport_cost;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public int getBox_id() {
        return box_id;
    }

    public void setBox_id(int box_id) {
        this.box_id = box_id;
    }
}
