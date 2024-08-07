package net.selsela.almobarakeya.ui.auoth;

import android.content.Intent;
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
import timber.log.Timber;

public class GuestScreenActivity extends BaseActivity implements LoginMvpView {
    @Inject
    LoginPresenter loginPresenter;
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
        loginPresenter.attachView(this);
        ButterKnife.bind(this);
        activityTitle = "";
        initToolbar();
        Country country = preferencesHelper.getCountry();
        if (country != null)
            phoneKey.setText(country.getPrefix() + "");
    }

    @OnClick({R.id.phoneKey, R.id.followup_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phoneKey:
                //showPhoneDialog();
                break;
            case R.id.followup_bt:
                guestLogin();
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

    public void guestLogin() {
        if (hasInternetConnection()) {
            if (usernameEditText.getText().length() < 1) {
                usernameEditText.setError(this.getString(R.string.empty_label));
            } else if (phoneNumberEditText.getText().length() < 1) {
                phoneNumberEditText.setError(this.getString(R.string.phone_empty_label));
            } else {
                UserBody userBody = new UserBody();
                userBody.setMobile(phoneNumberEditText.getText().toString());
                userBody.setName(usernameEditText.getText().toString());
                userBody.setCountryId(preferencesHelper.getCountry().getId());
                loginPresenter.guest_log_reg(this, userBody);
            }

        } else
            hasActiveInternetConnection(false);

    }

    @Override
    public void isSuccess(Boolean status) {
        super.isSuccess(status);
        Timber.d("isSuccess");
        if (status) {
            Timber.d("loginResponse");
            if (getIntent().hasExtra(Const.TYPE)
                    && getIntent().getStringExtra(Const.TYPE).equals(ShoppingBasketActivity.class.getSimpleName())) {
                startActivity(new Intent(this, ShoppingBasketActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK));
            } else
                startActivity(new Intent(this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }
}
