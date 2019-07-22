package net.selsela.almobarakeya.ui.favorites;

import net.selsela.almobarakeya.data.model.home.Product;

public class FavProduct {
    private final Product fav;

    public FavProduct(Product favProduct) {
        this.fav = favProduct;
    }

    public Product getFav() {
        return fav;
    }

    @Override
    public String toString() {
        return "FavProduct{" +
                "fav=" + fav +
                '}';
    }
}
