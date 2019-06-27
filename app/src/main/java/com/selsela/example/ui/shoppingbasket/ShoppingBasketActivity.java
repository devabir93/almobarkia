package com.selsela.example.ui.shoppingbasket;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.data.local.DataHolder;
import com.selsela.example.data.model.coupon.Copone;
import com.selsela.example.data.model.order.Order;
import com.selsela.example.data.model.order.OrderBody;
import com.selsela.example.data.model.send_order.ProductOrderBody;
import com.selsela.example.data.model.user.UserData;
import com.selsela.example.ui.address.AddressActivity;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.util.AppUtils;
import com.selsela.example.util.Utils;
import com.travijuu.numberpicker.library.Enums.ActionEnum;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class ShoppingBasketActivity extends BaseActivity implements PaymentMvpView, CartRecyclerViewAdapter.UpdateDataClickListener {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.shoppingbasket_list)
    RecyclerView shoppingbasketList;
    @BindView(R.id.total_amount)
    TextView totalAmount;
    @BindView(R.id.contiue_buying_action)
    TextView contiueBuyingAction;
    @BindView(R.id.total_amount_label)
    TextView totalAmountLabel;
    @BindView(R.id.cont_shopping_btn_empty)
    Button contShoppingBtnEmpty;
    @BindView(R.id.empty_bag_layout)
    LinearLayout emptyBagLayout;
    @BindView(R.id.cart_layout)
    ConstraintLayout cartLayout;
    private List<ProductOrderBody> mProductOrderList;
    private int quantity, pos;
    private String newPrice;
    private CartRecyclerViewAdapter productAdapter;
    private OrderBody orderBody;

    @Inject
    PaymentPresenter paymentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_basket);
        getActivityComponent().inject(this);

        ButterKnife.bind(this);

        paymentPresenter.attachView(this);
        paymentPresenter.getSavedOrders();
        activityTitle = getString(R.string.cart_label);
        initToolbar();
        DataHolder.clearOrder();
        orderBody = new OrderBody();
    }

    @OnClick(R.id.contiue_buying_action)
    public void onViewClicked() {
        prepareOrder();
    }


    private void prepareOrder() {
        orderBody.setProductsStrings(Arrays.deepToString(convertObjectToArray(mProductOrderList)));
        orderBody.setProductList(mProductOrderList);
        DataHolder.setOrder(orderBody);
        startActivity(new Intent(this, AddressActivity.class));
    }

    @Override
    public void onDeleteOrder(int position) {
        deleteOrder(mProductOrderList.get(position));
    }

    private void deleteOrder(ProductOrderBody productOrderBody) {
        productAdapter.notifyDataSetChanged();
        paymentPresenter.deleteOrder(productOrderBody);
    }

    @Override
    public void onPickQuantity(int pos, int quantity, String newPrice, ActionEnum actionEnum) {
        this.pos = pos;
        this.quantity = quantity;
        this.newPrice = newPrice;
        updateQuantity();
        productAdapter.notifyItemChanged(pos);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        showMessageDialog(message);
    }

    private void updateQuantity() {
        Timber.d("updateQuantity");
        mProductOrderList.get(pos).setQuantity(quantity);
        mProductOrderList.get(pos).setPrice(newPrice);
        productAdapter.notifyItemChanged(pos);
        productAdapter.notifyDataSetChanged();
        paymentPresenter.saveProduct(mProductOrderList.get(pos));
        calculateTotalPrice();
    }

    @Override
    public void showSuccess(String msg) {

    }

    @Override
    public void showSavedOrders(List<ProductOrderBody> productOrderList) {
        mProductOrderList = productOrderList;
        emptyBagLayout.setVisibility(View.GONE);
        cartLayout.setVisibility(View.VISIBLE);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        shoppingbasketList.setLayoutManager(mLayoutManager);
        productAdapter = new CartRecyclerViewAdapter(this, this);
        shoppingbasketList.setAdapter(productAdapter);
        productAdapter.setList(productOrderList);
        productAdapter.setCurrency(getCurrency());
        calculateTotalPrice();

    }

    @Override
    public void doPayment(Order order) {

    }

    @Override
    public void addedToBag(Integer aBoolean) {

    }

    @Override
    public void showCoupone(Copone copone) {

    }

    @Override
    public void showEmpty() {
        super.showEmpty();
        emptyBagLayout.setVisibility(View.VISIBLE);
        cartLayout.setVisibility(View.GONE);
    }

    public void calculateTotalPrice() {
        double price = 0;
        for (ProductOrderBody productOrderBody :
                mProductOrderList) {
            price += Double.parseDouble(AppUtils.calculateEquation(Utils.arabicToDecimal(String.valueOf(productOrderBody.getPriceForSingleItem()))
                    + "*" + productOrderBody.getQuantity()));
        }
        totalAmount.setText(Utils.formatDecimal(String.valueOf(price)));
        //productCount.setText(String.valueOf(mProductOrderList.size()));
        orderBody.setPrice(Double.parseDouble(Utils.formatDecimal(String.valueOf(price))));
    }

    public Integer[][] convertObjectToArray(List<ProductOrderBody> players) {
        Integer[][] playersObjects = new Integer[players.size()][4];

        for (int i = 0; i < playersObjects.length; i++) {
            playersObjects[i][0] = (players.get(i).getOrderId());
            playersObjects[i][1] = (players.get(i).getQuantity());
            playersObjects[i][2] = (players.get(i).getColor().getColorId());
            playersObjects[i][3] = (players.get(i).getSizeId());

        }

        Timber.d("convertObjectToArray %s", Arrays.deepToString(playersObjects));
        return playersObjects;

    }
}
