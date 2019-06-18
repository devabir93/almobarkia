package com.selsela.example.ui.previeworder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreviewActivity extends BaseActivity {

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
    @BindView(R.id.billdetails_label)
    TextView billdetailsLabel;
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
    @BindView(R.id.back_btt_textView)
    TextView backBttTextView;
    @BindView(R.id.confirm_textView)
    TextView confirmTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        getActivityComponent().inject(this);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btt_textView, R.id.confirm_textView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_textView:
                Intent intent = new Intent(PreviewActivity.this, WebViewActivity.class);
                break;
            case R.id.back_btt_textView:
                onBackPressed();
                break;
        }
    }
}
