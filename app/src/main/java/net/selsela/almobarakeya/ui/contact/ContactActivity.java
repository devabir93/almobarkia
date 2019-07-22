package net.selsela.almobarakeya.ui.contact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.config.Config;
import net.selsela.almobarakeya.data.model.user.UserBody;
import net.selsela.almobarakeya.ui.auoth.LoginMvpView;
import net.selsela.almobarakeya.ui.auoth.LoginPresenter;
import net.selsela.almobarakeya.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class ContactActivity extends BaseActivity implements LoginMvpView {

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
    @BindView(R.id.mobile)
    TextView callAction;
    @Inject
    LoginPresenter loginPresenter;
    @BindView(R.id.email)
    TextView email;
    private Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        loginPresenter.attachView(this);

        activityTitle = getString(R.string.callus_label);
        initToolbar();
        config = preferencesHelper.getCurrentUserSetting().getConfig();
        if (config != null) {
            callAction.setText(config.getMobile());
            email.setText(config.getEmail());
            phoneKey.setText(preferencesHelper.getCountry().getPrefix() + "");
        }
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


    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(ContactActivity.this,
                    android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.CALL_PHONE,
                        },
                        1);
                return false;
            } else {
                Log.e("DB", "PERMISSION GRANTED");
                return true;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Timber.d("Permission is granted");
            return true;
        }
    }

    @SuppressLint("MissingPermission")
    public void call_action() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + config.getMobile()));
        startActivity(callIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                }
                return;
            }
        }
    }

    @OnClick({R.id.email, R.id.mobile, R.id.send_textView_background})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.email:
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{config.getEmail()});
                startActivity(emailIntent);
                break;
            case R.id.mobile:
                if (isPermissionGranted()) {
                    call_action();
                }
                break;
            case R.id.send_textView_background:
                send();
                break;
        }
    }

    public void send() {
        if (hasInternetConnection()) {
            if (textInputEditText.getText().length() < 1) {
                textInputEditText.setError(this.getString(R.string.name_erro));
            } else if (emailaddressEditText.getText().length() < 1) {
                emailaddressEditText.setError(this.getString(R.string.empty_label));
            } else if (phoneNumberEditText.getText().length() < 1) {
                phoneNumberEditText.setError(this.getString(R.string.emptyphone_label));
            } else if (topicTitleEditText.getText().length() < 1) {
                topicTitleEditText.setError(this.getString(R.string.emptytopic_label));
            } else {
                String username = textInputEditText.getText().toString();
                String email = emailaddressEditText.getText().toString();
                String mobile = phoneNumberEditText.getText().toString();
                String mess = topicTitleEditText.getText().toString();
                UserBody userBody = new UserBody();
                userBody.setName(username);
                userBody.setEmailL(email);
                userBody.setMobile(mobile);
                userBody.setMessage(mess);
                userBody.setCountryId(preferencesHelper.getCountry().getId());
                loginPresenter.contact_us(this, userBody);


            }
        } else
            hasActiveInternetConnection(false);
    }
}