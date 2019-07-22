
package net.selsela.almobarakeya.data.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainCategory implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("show_in_main")
    @Expose
    private Integer showInMain;
    @SerializedName("products_count")
    @Expose
    private Integer productsCount;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("sub_categories")
    @Expose
    private List<MainCategory> subCategories = null;
    @SerializedName("parent_id")
    @Expose
    private int parentId;

    ///////filter
    @SerializedName("category")
    @Expose
    private MainCategory category = null;

    /**
     * No args constructor for use in serialization
     */
    public MainCategory() {
    }

    /**
     * @param id
     * @param productsCount
     * @param status
     * @param name
     * @param image
     * @param products
     * @param showInMain
     */
    public MainCategory(Integer id, String image, Integer status, Integer showInMain, Integer productsCount, String name, List<Product> products) {
        super();
        this.id = id;
        this.image = image;
        this.status = status;
        this.showInMain = showInMain;
        this.productsCount = productsCount;
        this.name = name;
        this.products = products;
    }

    protected MainCategory(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        image = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            showInMain = null;
        } else {
            showInMain = in.readInt();
        }
        if (in.readByte() == 0) {
            productsCount = null;
        } else {
            productsCount = in.readInt();
        }
        name = in.readString();
        products = in.createTypedArrayList(Product.CREATOR);
        subCategories = in.createTypedArrayList(MainCategory.CREATOR);
        parentId = in.readInt();
        category = in.readParcelable(MainCategory.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(image);
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        if (showInMain == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(showInMain);
        }
        if (productsCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(productsCount);
        }
        dest.writeString(name);
        dest.writeTypedList(products);
        dest.writeTypedList(subCategories);
        dest.writeInt(parentId);
        dest.writeParcelable(category, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainCategory> CREATOR = new Creator<MainCategory>() {
        @Override
        public MainCategory createFromParcel(Parcel in) {
            return new MainCategory(in);
        }

        @Override
        public MainCategory[] newArray(int size) {
            return new MainCategory[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getShowInMain() {
        return showInMain;
    }

    public void setShowInMain(Integer showInMain) {
        this.showInMain = showInMain;
    }

    public Integer getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "MainCategory{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", showInMain=" + showInMain +
                ", productsCount=" + productsCount +
                ", name='" + name + '\'' +
                ", products=" + products +
                ", subCategories=" + subCategories +
                '}';
    }

    public List<MainCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<MainCategory> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainCategory)) return false;

        MainCategory that = (MainCategory) o;

        if (getParentId() != that.getParentId()) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getImage() != null ? !getImage().equals(that.getImage()) : that.getImage() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(that.getStatus()) : that.getStatus() != null)
            return false;
        if (getShowInMain() != null ? !getShowInMain().equals(that.getShowInMain()) : that.getShowInMain() != null)
            return false;
        if (getProductsCount() != null ? !getProductsCount().equals(that.getProductsCount()) : that.getProductsCount() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        if (getProducts() != null ? !getProducts().equals(that.getProducts()) : that.getProducts() != null)
            return false;
        if (getSubCategories() != null ? !getSubCategories().equals(that.getSubCategories()) : that.getSubCategories() != null)
            return false;
        return getCategory() != null ? getCategory().equals(that.getCategory()) : that.getCategory() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getShowInMain() != null ? getShowInMain().hashCode() : 0);
        result = 31 * result + (getProductsCount() != null ? getProductsCount().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        result = 31 * result + (getSubCategories() != null ? getSubCategories().hashCode() : 0);
        result = 31 * result + getParentId();
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        return result;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public MainCategory getCategory() {
        return category;
    }

    public void setCategory(MainCategory category) {
        this.category = category;
    }
}
