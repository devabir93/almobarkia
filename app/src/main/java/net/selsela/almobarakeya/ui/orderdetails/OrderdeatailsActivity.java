package net.selsela.almobarakeya.ui.orderdetails;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.order.Order;
import net.selsela.almobarakeya.data.model.order.ProductData;
import net.selsela.almobarakeya.data.model.user.UserBody;
import net.selsela.almobarakeya.data.model.user.UserData;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.orders.OrdersPresenter;
import net.selsela.almobarakeya.ui.orders.OrdresMvpView;
import net.selsela.almobarakeya.util.Const;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderdeatailsActivity extends BaseActivity implements OrdresMvpView {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.order_num)
    TextView orderNum;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.order_label)
    TextView orderLabel;
    @BindView(R.id.product_label)
    TextView productLabel;
    @BindView(R.id.paymentmethod_label)
    TextView paymentmethodLabel;
    @BindView(R.id.service_label)
    TextView serviceLabel;
    @BindView(R.id.deliverycost_label)
    TextView deliverycostLabel;
    @BindView(R.id.discountcode_label)
    TextView discountcodeLabel;
    @BindView(R.id.orderstaotal_label)
    TextView orderstaotalLabel;
    @BindView(R.id.discointvalue_label)
    TextView discointvalueLabel;
    @BindView(R.id.discountvalue_textView)
    TextView discountvalueTextView;
    @BindView(R.id.Paymentamount_label)
    TextView PaymentamountLabel;
    @BindView(R.id.paymentTax_label)
    TextView paymentTaxLabel;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.user_data)
    TextView userData;
    @BindView(R.id.username_label)
    TextView usernameLabel;
    @BindView(R.id.user_addresslabel)
    TextView userAddresslabel;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.product_data)
    TextView productData;
    @BindView(R.id.orderdeatils_list)
    RecyclerView orderdeatilsList;
    @BindView(R.id.status_type)
    TextView statusType;
    @BindView(R.id.product_sumtexView)
    TextView productSumtexView;
    @BindView(R.id.payment_type)
    TextView paymentType;
    @BindView(R.id.service)
    TextView service;
    @BindView(R.id.cost)
    TextView cost;
    @BindView(R.id.discount_code)
    TextView discountCode;
    @BindView(R.id.allorders)
    TextView allorders;
    @BindView(R.id.code_textView)
    TextView codeTextView;
    @BindView(R.id.dicountevalue)
    TextView dicountevalue;
    @BindView(R.id.cost_all)
    TextView costAll;
    MaterialDialog dialog;

    private OrderDeatailsRecyclerViewAdapter adapter;
    private UserData user;

    @Inject
    OrdersPresenter ordersPresenter;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdeatails);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        ordersPresenter .attachView(this);
        activityTitle = getString(R.string.order_details);
        initToolbar();
        user = mUserSession.getCurrentUser();
        order = getIntent().getParcelableExtra(Const.Details);
        if (order == null)
            return;
        orderTime.setText(order.getCreatedAtText());
        orderNum.setText(order.getOrderNumber() + "");
        statusType.setText(order.getStatus().getName());
        if (order.getProducts() != null)
            productSumtexView.setText(order.getProducts().size() + "");
        paymentType.setText(order.getPayment().getName());
        service.setText(order.getServiceCost() + "" + order.getCountry().getCurreny());
        cost.setText(order.getTransportCost() + "" + order.getCountry().getCurreny());
        allorders.setText(order.getPrice() + "" + order.getCountry().getCurreny());
        costAll.setText(order.getPrice() + "" + order.getCountry().getCurreny());

        if (order.getCouponRatio().equals("0.00")) {
            discountcodeLabel.setVisibility(View.GONE);
            discointvalueLabel.setVisibility(View.GONE);
            discountvalueTextView.setVisibility(View.GONE);
            discountCode.setVisibility(View.GONE);
            dicountevalue.setVisibility(View.GONE);
            codeTextView.setVisibility(View.GONE);

        } else {
            discountvalueTextView.setText(order.getCouponRatio() + order.getCountry().getCurreny());
            discountCode.setText(order.getCouponCode());
        }

        usernameLabel.setText(order.getUser().getName());
        //userAddresslabel.setText(order.getAddress().getFullAddress());
//        userAddresslabel.setText(order.getAddress().getGovTxt() + "" + " " + order.getAddress().getAreaTxt() + "" + " " + order.getAddress().getBlockNumber() + "" + order.getAddress().getStreet()
//                + " " + order.getAddress().getAreaTxt() + " " + order.getAddress().getGovTxt() + " " + order.getAddress().getFlatNumber() + ""
//        );

        showProducts(order.getProducts());
    }


    private void showEvaluateDialog(final Product product) {

        dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_evaluate, false)
                .contentGravity(GravityEnum.START)
                .build();
        dialog.show();
        View view2 = dialog.getCustomView();

        TextView verifyButton = view2.findViewById(R.id.send_textView);
        TextView closeTextView = view2.findViewById(R.id.cancel_textView);
        final RatingBar ratingBar = view2.findViewById(R.id.rate_bar);
        final TextView ratetextView = view2.findViewById(R.id.rate_label);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratetextView.setText(rating + "");
            }
        });
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasInternetConnection()) {
                    final UserBody userBody = new UserBody();
                    userBody.setToken(user.getToken());
                    userBody.setUser_id(user.getId());
                    float rateVal = ratingBar.getRating();
                    userBody.setOrderId(order.getId());
                    userBody.setProducts("[[" + product.getId() + "," + rateVal + "]]");
                    ordersPresenter.rate_product(getApplicationContext(),userBody);

                } else
                    hasActiveInternetConnection(false);
                 dialog.dismiss();
            }
        });

        closeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    @Override
    public void showOrders(List<Order> orders) {

    }

    public void showProducts(List<ProductData> products) {
        orderdeatilsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new OrderDeatailsRecyclerViewAdapter(products, this, new OrderDeatailsRecyclerViewAdapter.CallBack() {
            @Override
            public void onEmployeClick(Product product) {
                showEvaluateDialog(product);

            }
        });
        adapter.showRateButton(order.getStatusId()==4);
        adapter.setCurrency(order.getCountry().getCurreny());
        orderdeatilsList.setAdapter(adapter);
        orderdeatilsList.setNestedScrollingEnabled(false);


    }
    @Override
    public void isSuccess(Boolean isSuccess) {
        if (isSuccess)
            dialog.dismiss();
    }
}
