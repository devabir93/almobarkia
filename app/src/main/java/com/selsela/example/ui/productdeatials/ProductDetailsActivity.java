package com.selsela.example.ui.productdeatials;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.selsela.example.R;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.remote.SelselaService;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.shoppingbasket.ShoppingBasketActivity;
import com.selsela.example.util.Const;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class ProductDetailsActivity extends BaseActivity {

    @BindView(R.id.product_imageView)
    ImageView productImageView;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.product_descrption)
    TextView productDescrption;
    @BindView(R.id.productprice_label)
    TextView realPrice;
    @BindView(R.id.presentproductprice_label)
    TextView presentproductpriceLabel;
    @BindView(R.id.rating_bar)
    MaterialRatingBar ratingBar;
    @BindView(R.id.store_imageView)
    ImageView storeImageView;
    @BindView(R.id.store_name_label)
    TextView storeNameLabel;
    @BindView(R.id.owner_store_label)
    TextView ownerStoreLabel;
    @BindView(R.id.product_details_label)
    TextView productDetailsLabel;
    @BindView(R.id.product_details_textView)
    HtmlTextView productDetailsTextView;
    @BindView(R.id.basket_notification_ic)
    ImageView basketNotificationIc;
    @BindView(R.id.like_imageView)
    ImageView likeImageView;
    @BindView(R.id.constraintlayout)
    ConstraintLayout constraintlayout;
    @BindView(R.id.addcart_textView)
    TextView addcartTextView;
    @BindView(R.id.rating_label)
    TextView ratingLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        activityTitle = getString(R.string.product_details_label);
        initToolbar();

        Product product = getIntent().getParcelableExtra(Const.Details);
        if (product != null) {
            RequestOptions options = new RequestOptions()
                    .circleCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);


            if (product.getImage() != null)
                Glide.with(this).load(SelselaService.IMAGE_URL + product.getImage()).into(productImageView);

            if (product.getStore() != null && product.getStore().getImage() != null)

                Glide.with(this).load(SelselaService.IMAGE_URL + product.getStore().getImage()).apply(options).into(storeImageView);
            ratingBar.setRating(Float.parseFloat(product.getRate()));
            productDescrption.setText(product.getName());
            if (product.getDiscountRatio() > 0) {
                realPrice.setVisibility(View.VISIBLE);
                realPrice.setPaintFlags(realPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                realPrice.setText(product.getRealPrice() + "");
            } else if (product.getDiscountRatio() == 0) {
                realPrice.setVisibility(View.INVISIBLE);
            }
            presentproductpriceLabel.setText(product.getPrice() + "" + getCurrency());
            productDetailsTextView.setHtml(product.getDetails());
            ownerStoreLabel.setText(product.getStore().getName());
            ratingLabel.setText(product.getRate());

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.addcart_textView)
    public void onViewClicked(View view2) {
        view2.getId();
        showChangeDialog();
    }

    private void showChangeDialog() {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.productpopup, null);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();

        RecyclerView chooseRecyclerView = sheetView.findViewById(R.id.product_list);
        chooseRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        chooseRecyclerView.setAdapter(new DialogRecyclerViewAdapter());

        RecyclerView chooseSizeRecyclerView = sheetView.findViewById(R.id.size_list);
        chooseSizeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        chooseSizeRecyclerView.setAdapter(new DialogueSizeRecyclerViewAdapter());
        TextView cont_shopping = sheetView.findViewById(R.id.shop_label);
        TextView cont_purchase = sheetView.findViewById(R.id.continue_label);
        cont_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

        cont_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this, ShoppingBasketActivity.class);
                startActivity(intent);
            }
        });
    }
}
