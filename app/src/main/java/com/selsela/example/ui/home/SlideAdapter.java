package com.selsela.example.ui.home;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.selsela.example.R;
import com.selsela.example.data.model.home.Product;

public class SlideAdapter extends BaseSliderView {

    private Product product;
    String curr;

    public SlideAdapter(Context context) {
        super(context);
    }

    void setData(Product products, String currency) {
        this.product = products;
        this.curr = currency;
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.slider_view_item, null);
        ImageView target = (ImageView) v.findViewById(R.id.daimajia_slider_image);
        TextView price = (TextView) v.findViewById(R.id.price);
        TextView currency = (TextView) v.findViewById(R.id.currency);
        TextView prev_price = (TextView) v.findViewById(R.id.prev_price);
        TextView title = (TextView) v.findViewById(R.id.title);
        price.setText(product.getPrice() + "");
        if (product.getDiscountRatio() > 0) {
            prev_price.setVisibility(View.VISIBLE);
            prev_price.setPaintFlags(prev_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            prev_price.setText(product.getRealPrice() + "");
        } else if (product.getDiscountRatio() == 0) {
            prev_price.setVisibility(View.GONE);
        }
        title.setText(product.getName());
        currency.setText(curr);
        bindEventAndShow(v, target);
        return v;
    }
}
