package com.selsela.example.ui.auoth;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.selsela.example.R;
import com.selsela.example.data.model.user.UserBody;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.contact.PhoneKeyRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements LoginMvpView {

    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.signup_textView)
    TextView signupTextView;
    @BindView(R.id.signup_requriments_label)
    TextView signupRequrimentsLabel;
    @BindView(R.id.username_EditText)
    TextInputEditText usernameEditText;
    @BindView(R.id.emailaddress_editText)
    TextInputEditText emailaddressEditText;
    @BindView(R.id.phone_number_editText)
    TextInputEditText phoneNumberEditText;
    @BindView(R.id.phoneKey)
    TextView phoneKey;
    @BindView(R.id.password_Login_editText)
    TextInputEditText passwordLoginEditText;
    @BindView(R.id.sure_bt)
    Button sureBt;

    @Inject
    LoginPresenter loginPresenter;
    @BindView(R.id.confirm_password_Login_editText)
    TextInputEditText confirmPasswordLoginEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        loginPresenter.attachView(this);
        activityTitle = "";
        initToolbar();
        phoneKey.setText(preferencesHelper.getCountry().getPrefix());

    }

    @OnClick(R.id.sure_bt)
    public void onViewClicked() {
        if (hasInternetConnection()) {
            if (!passwordLoginEditText.getText().toString().equals(confirmPasswordLoginEditText.getText().toString()))
                confirmPasswordLoginEditText.setError(this.getString(R.string.confirmpassword_label));

            if (usernameEditText.getText().length() < 1) {
                usernameEditText.setError(this.getString(R.string.name_erro));

            } else if (emailaddressEditText.getText().length() < 1) {
                emailaddressEditText.setError(this.getString(R.string.empty_label));


            } else if (passwordLoginEditText.getText().length() < 1) {
                passwordLoginEditText.setError(this.getString(R.string.passwordempty_label));

            } else if (confirmPasswordLoginEditText.getText().length() < 1) {
                confirmPasswordLoginEditText.setError(this.getString(R.string.passwordempty_label));
            } else {
                String username = usernameEditText.getText().toString();
                String email = emailaddressEditText.getText().toString();
                String mobile = phoneNumberEditText.getText().toString();
                String password = passwordLoginEditText.getText().toString();
                String confirmPassword = confirmPasswordLoginEditText.getText().toString();
                UserBody userBody = new UserBody();
                userBody.setName(username);
                userBody.setEmailL(email);
                userBody.setMobile(mobile);
                userBody.setPassword(password);
                userBody.setConfirm_password(confirmPassword);
                userBody.setCountryId(3);
                loginPresenter.register(this, userBody);
            }
        } else
            hasActiveInternetConnection(false);
    }

    @Override
    public void isSuccess(boolean isSuccess) {
        showMessageDialog("registerd done");

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
    public void onViewClickedPhone(View view2) {
        view2.getId();
        showPhoneDialog();
    }
}
