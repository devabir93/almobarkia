package com.selsela.example.ui.productdeatials;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.selsela.example.R;
import com.selsela.example.data.model.home.Color;
import com.selsela.example.data.model.home.Image;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.model.home.Size;
import com.selsela.example.data.model.send_order.ProductOrderBody;
import com.selsela.example.data.remote.SelselaService;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.shoppingbasket.ShoppingBasketActivity;
import com.selsela.example.util.AppUtils;
import com.selsela.example.util.Const;
import com.selsela.example.util.Utils;
import com.selsela.example.util.ViewUtil;
import com.stfalcon.frescoimageviewer.ImageViewer;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.LimitExceededListener;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import timber.log.Timber;

public class ProductDetailsActivity extends BaseActivity implements ProductsMvp, BaseSliderView.OnSliderClickListener, DialogSizeRecyclerViewAdapter.Callback {

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
    RelativeLayout basketNotificationIc;
    @BindView(R.id.like_imageView)
    ImageView likeImageView;
    @BindView(R.id.constraintlayout)
    ConstraintLayout constraintlayout;
    @BindView(R.id.addcart_textView)
    TextView addcartTextView;
    @BindView(R.id.rating_label)
    TextView ratingLabel;
    @BindView(R.id.bag_iv)
    ImageView bagIv;
    @BindView(R.id.cart_count)
    TextView cartCount;
    private LinearLayout addToCartSheet;
    private NumberPicker numberPicker;
    ImageView bigImageview;
    private Product product;
    private String productPrice;
    @Inject
    ProductsPresenter productsPresenter;
    private Color selectedColor;
    Image selectedImage;
    private Size selectedSize;
    private ProductOrderBody productOrder;
    @BindView(R.id.slider)
    SliderLayout mDemoSlider;
    private List<String> imagesUriForFullScreen;
    private TextView costValue;
    public Double totalPrice;
    private DialogSizeRecyclerViewAdapter dialogSize;
    private String newPrice;
    private TextView maxOrderValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        productsPresenter.attachView(this);
        productsPresenter.getCartBadge();
        activityTitle = getString(R.string.product_details_label);
        initToolbar();

