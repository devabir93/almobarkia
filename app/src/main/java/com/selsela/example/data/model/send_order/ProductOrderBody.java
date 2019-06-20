
package com.selsela.example.data.model.send_order;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.selsela.example.data.model.home.Color;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.model.home.Size;

public class ProductOrderBody extends SugarRecord implements Parcelable, Cloneable {

    private Integer orderId;
    private Integer quantity;
    private Product product;
    private String price;
    private Size size;
    private Color color;
    private int isGift;
    private String giftName;
    private String giftMessage;
    int userId;
    int colorId = 0;
    int SizeId = 0;
    private Double weight;
    private Integer amount;

    public ProductOrderBody(Integer orderId, Integer quantity, Product product, String price, Size size, Color color, int isGift, String giftName, String giftMessage, int userId, String priceForSingleItem) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.product = product;
        this.price = price;
        this.size = size;
        this.color = color;
        this.isGift = isGift;
        this.giftName = giftName;
        this.giftMessage = giftMessage;
        this.userId = userId;
        this.priceForSingleItem = priceForSingleItem;
    }

    protected ProductOrderBody(Parcel in) {
        if (in.readByte() == 0) {
            orderId = null;
        } else {
            orderId = in.readInt();
        }
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        product = in.readParcelable(Product.class.getClassLoader());
        price = in.readString();
        size = in.readParcelable(Size.class.getClassLoader());
        color = in.readParcelable(Color.class.getClassLoader());
        isGift = in.readInt();
        giftName = in.readString();
        giftMessage = in.readString();
        userId = in.readInt();
        colorId = in.readInt();
        SizeId = in.readInt();
        if (in.readByte() == 0) {
            weight = null;
        } else {
            weight = in.readDouble();
        }
        if (in.readByte() == 0) {
            amount = null;
        } else {
            amount = in.readInt();
        }
        priceForSingleItem = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (orderId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(orderId);
        }
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quantity);
        }
        dest.writeParcelable(product, flags);
        dest.writeString(price);
        dest.writeParcelable(size, flags);
        dest.writeParcelable(color, flags);
        dest.writeInt(isGift);
        dest.writeString(giftName);
        dest.writeString(giftMessage);
        dest.writeInt(userId);
        dest.writeInt(colorId);
        dest.writeInt(SizeId);
        if (weight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(weight);
        }
        if (amount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(amount);
        }
        dest.writeString(priceForSingleItem);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductOrderBody> CREATOR = new Creator<ProductOrderBody>() {
        @Override
        public ProductOrderBody createFromParcel(Parcel in) {
            return new ProductOrderBody(in);
        }

        @Override
        public ProductOrderBody[] newArray(int size) {
            return new ProductOrderBody[size];
        }
    };

    @Override
    public ProductOrderBody clone() {
        ProductOrderBody clone = null;
        try {
            clone = (ProductOrderBody) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e); // won't happen } return clone; }

        }
        return clone;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getIsGift() {
        return isGift;
    }

    public void setIsGift(int isGift) {
        this.isGift = isGift;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int isGift() {
        return isGift;
    }

    public void setGift(int gift) {
        isGift = gift;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftMessage() {
        return giftMessage;
    }

    public void setGiftMessage(String giftMessage) {
        this.giftMessage = giftMessage;
    }


    public String getPriceForSingleItem() {
        return priceForSingleItem;
    }

    public void setPriceForSingleItem(String priceForSingleItem) {
        this.priceForSingleItem = priceForSingleItem;
    }

    private String priceForSingleItem;

    public ProductOrderBody() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getColorId() {
        return colorId;
    }

    public int getSizeId() {
        return SizeId;
    }

    public void setSizeId(int sizeId) {
        SizeId = sizeId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }


    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "ProductOrderBody{" +
                "orderId=" + orderId +
                ", quantity=" + quantity +
                ", product=" + product +
                ", price='" + price + '\'' +
                ", size=" + size +
                ", color=" + color +
                ", isGift=" + isGift +
                ", giftName='" + giftName + '\'' +
                ", giftMessage='" + giftMessage + '\'' +
                ", userId=" + userId +
                ", colorId=" + colorId +
                ", SizeId=" + SizeId +
                ", weight=" + weight +
                ", amount=" + amount +
                ", priceForSingleItem='" + priceForSingleItem + '\'' +
                '}';
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductOrderBody)) return false;

        ProductOrderBody that = (ProductOrderBody) o;

        if (getIsGift() != that.getIsGift()) return false;
        if (getUserId() != that.getUserId()) return false;
        if (getColorId() != that.getColorId()) return false;
        if (getSizeId() != that.getSizeId()) return false;
        if (getOrderId() != null ? !getOrderId().equals(that.getOrderId()) : that.getOrderId() != null)
            return false;
        if (getQuantity() != null ? !getQuantity().equals(that.getQuantity()) : that.getQuantity() != null)
            return false;
        if (getProduct() != null ? !getProduct().equals(that.getProduct()) : that.getProduct() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(that.getPrice()) : that.getPrice() != null)
            return false;
        if (getSize() != null ? !getSize().equals(that.getSize()) : that.getSize() != null)
            return false;
        if (getColor() != null ? !getColor().equals(that.getColor()) : that.getColor() != null)
            return false;
        if (getGiftName() != null ? !getGiftName().equals(that.getGiftName()) : that.getGiftName() != null)
            return false;
        if (getGiftMessage() != null ? !getGiftMessage().equals(that.getGiftMessage()) : that.getGiftMessage() != null)
            return false;
        if (getWeight() != null ? !getWeight().equals(that.getWeight()) : that.getWeight() != null)
            return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null)
            return false;
        return getPriceForSingleItem() != null ? getPriceForSingleItem().equals(that.getPriceForSingleItem()) : that.getPriceForSingleItem() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getQuantity() != null ? getQuantity().hashCode() : 0);
        result = 31 * result + (getProduct() != null ? getProduct().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + getIsGift();
        result = 31 * result + (getGiftName() != null ? getGiftName().hashCode() : 0);
        result = 31 * result + (getGiftMessage() != null ? getGiftMessage().hashCode() : 0);
        result = 31 * result + getUserId();
        result = 31 * result + getColorId();
        result = 31 * result + getSizeId();
        result = 31 * result + (getWeight() != null ? getWeight().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getPriceForSingleItem() != null ? getPriceForSingleItem().hashCode() : 0);
        return result;
    }
}
