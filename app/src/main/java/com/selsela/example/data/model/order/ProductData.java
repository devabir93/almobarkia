
package com.selsela.example.data.model.order;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selsela.example.data.model.home.Color;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.model.home.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProductData implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("color_id")
    @Expose
    private Integer colorId;
    @SerializedName("size_id")
    @Expose
    private Integer sizeId;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("product_price")
    @Expose
    private Integer productPrice;
    @SerializedName("discount_ratio")
    @Expose
    private Integer discountRatio;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("color")
    @Expose
    private Color color;
    @SerializedName("size")
    @Expose
    private Size size;
    public final static Creator<ProductData> CREATOR = new Creator<ProductData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        public ProductData[] newArray(int size) {
            return (new ProductData[size]);
        }

    }
    ;

    protected ProductData(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.productId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.quantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.colorId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.sizeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.price = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.productPrice = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.discountRatio = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.storeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.product = ((Product) in.readValue((Product.class.getClassLoader())));
        this.color = ((Color) in.readValue((Color.class.getClassLoader())));
        this.size = ((Size) in.readValue((Size.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductData() {
    }

    /**
     * 
     * @param colorId
     * @param size
     * @param productId
     * @param product
     * @param id
     * @param updatedAt
     * @param price
     * @param color
     * @param createdAt
     * @param quantity
     * @param discountRatio
     * @param productPrice
     * @param storeId
     * @param orderId
     * @param sizeId
     */
    public ProductData(Integer id, Integer orderId, Integer productId, Integer quantity, Integer colorId, Integer sizeId, Integer price, Integer productPrice, Integer discountRatio, Integer storeId, String createdAt, String updatedAt, Product product, Color color, Size size) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.colorId = colorId;
        this.sizeId = sizeId;
        this.price = price;
        this.productPrice = productPrice;
        this.discountRatio = discountRatio;
        this.storeId = storeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.product = product;
        this.color = color;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(Integer discountRatio) {
        this.discountRatio = discountRatio;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("orderId", orderId).append("productId", productId).append("quantity", quantity).append("colorId", colorId).append("sizeId", sizeId).append("price", price).append("productPrice", productPrice).append("discountRatio", discountRatio).append("storeId", storeId).append("createdAt", createdAt).append("updatedAt", updatedAt).append("product", product).append("color", color).append("size", size).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(colorId).append(size).append(productId).append(product).append(updatedAt).append(id).append(price).append(color).append(createdAt).append(quantity).append(discountRatio).append(storeId).append(productPrice).append(sizeId).append(orderId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProductData) == false) {
            return false;
        }
        ProductData rhs = ((ProductData) other);
        return new EqualsBuilder().append(colorId, rhs.colorId).append(size, rhs.size).append(productId, rhs.productId).append(product, rhs.product).append(updatedAt, rhs.updatedAt).append(id, rhs.id).append(price, rhs.price).append(color, rhs.color).append(createdAt, rhs.createdAt).append(quantity, rhs.quantity).append(discountRatio, rhs.discountRatio).append(storeId, rhs.storeId).append(productPrice, rhs.productPrice).append(sizeId, rhs.sizeId).append(orderId, rhs.orderId).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(orderId);
        dest.writeValue(productId);
        dest.writeValue(quantity);
        dest.writeValue(colorId);
        dest.writeValue(sizeId);
        dest.writeValue(price);
        dest.writeValue(productPrice);
        dest.writeValue(discountRatio);
        dest.writeValue(storeId);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(product);
        dest.writeValue(color);
        dest.writeValue(size);
    }

    public int describeContents() {
        return  0;
    }

}
