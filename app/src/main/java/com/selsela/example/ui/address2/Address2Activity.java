package com.selsela.example.ui.address2;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.ui.address1.AddressRecyclerViewAdapter;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.payment.PaymentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Address2Activity extends BaseActivity {


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
    @BindView(R.id.address_label)
    TextView addressLabel;
    @BindView(R.id.list_label)
    TextView listLabel;
    @BindView(R.id.arlanguage_label)
    TextView arlanguageLabel;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.enlanguage_label)
    TextView enlanguageLabel;
    @BindView(R.id.switch_layout)
    ConstraintLayout switchLayout;
    @BindView(R.id.building_nmuber_EditText)
    TextInputEditText buildingNmuberEditText;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.buildingnumber)
    TextView buildingnumber;
    @BindView(R.id.floor_numberEditText)
    TextInputEditText floorNumberEditText;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.floornumber)
    TextView floornumber;
    @BindView(R.id.flat_numberEditText)
    TextInputEditText flatNumberEditText;
    @BindView(R.id.line6)
    View line6;
    @BindView(R.id.productnumber)
    TextView productnumber;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.lay2)
    ConstraintLayout lay2;
    @BindView(R.id.back_btt_textView)
    TextView backBttTextView;
    @BindView(R.id.followup_textView)
    TextView followupTextView;
    @BindView(R.id.address_list)
    RecyclerView addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address2);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.followup_textView, R.id.back_btt_textView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.followup_textView:
                Intent intent = new Intent(Address2Activity.this, PaymentActivity.class);
                startActivity(intent);
                break;
            case R.id.back_btt_textView:
                onBackPressed();
                break;
        }
    }

    @OnClick({R.id.address_label, R.id.list_label})
    public void onViewClickedlist(View view) {
        switch (view.getId()) {
            case R.id.address_label:
                onAddresss();
                break;
            case R.id.list_label:
                addressList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                addressList.setAdapter(new AddressRecyclerViewAdapter());
                onList();
                break;
        }
    }

    private void onAddresss() {
        addressList.setVisibility(View.GONE);
        lay2.setVisibility(View.VISIBLE);
        switchLayout.setVisibility(View.VISIBLE);
        addressLabel.setTextColor(getResources().getColor(R.color.brown));
        addressLabel.setBackgroundColor(getResources().getColor(R.color.colorprimary));

        listLabel.setTextColor(getResources().getColor(R.color.colorprimary));
        listLabel.setBackgroundColor(getResources().getColor(R.color.white));




    }

    private void onList() {
        addressList.setVisibility(View.VISIBLE);
        switchLayout.setVisibility(View.GONE);
        lay2.setVisibility(View.GONE);
        addressLabel.setTextColor(getResources().getColor(R.color.colorprimary));
        addressLabel.setBackgroundColor(getResources().getColor(R.color.white));
        listLabel.setTextColor(getResources().getColor(R.color.brown));
        listLabel.setBackgroundColor(getResources().getColor(R.color.colorprimary));


    }
}
