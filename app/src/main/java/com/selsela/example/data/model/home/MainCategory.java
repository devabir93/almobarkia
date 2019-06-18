
package com.selsela.example.data.model.home;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MainCategory implements Parcelable
{

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
    public final static Creator<MainCategory> CREATOR = new Creator<MainCategory>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MainCategory createFromParcel(Parcel in) {
            return new MainCategory(in);
        }

        public MainCategory[] newArray(int size) {
            return (new MainCategory[size]);
        }

    }
    ;

    protected MainCategory(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.showInMain = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.productsCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.products, (Product.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainCategory() {
    }

    /**
     * 
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
        return new ToStringBuilder(this).append("id", id).append("image", image).append("status", status).append("showInMain", showInMain).append("productsCount", productsCount).append("name", name).append("products", products).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(productsCount).append(status).append(name).append(image).append(products).append(showInMain).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MainCategory) == false) {
            return false;
        }
        MainCategory rhs = ((MainCategory) other);
        return new EqualsBuilder().append(id, rhs.id).append(productsCount, rhs.productsCount).append(status, rhs.status).append(name, rhs.name).append(image, rhs.image).append(products, rhs.products).append(showInMain, rhs.showInMain).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(image);
        dest.writeValue(status);
        dest.writeValue(showInMain);
        dest.writeValue(productsCount);
        dest.writeValue(name);
        dest.writeList(products);
    }

    public int describeContents() {
        return  0;
    }

}
