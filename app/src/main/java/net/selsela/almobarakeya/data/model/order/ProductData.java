
package net.selsela.almobarakeya.data.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.selsela.almobarakeya.data.model.home.Color;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.home.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    private Double price;
    @SerializedName("product_price")
    @Expose
    private Double productPrice;
    @SerializedName("discount_ratio")
    @Expose
    private Double discountRatio;
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
    public ProductData(Integer id, Integer orderId, Integer productId, Integer quantity, Integer colorId, Integer sizeId, Double price, Double productPrice, Double discountRatio, Integer storeId, String createdAt, String updatedAt, Product product, Color color, Size size) {
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

    protected ProductData(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            orderId = null;
        } else {
            orderId = in.readInt();
        }
        if (in.readByte() == 0) {
            productId = null;
        } else {
            productId = in.readInt();
        }
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        if (in.readByte() == 0) {
            colorId = null;
        } else {
            colorId = in.readInt();
        }
        if (in.readByte() == 0) {
            sizeId = null;
        } else {
            sizeId = in.readInt();
        }
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        if (in.readByte() == 0) {
            productPrice = null;
        } else {
            productPrice = in.readDouble();
        }
        if (in.readByte() == 0) {
            discountRatio = null;
        } else {
            discountRatio = in.readDouble();
        }
        if (in.readByte() == 0) {
            storeId = null;
        } else {
            storeId = in.readInt();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
        product = in.readParcelable(Product.class.getClassLoader());
        color = in.readParcelable(Color.class.getClassLoader());
        size = in.readParcelable(Size.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (orderId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(orderId);
        }
        if (productId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(productId);
        }
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quantity);
        }
        if (colorId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(colorId);
        }
        if (sizeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sizeId);
        }
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
        if (productPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(productPrice);
        }
        if (discountRatio == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(discountRatio);
        }
        if (storeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(storeId);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeParcelable(product, flags);
        dest.writeParcelable(color, flags);
        dest.writeParcelable(size, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(Double discountRatio) {
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

    @Override
    public String


    toString() {
        return "ProductData{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", colorId=" + colorId +
                ", sizeId=" + sizeId +
                ", price=" + price +
                ", productPrice=" + productPrice +
                ", discountRatio=" + discountRatio +
                ", storeId=" + storeId +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", product=" + product +
                ", color=" + color +
                ", size=" + size +
                '}';
    }
}
