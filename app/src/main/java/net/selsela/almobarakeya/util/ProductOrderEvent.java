package net.selsela.almobarakeya.util;

public class ProductOrderEvent {
    Integer productId;
    Integer colorId;
    Integer imgId;

    public ProductOrderEvent(Integer productId, Integer colorId, Integer imgId) {
        this.productId = productId;
        this.colorId = colorId;
        this.imgId = imgId;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }
}
