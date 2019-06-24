
package com.selsela.example.data.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Product extends SugarRecord implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer productId;
    @SerializedName("real_price")
    @Expose
    private double realPrice;
    @SerializedName("discount_ratio")
    @Expose
    private double discountRatio;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("weight")
    @Expose
    private double weight;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("in_slider")
    @Expose
    private Integer inSlider;
    @SerializedName("most_popular")
    @Expose
    private Integer mostPopular;
    @SerializedName("recently_come")
    @Expose
    private Integer recentlyCome;
    @SerializedName("number_of_sales")
    @Expose
    private Integer numberOfSales;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("max_order")
    @Expose
    private Integer maxOrder;
    @SerializedName("amount_left")
    @Expose
    private Integer amountLeft;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("has_discount")
    @Expose
    private Integer hasDiscount;
    @SerializedName("in_favorite")
    @Expose
    private Integer inFavorite;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("has_colors")
    @Expose
    private Integer hasColors;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("store")
    @Expose
    private Store store;
    @SerializedName("colors")
    @Expose
    private List<Color> colors = null;
    @SerializedName("sizes")
    @Expose
    private List<Size> sizes = null;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    /**
     * No args constructor for use in serialization
     */
    public Product() {
    }

    /**
     * @param sizes
     * @param weight
     * @param store
     * @param imageUrl
     * @param inFavorite
     * @param amountLeft
     * @param currency
     * @param productId
     * @param amount
     * @param rate
     * @param details
     * @param numberOfSales
     * @param createdAt
     * @param name
     * @param maxOrder
     * @param discountRatio
     * @param realPrice
     * @param status
     * @param colors
     * @param categoryId
     * @param mostPopular
     * @param image
     * @param recentlyCome
     * @param code
     * @param hasDiscount
     * @param updatedAt
     * @param hasColors
     * @param price
     * @param inSlider
     * @param images
     * @param storeId
     */
    public Product(Integer productId, Integer realPrice, Integer discountRatio, Integer price, String code, Integer weight, Integer status, Integer categoryId, Integer storeId, Integer inSlider, Integer mostPopular, Integer recentlyCome, Integer numberOfSales, Integer amount, Integer maxOrder, Integer amountLeft, String createdAt, String updatedAt, String image, Integer hasDiscount, Integer inFavorite, String rate, String imageUrl, Integer hasColors, String name, String details, String currency, Store store, List<Color> colors, List<Size> sizes, List<Image> images) {
        super();
        this.productId = productId;
        this.realPrice = realPrice;
        this.discountRatio = discountRatio;
        this.price = price;
        this.code = code;
        this.weight = weight;
        this.status = status;
        this.categoryId = categoryId;
        this.storeId = storeId;
        this.inSlider = inSlider;
        this.mostPopular = mostPopular;
        this.recentlyCome = recentlyCome;
        this.numberOfSales = numberOfSales;
        this.amount = amount;
        this.maxOrder = maxOrder;
        this.amountLeft = amountLeft;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.image = image;
        this.hasDiscount = hasDiscount;
        this.inFavorite = inFavorite;
        this.rate = rate;
        this.imageUrl = imageUrl;
        this.hasColors = hasColors;
        this.name = name;
        this.details = details;
        this.currency = currency;
        this.store = store;
        this.colors = colors;
        this.sizes = sizes;
        this.images = images;
    }

    protected Product(Parcel in) {
        if (in.readByte() == 0) {
            productId = null;
        } else {
            productId = in.readInt();
        }
        realPrice = in.readDouble();
        discountRatio = in.readDouble();
        price = in.readDouble();
        code = in.readString();
        weight = in.readDouble();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            categoryId = null;
        } else {
            categoryId = in.readInt();
        }
        if (in.readByte() == 0) {
            storeId = null;
        } else {
            storeId = in.readInt();
        }
        if (in.readByte() == 0) {
            inSlider = null;
        } else {
            inSlider = in.readInt();
        }
        if (in.readByte() == 0) {
            mostPopular = null;
        } else {
            mostPopular = in.readInt();
        }
        if (in.readByte() == 0) {
            recentlyCome = null;
        } else {
            recentlyCome = in.readInt();
        }
        if (in.readByte() == 0) {
            numberOfSales = null;
        } else {
            numberOfSales = in.readInt();
        }
        if (in.readByte() == 0) {
            amount = null;
        } else {
            amount = in.readInt();
        }
        if (in.readByte() == 0) {
            maxOrder = null;
        } else {
            maxOrder = in.readInt();
        }
        if (in.readByte() == 0) {
            amountLeft = null;
        } else {
            amountLeft = in.readInt();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
        image = in.readString();
        if (in.readByte() == 0) {
            hasDiscount = null;
        } else {
            hasDiscount = in.readInt();
        }
        if (in.readByte() == 0) {
            inFavorite = null;
        } else {
            inFavorite = in.readInt();
        }
        rate = in.readString();
        imageUrl = in.readString();
        if (in.readByte() == 0) {
            hasColors = null;
        } else {
            hasColors = in.readInt();
        }
        name = in.readString();
        details = in.readString();
        currency = in.readString();
        store = in.readParcelable(Store.class.getClassLoader());
        colors = in.createTypedArrayList(Color.CREATOR);
        sizes = in.createTypedArrayList(Size.CREATOR);
        images = in.createTypedArrayList(Image.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (productId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(productId);
        }
        dest.writeDouble(realPrice);
        dest.writeDouble(discountRatio);
        dest.writeDouble(price);
        dest.writeString(code);
        dest.writeDouble(weight);
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        if (categoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(categoryId);
        }
        if (storeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(storeId);
        }
        if (inSlider == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(inSlider);
        }
        if (mostPopular == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mostPopular);
        }
        if (recentlyCome == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(recentlyCome);
        }
        if (numberOfSales == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numberOfSales);
        }
        if (amount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(amount);
        }
        if (maxOrder == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(maxOrder);
        }
        if (amountLeft == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(amountLeft);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(image);
        if (hasDiscount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hasDiscount);
        }
        if (inFavorite == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(inFavorite);
        }
        dest.writeString(rate);
        dest.writeString(imageUrl);
        if (hasColors == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hasColors);
        }
        dest.writeString(name);
        dest.writeString(details);
        dest.writeString(currency);
        dest.writeParcelable(store, flags);
        dest.writeTypedList(colors);
        dest.writeTypedList(sizes);
        dest.writeTypedList(images);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public double getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(double discountRatio) {
        this.discountRatio = discountRatio;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getInSlider() {
        return inSlider;
    }

    public void setInSlider(Integer inSlider) {
        this.inSlider = inSlider;
    }

    public Integer getMostPopular() {
        return mostPopular;
    }

    public void setMostPopular(Integer mostPopular) {
        this.mostPopular = mostPopular;
    }

    public Integer getRecentlyCome() {
        return recentlyCome;
    }

    public void setRecentlyCome(Integer recentlyCome) {
        this.recentlyCome = recentlyCome;
    }

    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMaxOrder() {
        return maxOrder;
    }

    public void setMaxOrder(Integer maxOrder) {
        this.maxOrder = maxOrder;
    }

    public Integer getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(Integer amountLeft) {
        this.amountLeft = amountLeft;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Integer hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Integer getInFavorite() {
        return inFavorite;
    }

    public void setInFavorite(Integer inFavorite) {
        this.inFavorite = inFavorite;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getHasColors() {
        return hasColors;
    }

    public void setHasColors(Integer hasColors) {
        this.hasColors = hasColors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("productId", productId).append("realPrice", realPrice).append("discountRatio", discountRatio).append("price", price).append("code", code).append("weight", weight).append("status", status).append("categoryId", categoryId).append("storeId", storeId).append("inSlider", inSlider).append("mostPopular", mostPopular).append("recentlyCome", recentlyCome).append("numberOfSales", numberOfSales).append("amount", amount).append("maxOrder", maxOrder).append("amountLeft", amountLeft).append("createdAt", createdAt).append("updatedAt", updatedAt).append("image", image).append("hasDiscount", hasDiscount).append("inFavorite", inFavorite).append("rate", rate).append("imageUrl", imageUrl).append("hasColors", hasColors).append("name", name).append("details", details).append("currency", currency).append("store", store).append("colors", colors).append("sizes", sizes).append("images", images).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sizes).append(weight).append(store).append(imageUrl).append(inFavorite).append(amountLeft).append(currency).append(amount).append(productId).append(rate).append(details).append(createdAt).append(numberOfSales).append(name).append(maxOrder).append(discountRatio).append(realPrice).append(status).append(colors).append(categoryId).append(image).append(mostPopular).append(recentlyCome).append(code).append(hasDiscount).append(updatedAt).append(hasColors).append(price).append(inSlider).append(images).append(storeId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Product) == false) {
            return false;
        }
        Product rhs = ((Product) other);
        return new EqualsBuilder().append(sizes, rhs.sizes).append(weight, rhs.weight).append(store, rhs.store).append(imageUrl, rhs.imageUrl).append(inFavorite, rhs.inFavorite).append(amountLeft, rhs.amountLeft).append(currency, rhs.currency).append(amount, rhs.amount).append(productId, rhs.productId).append(rate, rhs.rate).append(details, rhs.details).append(createdAt, rhs.createdAt).append(numberOfSales, rhs.numberOfSales).append(name, rhs.name).append(maxOrder, rhs.maxOrder).append(discountRatio, rhs.discountRatio).append(realPrice, rhs.realPrice).append(status, rhs.status).append(colors, rhs.colors).append(categoryId, rhs.categoryId).append(image, rhs.image).append(mostPopular, rhs.mostPopular).append(recentlyCome, rhs.recentlyCome).append(code, rhs.code).append(hasDiscount, rhs.hasDiscount).append(updatedAt, rhs.updatedAt).append(hasColors, rhs.hasColors).append(price, rhs.price).append(inSlider, rhs.inSlider).append(images, rhs.images).append(storeId, rhs.storeId).isEquals();
    }

}
