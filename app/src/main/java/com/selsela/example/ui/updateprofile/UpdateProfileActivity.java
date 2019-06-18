package com.selsela.example.ui.updateprofile;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.selsela.example.R;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.contact.PhoneKeyRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateProfileActivity extends BaseActivity {

    @BindView(R.id.ic_profilepicbackground)
    ImageView icProfilepic;
    @BindView(R.id.arlanguage_label)
    TextView arlanguageLabel;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.enlanguage_label)
    TextView enlanguageLabel;
    @BindView(R.id.switch_layout)
    ConstraintLayout switchLayout;
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
    @BindView(R.id.password_Login_editText)
    TextInputEditText passwordLoginEditText;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.password_textView)
    TextView passwordTextView;
    @BindView(R.id.changepass_textView)
    TextView changepassTextView;
    @BindView(R.id.profile_pic)
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.changepass_textView)
    public void onViewClicked(View view2) {
        view2.getId();
        showChangeDialog();
    }

    private void showChangeDialog() {

        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_changepass, false)
                .contentGravity(GravityEnum.START)
                .build();
        dialog.show();
        View view2 = dialog.getCustomView();

        TextView verifyButton = view2.findViewById(R.id.confirm_textView);
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
    @OnClick(R.id.phoneKey)
    public void onViewClicked2(View view2) {
        view2.getId();
        showPhoneDialog();
    }
}
