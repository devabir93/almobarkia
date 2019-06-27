package com.selsela.example.ui.auoth;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.selsela.example.R;
import com.selsela.example.data.model.user.UserBody;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity
        implements LoginMvpView {

    public static final int LOGIN_RESULT = 1003;
    @Inject
    LoginPresenter loginPresenter;
    MaterialDialog dialog;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.login_textView)
    TextView loginTextView;
    @BindView(R.id.textInputEditText)
    TextInputEditText textInputEditText;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.user_data)
    TextView userData;
    @BindView(R.id.password_Login_editText)
    TextInputEditText passwordLoginEditText;
    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.sign_up)
    Button signUp;
    @BindView(R.id.visitor)
    TextView visitor;
    @BindView(R.id.forget_password)
    TextView forgetPassword;
    @BindView(R.id.linearLayout5)
    ConstraintLayout linearLayout5;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.passuser_data)
    TextView passuserData;


    public LoginActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ViewUtil.hideStatusBar(this);
        setContentView(R.layout.activity_login);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        loginPresenter.attachView(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    @Override
    public void isSuccess(boolean loginResponse) {

        if (loginResponse) {

            startActivity(new Intent(this, MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK)
            );
        }
        //else if (loginResponse)
          // dialog.dismiss();
    }

    private void login() {
        if (hasInternetConnection()) {

            if (textInputEditText.getText().length() < 1) {
                textInputEditText.setError(this.getString(R.string.empty_label));
            } else if (passwordLoginEditText.getText().length() < 1) {
                passwordLoginEditText.setError(this.getString(R.string.empty_label));
            } else {
                UserBody userBody = new UserBody();
                userBody.setMobile(textInputEditText.getText().toString());
                userBody.setPassword(passwordLoginEditText.getText().toString());
                loginPresenter.login(getApplicationContext(), userBody);
                userBody.setCountryId(3);

            }
        } else
            hasActiveInternetConnection(false);
    }


    @OnClick({R.id.login_bt, R.id.sign_up, R.id.visitor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_bt:
                login();
                break;
            case R.id.sign_up:
                Intent signintent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(signintent);
                finish();
                break;
            case R.id.visitor:
                Intent guestntent = new Intent(LoginActivity.this, GuestScreenActivity.class);
                startActivity(guestntent);
                finish();
                break;
        }
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @OnClick(R.id.forget_password)
    public void onViewClicked() {
        showChangeDialog();


    }

    private void showChangeDialog() {
        dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog2_forgetpass, false)
                .contentGravity(GravityEnum.START)
                .build();
        dialog.show();
        View view2 = dialog.getCustomView();
        final TextView email_addreess = view2.findViewById(R.id.email_editText);
        final TextView verifyButton  = view2.findViewById(R.id.send_label);
        final TextView cancelButton = view2.findViewById(R.id.cancel_textView);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasInternetConnection()) {
                    if (email_addreess.getText().length() < 1) {
                        email_addreess.setError(getString(R.string.empty_label));

                    } else {
                        UserBody userBody = new UserBody();
                        String email = email_addreess.getText().toString();
                        userBody.setEmailL(email);
                        loginPresenter.forget_password(getApplicationContext(), userBody);


                    }
                } else
                    hasActiveInternetConnection(false);

            }
                });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}

