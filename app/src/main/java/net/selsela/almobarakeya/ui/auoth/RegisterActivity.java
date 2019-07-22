package net.selsela.almobarakeya.ui.auoth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.country.Country;
import net.selsela.almobarakeya.data.model.user.UserBody;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.contact.PhoneKeyRecyclerViewAdapter;
import net.selsela.almobarakeya.ui.main.MainActivity;
import net.selsela.almobarakeya.ui.shoppingbasket.ShoppingBasketActivity;
import net.selsela.almobarakeya.util.Const;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements LoginMvpView, View.OnTouchListener {

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
    private Country country;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        loginPresenter.attachView(this);
        activityTitle = "";
        initToolbar();
        country = preferencesHelper.getCountry();
        if (country != null)
            phoneKey.setText(country.getPrefix() + "");

//        confirmPasswordLoginEditText.performClick();
//        passwordLoginEditText.performClick();
//        confirmPasswordLoginEditText.setOnTouchListener(this);
//        passwordLoginEditText.setOnTouchListener(this);
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
                userBody.setCountryId(country.getId());
                loginPresenter.register(this, userBody);
            }
        } else
            hasActiveInternetConnection(false);
    }

    @Override
    public void isSuccess(Boolean status) {
        super.isSuccess(status);
        if (status) {
            if (getIntent().hasExtra(Const.TYPE)
                    && getIntent().getStringExtra(Const.TYPE).equals(ShoppingBasketActivity.class.getSimpleName())
            ) {
                startActivity(new Intent(this, ShoppingBasketActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK)
                );
            } else
                startActivity(new Intent(this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK)
                );
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

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {


            case R.id.password_Login_editText:

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        passwordLoginEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        break;
                    case MotionEvent.ACTION_UP:
                        passwordLoginEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;
                }
                break;

            case R.id.confirm_password_Login_editText:

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        confirmPasswordLoginEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        break;
                    case MotionEvent.ACTION_UP:
                        confirmPasswordLoginEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;
                }
                break;
        }
        return true;
    }
}
