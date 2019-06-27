package com.selsela.example.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.data.local.DataHolder;
import com.selsela.example.data.model.coupon.Copone;
import com.selsela.example.data.model.order.Order;
import com.selsela.example.data.model.order.OrderBody;
import com.selsela.example.data.model.send_order.ProductOrderBody;
import com.selsela.example.data.model.user.UserData;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.previeworder.PreviewActivity;
import com.selsela.example.ui.shoppingbasket.PaymentMvpView;
import com.selsela.example.ui.shoppingbasket.PaymentPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentActivity extends BaseActivity implements PaymentMvpView {

    public static final int CASH = 1;
    public static final int VISA = 2;
    public static final int KNET = 3;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.checked_ic)
    ImageView checkedIc;
    @BindView(R.id.dottline)
    View dottline;
    @BindView(R.id.ic_cridet)
    ImageView icCridet;
    @BindView(R.id.dottline2)
    View dottline2;
    @BindView(R.id.username_tetxtView)
    TextView usernameTetxtView;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.user_data)
    TextView userData;
    @BindView(R.id.phonenumber_tetxtView)
    TextView phonenumberTetxtView;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.phone_num)
    TextView phoneNum;
    @BindView(R.id.address_InputEditText)
    TextView addressInputEditText;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.user_address)
    TextView userAddress;
    @BindView(R.id.back_btt_textView)
    TextView backBttTextView;
    @BindView(R.id.followup_textView)
    TextView followupTextView;
    @BindView(R.id.paymentmethod_label)
    TextView paymentmethodLabel;
    @BindView(R.id.cash_label)
    TextView cashLabel;
    @BindView(R.id.chachradio_butt)
    RadioButton chachradioButt;
    @BindView(R.id.cobon_label)
    TextView cobonLabel;
    @BindView(R.id.knet_label)
    TextView knetLabel;
    @BindView(R.id.knetradio_butt)
    RadioButton knetradioButt;
    @BindView(R.id.coboncode_label)
    TextView coboncodeLabel;
    @BindView(R.id.cobon_textInput)
    TextInputEditText cobonTextInput;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.cobon_code)
    TextView cobonCode;
    @BindView(R.id.verify)
    Button verify;
    private OrderBody orderBody;


    @Inject
    PaymentPresenter paymentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getActivityComponent().inject(this);
        paymentPresenter.attachView(this);
        ButterKnife.bind(this);
        activityTitle = getString(R.string.payment_activity);
        initToolbar();
        orderBody = DataHolder.getOrder();

        UserData userData = mUserSession.getCurrentUser();
        usernameTetxtView.setText(userData.getName());
        phonenumberTetxtView.setText(userData.getMobile() + "");
        addressInputEditText.setText(orderBody.getFull_address());

        cobonTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                orderBody.setCoupon_id(0);
                orderBody.setCoupon(null);
                cobonTextInput.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);

            }
        });

    }

    @OnClick({R.id.back_btt_textView, R.id.followup_textView, R.id.verify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.followup_textView:
                orderBody.setPayment_type(CASH);
                DataHolder.setOrder(orderBody);

                Intent intent = new Intent(PaymentActivity.this, PreviewActivity.class);
                startActivity(intent);
                break;
            case R.id.back_btt_textView:
                onBackPressed();
                break;
            case R.id.verify:
                if (cobonTextInput.getText().length() > 0)
                    paymentPresenter.checkCopon(cobonTextInput.getText().toString());
                break;
        }
    }

    @Override
    public void showSuccess(String msg) {

    }

    @Override
    public void showSavedOrders(List<ProductOrderBody> productOrderList) {

    }

    @Override
    public void doPayment(Order order) {

    }

    @Override
    public void addedToBag(Integer aBoolean) {

    }

    @Override
    public void showCoupone(Copone copone) {
        if (copone.getRatio() > 0 && copone.getStatus() == 1) {
            orderBody.setCoupon_id(copone.getId());
            orderBody.setCoupon(copone);
            cobonTextInput.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this, R.drawable.ic_checked_confirm), null);
        }

    }
}