        imagesUriForFullScreen = new ArrayList<>();
        product = getIntent().getParcelableExtra(Const.Details);
        if (product != null) {

            if (product.getImages() != null)
                setSliderViews(product.getImages());
            RequestOptions options = new RequestOptions()
                    .circleCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);

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
            if (product.getInFavorite() != null) {
                if (product.getInFavorite() == 1) {
                    likeImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_likedheart));
                } else if (product.getInFavorite() == 0) {
                    likeImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_like));
                }
            }

        }

    }

    private void setSliderViews(List<Image> images) {
        mDemoSlider.removeAllSliders();
        for (Image image : images) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            imagesUriForFullScreen.add(SelselaService.IMAGE_URL + image.getImage());
            defaultSliderView
                    .image(SelselaService.IMAGE_URL + image.getImage())
                    .setScaleType(BaseSliderView.ScaleType.CenterInside)
                    .setOnSliderClickListener(this);
            //add your extra information
            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra", image.getImage());
            mDemoSlider.addSlider(defaultSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        // mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        // startActivity(new Intent(getContext(), ProductDetailsActivity.class).putExtra(Const.Details, sliderProducts.get(mDemoSlider.getCurrentPosition())));
        new ImageViewer.Builder(ProductDetailsActivity.this, imagesUriForFullScreen).setStartPosition(mDemoSlider.getCurrentPosition()).show();
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
        showAddToCartDialog();
    }

    private void showAddToCartDialog() {
        productsPresenter.getCartPrice();
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.productpopup, null);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
        BottomSheetBehavior mBottomSheetBehavior = BottomSheetBehavior.from(((View) sheetView.getParent()));
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        mBottomSheetBehavior.setSkipCollapsed(true);
        addToCartSheet = sheetView.findViewById(R.id.add_to_cart);
        numberPicker = sheetView.findViewById(R.id.number_picker);
        bigImageview = sheetView.findViewById(R.id.product_imageView);
        costValue = sheetView.findViewById(R.id.cost_value);
        maxOrderValue = sheetView.findViewById(R.id.maxorder_num_value);
        dialogSize = new DialogSizeRecyclerViewAdapter(this, this);
        RecyclerView colorRecyclerview = sheetView.findViewById(R.id.product_list);
        colorRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        colorRecyclerview.setAdapter(new GalleryAdapter(this, product.getImages(), new GalleryAdapter.Callback() {
            @Override
            public void onImageClick(Image image) {
                selectedImage = image;
                if (image.getColor() != null && image.getColor().size() > 0)
                    selectedColor = image.getColor().get(0);
                Glide.with(getApplicationContext()).load(SelselaService.IMAGE_URL + selectedImage.getImageUrl()).thumbnail(.7f).into(bigImageview);
                dialogSize.setData(selectedColor.getSizes());
            }
        }));


        RecyclerView chooseSizeRecyclerView = sheetView.findViewById(R.id.size_list);
        chooseSizeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        chooseSizeRecyclerView.setAdapter(dialogSize);


        /////// set first image clicked

        if (product.getImages() != null && product.getImages().size() > 0) {
            selectedImage = product.getImages().get(0);
            selectedColor = selectedImage.getColor().get(0);
            selectedSize = selectedColor.getSizes().get(0);
            productsPresenter.getProductById(product.getProductId(), selectedColor.getColorId(), selectedColor.getProductImageId(), selectedSize.getSizeId());
            Glide.with(this).load(SelselaService.IMAGE_URL + selectedImage.getImageUrl()).thumbnail(.7f).into(bigImageview);
            bigImageview.setBackgroundColor(ViewUtil.getHexColor(selectedColor.getColorHexa()));
            dialogSize.setData(selectedColor.getSizes());
            onSizeClick(selectedSize, 0);
            dialogSize.notifyDataSetChanged();

        }
        numberPicker.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                if (value > 0) {
                    //if (action.equals(ActionEnum.INCREMENT))
                    newPrice = AppUtils.calculateEquation(Utils.arabicToDecimal(String.valueOf(product.getPrice()))
                            + "*" + numberPicker.getValue());

                    addToCart(Double.parseDouble(newPrice));
                }
            }
        });

        numberPicker.setLimitExceededListener(new LimitExceededListener() {
            @Override
            public void limitExceeded(int limit, int exceededValue) {
                Timber.d("limit %s , exceededvalue %s", limit, exceededValue);
                if (exceededValue > limit) {
                    showMessageDialog(getString(R.string.exceed_amount_left));
                }
            }
        });
        addToCartSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(product.getPrice());
                addToCartSheet.setVisibility(View.GONE);
                //goToMain();
            }
        });

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

    private void initNumPicker() {
        addToCartSheet.setVisibility(View.VISIBLE);
        numberPicker.setValue(1);
        costValue.setText(totalPrice + "");

    }

    public void addToCart(double newPrice) {
        Timber.d("new price %s", newPrice);
        newPrice = Double.parseDouble(AppUtils.calculateEquation(Utils.arabicToDecimal(String.valueOf(newPrice))
                + "+" + totalPrice));
        costValue.setText(String.valueOf(newPrice));
        // totalPriceValue.setText(totalPrice + getCurrency());
        if (selectedColor != null && selectedSize != null) {
            ProductOrderBody productOrder = new ProductOrderBody();
            productOrder.setProduct(product);
            productOrder.setQuantity(numberPicker.getValue());
            productOrder.setColor(selectedColor);
            productOrder.setColorId(selectedColor.getColorId());
            productOrder.setSize(selectedSize);
            productOrder.setSizeId(selectedSize.getSizeId());
            productOrder.setOrderId(product.getProductId());
            productOrder.setPriceForSingleItem(String.valueOf(product.getPrice()));
            productOrder.setPrice(String.valueOf(newPrice));
            productOrder.setUserId(getUserId());
            productsPresenter.saveProduct(productOrder);
        }
    }

    @Override
    public void showCartPrice(Double price) {
        this.totalPrice = price;
        if (costValue != null)
            costValue.setText(String.valueOf(totalPrice));
    }

    @Override
    public void showCartBadge(Integer integer) {
        super.showCartBadge(integer);
        if (integer > 0) {
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(integer + "");
        } else {
            cartCount.setVisibility(View.GONE);
        }

    }

    @Override
    public void showSavedProductOrder(ProductOrderBody productOrder) {
        super.showSavedProductOrder(productOrder);
        Timber.d("showSavedProductOrder %s", productOrder);
        this.productOrder = productOrder;
        if (productOrder != null && productOrder.getQuantity() > 0) {
            if (numberPicker != null) {
                addToCartSheet.setVisibility(View.GONE);
                numberPicker.setVisibility(View.VISIBLE);
                numberPicker.setValue(productOrder.getQuantity());
            }
        }
    }

    @Override
    public void onSizeClick(Size size, int pos) {
        selectedSize = size;
        dialogSize.selected(pos);
        maxOrderValue.setText(size.getPivot().getAmount() + "");
        productsPresenter.getProductById(product.getProductId(), selectedColor.getColorId(), selectedColor.getProductImageId(), selectedSize.getSizeId());
        initNumPicker();
    }
}
