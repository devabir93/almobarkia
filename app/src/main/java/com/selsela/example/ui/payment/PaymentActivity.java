package com.selsela.example.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.previeworder.PreviewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentActivity extends BaseActivity {

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getActivityComponent().inject(this);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btt_textView, R.id.followup_textView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.followup_textView:
                Intent intent = new Intent(PaymentActivity.this, PreviewActivity.class);
                startActivity(intent);
                break;
            case R.id.back_btt_textView:
                onBackPressed();
                break;
        }
    }


}
