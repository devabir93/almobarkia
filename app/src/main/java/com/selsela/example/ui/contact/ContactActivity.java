package com.selsela.example.ui.contact;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.selsela.example.R;
import com.selsela.example.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.welcome_textView)
    TextView welcomeTextView;
    @BindView(R.id.welcome_textView2)
    TextView welcomeTextView2;
    @BindView(R.id.textInputEditText)
    TextInputEditText textInputEditText;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.user_data)
    TextView userData;
    @BindView(R.id.emailaddress_editText)
    TextInputEditText emailaddressEditText;
    @BindView(R.id.phone_number_editText)
    TextInputEditText phoneNumberEditText;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.phone_num)
    TextView phoneNum;
    @BindView(R.id.phoneKey)
    TextView phoneKey;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.email_adress)
    TextView emailAdress;
    @BindView(R.id.topic_title_editText)
    TextInputEditText topicTitleEditText;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.topic)
    TextView topic;
    @BindView(R.id.send_textView_background)
    TextView sendTextViewBackground;
    @BindView(R.id.contact_tetxtView)
    TextView contactTetxtView;
    @BindView(R.id.call_action)
    TextView callAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.phoneKey)
    public void onViewClicked() {
        showChangeDialog();
    }

    private void showChangeDialog() {

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
