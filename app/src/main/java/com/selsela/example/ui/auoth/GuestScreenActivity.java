package com.selsela.example.ui.auoth;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.selsela.example.R;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.contact.PhoneKeyRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuestScreenActivity extends BaseActivity {

    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.welcome_textView)
    TextView welcomeTextView;
    @BindView(R.id.visitor)
    TextView visitor;
    @BindView(R.id.data_label)
    TextView dataLabel;
    @BindView(R.id.username_EditText)
    TextInputEditText usernameEditText;
    @BindView(R.id.phone_number_editText)
    TextInputEditText phoneNumberEditText;
    @BindView(R.id.phoneKey)
    TextView phoneKey;
    @BindView(R.id.followup_bt)
    Button followupBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_reg);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.phoneKey, R.id.followup_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phoneKey:
                showPhoneDialog();
                break;
            case R.id.followup_bt:
                break;
        }
    }

    private void showPhoneDialog() {

        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialogchoosecountry_layout, false)
                .contentGravity(GravityEnum.START)
                .build();
        dialog.show();
        View view2 = dialog.getCustomView();
        RecyclerView phoneKeyRecyclerView = view2.findViewById(R.id.country_list);
        phoneKeyRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        phoneKeyRecyclerView.setAdapter(new PhoneKeyRecyclerViewAdapter());
        ImageView close_Button = view2.findViewById(R.id.close_imageView);
        close_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}
