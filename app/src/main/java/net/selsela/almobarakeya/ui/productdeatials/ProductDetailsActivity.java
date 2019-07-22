package net.selsela.almobarakeya.ui.productdeatials;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Color;
import net.selsela.almobarakeya.data.model.home.Image;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.home.Size;
import net.selsela.almobarakeya.data.model.send_order.ProductOrderBody;
import net.selsela.almobarakeya.data.remote.SelselaService;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.productlist.ColorRecyclerViewAdapter;
import net.selsela.almobarakeya.ui.productlist.SizeRecyclerViewAdapter;
import net.selsela.almobarakeya.ui.shoppingbasket.ShoppingBasketActivity;
import net.selsela.almobarakeya.util.AppUtils;
import net.selsela.almobarakeya.util.Const;
import net.selsela.almobarakeya.util.Utils;
import com.stfalcon.frescoimageviewer.ImageViewer;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
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


    public Double totalPrice = 0.0;
    @BindView(R.id.product_title)
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
    @BindView(R.id.like_imageView)
    ImageView likeImageView;
    @BindView(R.id.addcart_textView)
    TextView addcartTextView;
    @BindView(R.id.rating_label)
    TextView ratingLabel;
    @BindView(R.id.custom_indicator)
    PagerIndicator customIndicator;
    @BindView(R.id.currency)
    TextView currency;
    @BindView(R.id.label11)
    TextView label11;
    @BindView(R.id.discaount_percentage_value)
    TextView discaountPercentageValue;
    @BindView(R.id.filter_size)
    TextView filterSize;
    @BindView(R.id.size_list)
    RecyclerView sizeRecyclerview;
    @BindView(R.id.colorfilter_label)
    TextView colorfilterLabel;
    @BindView(R.id.color_list)
    RecyclerView colorList;
    @BindView(R.id.line1)
    View line1;
    ImageView bigImageview;
    @Inject
    ProductsPresenter productsPresenter;
    Image selectedImage;
    @BindView(R.id.slider)
    SliderLayout mDemoSlider;
    private TextView addToCartSheet;
    private NumberPicker numberPicker;
    private Product product;
    private String productPrice;
    private Color selectedColor;
    private Size selectedSize;
    private ProductOrderBody productOrder;
    private List<String> imagesUriForFullScreen;
    private TextView costValue;
    private DialogSizeRecyclerViewAdapter dialogSize;
    private String newPrice;
    private TextView maxOrderValue;
    private TextView cartNo;
    private int mNotifCount = 0;
    private SizeRecyclerViewAdapter sizeAdapter;
    private ColorRecyclerViewAdapter colorAdapter;
    private List<Size> sizeList;
    private Boolean isExceed;
    private TextView maxOrderLabel;

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
        initProduct();

    }

    private void initProduct() {
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
                discaountPercentageValue.setText(product.getDiscountRatio() + " %");
            } else if (product.getDiscountRatio() == 0) {
                realPrice.setVisibility(View.INVISIBLE);
                discaountPercentageValue.setVisibility(View.INVISIBLE);
                label11.setVisibility(View.INVISIBLE);
            }
            presentproductpriceLabel.setText(product.getPrice() + "");
            productDetailsTextView.setHtml(product.getDetails());
            ownerStoreLabel.setText(product.getStore().getName());
            currency.setText(getCurrency());
            ratingLabel.setText(product.getRate());
            if (product.getInFavorite() != null) {
                if (product.getInFavorite() == 1) {
                    likeImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_likedheart));
                } else if (product.getInFavorite() == 0) {
                    likeImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_like));
                }
            }

            if (product.getSizes() != null && product.getSizes().size() > 0) {
                sizeRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                sizeAdapter = new SizeRecyclerViewAdapter(product.getSizes(), this, null);
                sizeAdapter.setClickable(false);
                sizeRecyclerview.setAdapter(sizeAdapter);
            } else
                filterSize.setVisibility(View.GONE);


            if (product.getColors() != null && product.getColors().size() > 0) {
                colorList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                colorAdapter = new ColorRecyclerViewAdapter(product.getColors(), this, null);
                colorAdapter.setClickable(false);
                colorList.setAdapter(colorAdapter);
            } else
                colorfilterLabel.setVisibility(View.GONE);

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
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //add your extra information
            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra", image.getImage());
            mDemoSlider.addSlider(defaultSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setCustomIndicator(customIndicator);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        new ImageViewer.Builder(ProductDetailsActivity.this, imagesUriForFullScreen).setStartPosition(mDemoSlider.getCurrentPosition()).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_details, menu);
        MenuItem notificationItem = menu.findItem(R.id.cart);
        notificationItem.setVisible(true);
        RelativeLayout notifications = (RelativeLayout) notificationItem.getActionView();
        cartNo = notifications.findViewById(R.id.cart_count);
        if (mNotifCount > 0) {
            cartNo.setVisibility(View.VISIBLE);
            cartNo.setText(mNotifCount + "");
        } else {
            cartNo.setVisibility(View.INVISIBLE);
        }
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailsActivity.this, ShoppingBasketActivity.class));
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.cart:
                Intent intent = new Intent(this, ShoppingBasketActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
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
        maxOrderLabel = sheetView.findViewById(R.id.maxorder_num);
        dialogSize = new DialogSizeRecyclerViewAdapter(this, this);
        if (product.getMaxOrder() > 0)
            numberPicker.setMax(product.getMaxOrder());
        else {
            maxOrderValue.setVisibility(View.GONE);
            maxOrderLabel.setVisibility(View.GONE);
        }
        maxOrderValue.setText(product.getMaxOrder() + "");


        RecyclerView colorRecyclerview = sheetView.findViewById(R.id.product_list);
        colorRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        if (product.getHasColors() == 2) {
            sizeList = product.getSizes();
        } else if (product.getHasColors() == 0)
            sizeRecyclerview.setVisibility(View.GONE);

        colorRecyclerview.setAdapter(new GalleryAdapter(this, product.getImages(), new GalleryAdapter.Callback() {
            @Override
            public void onImageClick(Image image) {
                selectedImage = image;
                initNumPicker();
                if (image.getColor() != null && image.getColor().size() > 0)
                    selectedColor = image.getColor().get(0);
                Glide.with(getApplicationContext()).load(selectedImage.getImageUrl()).thumbnail(.7f).into(bigImageview);
                if (product.getHasColors() == 1)
                    sizeList = selectedColor.getSizes();
                dialogSize.setData(sizeList);
                if (sizeList != null && sizeList.size() > 0)
                    onSizeClick(sizeList.get(0), 0);

            }
        }));


        RecyclerView chooseSizeRecyclerView = sheetView.findViewById(R.id.size_list);
        chooseSizeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        chooseSizeRecyclerView.setAdapter(dialogSize);


        /////// set first image clicked

        if (product.getImages() != null && product.getImages().size() > 0) {
            selectedImage = product.getImages().get(0);

            if (selectedImage.getColor() != null && selectedImage.getColor().size() > 0) {
                selectedColor = selectedImage.getColor().get(0);
                if (product.getHasColors() == 1)
                    sizeList = selectedColor.getSizes();
                selectedSize = sizeList.get(0);
                dialogSize.setData(selectedColor.getSizes());
                dialogSize.notifyDataSetChanged();
                if (sizeList != null && sizeList.size() > 0)
                    onSizeClick(selectedSize, 0);
                productsPresenter.getProductById(product.getProductId(), selectedColor.getColorId(), selectedColor.getProductImageId(), selectedSize.getSizeId());
                //  bigImageview.setBackgroundColor(ViewUtil.getHexColor(selectedColor.getColorHexa()));
            } else
                productsPresenter.getProductById(product.getProductId(), 0, 0, 0);

            Glide.with(this).load(selectedImage.getImageUrl()).thumbnail(.7f).into(bigImageview);


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

//        numberPicker.setLimitExceededListener(new LimitExceededListener() {
//            @Override
//            public void limitExceeded(int limit, int exceededValue) {
//                Timber.d("limit %s , exceededvalue %s", limit, exceededValue);
//                if (exceededValue >= limit) {
//                    showMessageDialog(getString(R.string.exceed_amount_left));
//                }
//            }
//        });
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
        numberPicker.refresh();
        numberPicker.setValue(1);
        costValue.setText(Utils.formatDecimal(String.valueOf(totalPrice)));

    }


    @Override
    public void isExceedWeight(Boolean aBoolean) {
        super.isExceedWeight(aBoolean);
        this.isExceed = aBoolean;
        Timber.d("isExceedWeight %s", aBoolean);
        if (aBoolean) {
            numberPicker.setValue(productOrder.getQuantity() - 1);
            numberPicker.setActionEnabled(ActionEnum.INCREMENT, false);
            showMessageDialog(this.getString(R.string.exceed_weight));
        } else {
            numberPicker.setActionEnabled(ActionEnum.INCREMENT, true);
            productsPresenter.saveProduct(productOrder);
        }
    }

    public void addToCart(double newPrice) {
        Timber.d("new price %s", newPrice);
        Timber.d("numberPicker.getValue() %s", numberPicker.getValue());
        newPrice = Double.parseDouble(AppUtils.calculateEquation(Utils.arabicToDecimal(String.valueOf(newPrice))
                + "+" + totalPrice));
        costValue.setText(Utils.formatDecimal(String.valueOf(totalPrice)));
        // totalPriceValue.setText(totalPrice + getCurrency());
        productOrder = new ProductOrderBody();
        productOrder.setProduct(product);
        productOrder.setQuantity(numberPicker.getValue());
        productOrder.setColor(selectedColor);
        if (selectedColor != null)
            productOrder.setColorId(selectedColor.getColorId());
        productOrder.setSize(selectedSize);
        if (selectedSize != null)
            productOrder.setSizeId(selectedSize.getSizeId());
        productOrder.setWeight(product.getWeight() * numberPicker.getValue());
        productOrder.setOrderId(product.getProductId());
        productOrder.setPriceForSingleItem(String.valueOf(product.getPrice()));
        productOrder.setPrice(String.valueOf(newPrice));
        productOrder.setUserId(getUserId());
        productOrder.setImageId(selectedImage.getId());
        productOrder.setImageUrl(selectedImage.getImageUrl());
        productsPresenter.checkMaxWeight(productOrder);


    }

    @Override
    public void showCartPrice(Double price) {
        this.totalPrice = price;
        if (costValue != null)
            costValue.setText(Utils.formatDecimal(String.valueOf(totalPrice)));
    }


    @Override
    public void showCartBadge(Integer i) {
        Timber.d("i %s", i);
        mNotifCount = i;
        if (cartNo != null)
            if (i > 0) {
                Timber.d("i %s", i);
                cartNo.setVisibility(View.VISIBLE);
                cartNo.setText(i + "");
            } else {
                cartNo.setVisibility(View.INVISIBLE);
            }

        invalidateOptionsMenu();
    }

    @Override
    public void showSavedProductOrder(ProductOrderBody productOrder) {
        super.showSavedProductOrder(productOrder);
        Timber.d("showSavedProductOrder %s", productOrder);
        this.productOrder = productOrder;
        if (productOrder != null) {
            if (productOrder.getQuantity() != null && productOrder.getQuantity() > 0) {
                if (numberPicker != null) {
                    addToCartSheet.setVisibility(View.GONE);
                    numberPicker.setVisibility(View.VISIBLE);
                    numberPicker.setValue(productOrder.getQuantity());
                }
            } else
                initNumPicker();
        } else
            initNumPicker();
    }

    @Override
    public void onSizeClick(Size size, int pos) {
        selectedSize = size;
        dialogSize.selected(pos);
        maxOrderValue.setVisibility(View.VISIBLE);
        maxOrderLabel.setVisibility(View.VISIBLE);
        maxOrderValue.setText(size.getPivot().getAmount() + "");
        // initNumPicker();
        productsPresenter.getProductById(product.getProductId(), selectedColor.getColorId(), selectedColor.getProductImageId(), selectedSize.getSizeId());
        if (size.getPivot().getAmount() > 0)
            numberPicker.setMax(size.getPivot().getAmount());

    }

    @OnClick({R.id.like_imageView, R.id.addcart_textView, R.id.product_details_label})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.like_imageView:
                productsPresenter.addToFav(product.getProductId(), this);
                break;
            case R.id.addcart_textView:
                showAddToCartDialog();
                break;

            case R.id.product_details_label:
                if (productDetailsTextView.getVisibility() == View.VISIBLE)
                    productDetailsTextView.setVisibility(View.GONE);
                else
                    productDetailsTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void isSuccess(Boolean status) {
        super.isSuccess(status);
        if (status) {
            toggleFav();
        }
    }

    private void toggleFav() {
        product.setInFavorite(product.getInFavorite() == 1 ? 0 : 1);
        if (product.getInFavorite() != null) {
            if (product.getInFavorite() == 1) {
                likeImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_likedheart));
            } else if (product.getInFavorite() == 0) {
                likeImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_like));
            }
        }
    }
}
