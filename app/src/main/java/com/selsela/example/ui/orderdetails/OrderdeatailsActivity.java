package com.selsela.example.ui.orderdetails;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.selsela.example.R;
import com.selsela.example.data.model.order.Order;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.util.Const;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderdeatailsActivity extends BaseActivity implements OrderDeatailsRecyclerViewAdapter.CallBack {

    String currency;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdeatails);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        activityTitle = getString(R.string.order_details);
        initToolbar();
        Order order = getIntent().getParcelableExtra(Const.Details);
        if (order == null)
            return;
        orderTime.setText(order.getCreatedAtText());
        orderNum.setText(order.getOrderNumber() + "");
        statusType.setText(order.getStatus().getName());
        if (order.getProducts() != null)
            productSumtexView.setText(order.getProducts().size() + "");
        paymentType.setText(order.getPayment().getName());
        service.setText(order.getServiceCost() + "" + getCurrency());
        cost.setText(order.getTransportCost() + "" + getCurrency());
        allorders.setText(order.getPrice() + "" + getCurrency());
        costAll.setText(order.getPrice() +""+getCurrency());

        if (order.getCouponRatio().equals("0.00")) {
            discountcodeLabel.setVisibility(View.GONE);
            discointvalueLabel.setVisibility(View.GONE);
            discountvalueTextView.setVisibility(View.GONE);
            discountCode.setVisibility(View.GONE);
            dicountevalue.setVisibility(View.GONE);
            codeTextView.setVisibility(View.GONE);


        } else {
            discountvalueTextView.setText(order.getCouponRatio());
            discountCode.setText(order.getCouponCode());
        }
        orderdeatilsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        orderdeatilsList.setAdapter(new OrderDeatailsRecyclerViewAdapter(this, this));
        orderdeatilsList.setNestedScrollingEnabled(false);
    }

    private void showChangeDialog() {

        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_evaluate, false)
                .contentGravity(GravityEnum.START)
                .build();
        dialog.show();
        View view2 = dialog.getCustomView();

        TextView verifyButton = view2.findViewById(R.id.send_textView);
        TextView closeTextView = view2.findViewById(R.id.cancel_textView);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    public void onEmployeClick() {
        showChangeDialog();
    }


}
