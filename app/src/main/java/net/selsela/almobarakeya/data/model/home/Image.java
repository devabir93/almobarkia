
package net.selsela.almobarakeya.data.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Image implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("is_main")
    @Expose
    private Integer isMain;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("color")
    @Expose
    private List<Color> color = null;

    /**
     * No args constructor for use in serialization
     */
    public Image() {
    }

    /**
     * @param id
     * @param color
     * @param imageUrl
     * @param image
     * @param isMain
     * @param productId
     */
    public Image(Integer id, Integer productId, String image, Integer isMain, String imageUrl, List<Color> color) {
        super();
        this.id = id;
        this.productId = productId;
        this.image = image;
        this.isMain = isMain;
        this.imageUrl = imageUrl;
        this.color = color;
    }

    protected Image(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            productId = null;
        } else {
            productId = in.readInt();
        }
        image = in.readString();
        if (in.readByte() == 0) {
            isMain = null;
        } else {
            isMain = in.readInt();
        }
        imageUrl = in.readString();
        type = in.readString();
        color = in.createTypedArrayList(Color.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (productId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(productId);
        }
        dest.writeString(image);
        if (isMain == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isMain);
        }
        dest.writeString(imageUrl);
        dest.writeString(type);
        dest.writeTypedList(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("productId", productId).append("image", image).append("isMain", isMain).append("imageUrl", imageUrl).append("color", color).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(color).append(imageUrl).append(image).append(isMain).append(productId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Image) == false) {
            return false;
        }
        Image rhs = ((Image) other);
        return new EqualsBuilder().append(id, rhs.id).append(color, rhs.color).append(imageUrl, rhs.imageUrl).append(image, rhs.image).append(isMain, rhs.isMain).append(productId, rhs.productId).isEquals();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
