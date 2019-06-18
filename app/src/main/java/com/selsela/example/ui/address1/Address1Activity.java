package com.selsela.example.ui.address1;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.selsela.example.R;
import com.selsela.example.ui.address2.Address2Activity;
import com.selsela.example.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Address1Activity extends BaseActivity {


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
    @BindView(R.id.addressspinner)
    MaterialSpinner addressspinner;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.user_data)
    TextView userData;
    @BindView(R.id.address2spinner)
    MaterialSpinner address2spinner;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.phone_num)
    TextView phoneNum;
    @BindView(R.id.product_InputEditText)
    TextInputEditText productInputEditText;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.productnumber)
    TextView productnumber;
    @BindView(R.id.streetnum_InputEditText)
    TextInputEditText streetnumInputEditText;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.streetnumber)
    TextView streetnumber;
    @BindView(R.id.jada_num_InputEditText)
    TextInputEditText jadaNumInputEditText;
    @BindView(R.id.line6)
    View line6;
    @BindView(R.id.jadanumber)
    TextView jadanumber;
    @BindView(R.id.back_btt_textView)
    TextView backBttTextView;
    @BindView(R.id.followup_textView)
    TextView followupTextView;
    @BindView(R.id.address_list)
    RecyclerView addressList;
    @BindView(R.id.lay1)
    ConstraintLayout addressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address1);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);




    }


    @OnClick({R.id.followup_textView, R.id.back_btt_textView})
    public void onViewClicked2(View view) {
        switch (view.getId()) {
            case R.id.followup_textView:
                Intent intent = new Intent(this, Address2Activity.class);
                startActivity(intent);
                break;
            case R.id.back_btt_textView:
                onBackPressed();
                break;
        }
    }




    @OnClick({R.id.address_label, R.id.list_label})
    public void onViewClickedList(View view) {
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
   addressLayout.setVisibility(View.VISIBLE);
   addressLabel.setTextColor(getResources().getColor(R.color.brown));
   addressLabel.setBackgroundColor(getResources().getColor(R.color.colorprimary));

   listLabel.setTextColor(getResources().getColor(R.color.colorprimary));
   listLabel.setBackgroundColor(getResources().getColor(R.color.white));




    }

    private void onList() {
        addressList.setVisibility(View.VISIBLE);
        addressLayout.setVisibility(View.GONE);
        addressLabel.setTextColor(getResources().getColor(R.color.colorprimary));
        addressLabel.setBackgroundColor(getResources().getColor(R.color.white));
        listLabel.setTextColor(getResources().getColor(R.color.brown));
        listLabel.setBackgroundColor(getResources().getColor(R.color.colorprimary));


    }
}
