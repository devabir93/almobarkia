package com.selsela.example.ui.updateprofile;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.selsela.example.R;
import com.selsela.example.data.model.user.UserBody;
import com.selsela.example.data.model.user.UserData;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.contact.PhoneKeyRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateProfileActivity extends BaseActivity implements UpdateMvpView {
    @Inject
    UpdatePresenter updatePresenter;
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

    private UserData user;
    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        updatePresenter.attachView(this);

        activityTitle="";
        initToolbar();
        fillWithUserData();
    }

    private void fillWithUserData() {
        user = mUserSession.getCurrentUser();
        if (user != null) {
            emailaddressEditText.setText(user.getEmail());
            textInputEditText.setText(user.getName());
            phoneNumberEditText.setText(user.getMobile() + "");
            //passwordLoginEditText.setText(user.getPasswordResetCode());
        }
    }

    @OnClick(R.id.changepass_textView)
    public void onViewClicked(View view2) {
        view2.getId();
        showChangeDialog();
    }

    private void showChangeDialog() {
        dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_changepass, false)
                .contentGravity(GravityEnum.START)
                .build();
        dialog.show();
        View view2 = dialog.getCustomView();

        TextView verifyButton = view2.findViewById(R.id.confirm_textView);
        final TextView current_pass_input = view2.findViewById(R.id.password_current_tetxtView);
        final TextView password_confirm_input = view2.findViewById(R.id.password_confirm_tetxtView);
        final TextView password_new_input = view2.findViewById(R.id.password_new_tetxtView);
        TextView closeTextView = view2.findViewById(R.id.cancel_textView);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dialog.dismiss();

                if (hasInternetConnection()) {
                    if (!password_new_input.getText().toString().equals(password_confirm_input.getText().toString()))
                        password_new_input.setError(getString(R.string.confirmpassword_label));
                    if (password_confirm_input.getText().length() < 1) {
                        password_confirm_input.setError(getString(R.string.passwordempty_label));
                    } else if (password_new_input.getText().length() < 1) {
                        password_new_input.setError(getString(R.string.passwordempty_label));

                    } else {
                        UserBody userBody = new UserBody();
                        String current_pass = current_pass_input.getText().toString();
                        String new_pass = password_new_input.getText().toString();
                        String confirm_pass = password_confirm_input.getText().toString();
                        userBody.setConfirm_password(confirm_pass);
                        userBody.setNew_password(new_pass);
                        userBody.setOld_password(current_pass);
                        userBody.setToken(user.getToken());
                        userBody.setUser_id(user.getId());
                        updatePresenter.change_password(getApplicationContext(), userBody);
                    }

                } else
                    hasActiveInternetConnection(false);

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

    @Override
    public void isSuccess(boolean isSuccess) {
        if (isSuccess)
            dialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveUpdate();
                return true;

            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void saveUpdate() {
        UserBody userBody = new UserBody();
        String username = textInputEditText.getText().toString();
        String email = emailaddressEditText.getText().toString();
        String mobile = phoneNumberEditText.getText().toString();
        if (hasInternetConnection()) {
            if (textInputEditText.getText().length() < 1) {
                textInputEditText.setError(this.getString(R.string.name_erro));
            } else if (emailaddressEditText.getText().length() < 1) {
                emailaddressEditText.setError(this.getString(R.string.empty_label));
            } else if (phoneNumberEditText.getText().length() < 1) {
                phoneNumberEditText.setError(this.getString(R.string.emptyphone_label));
            } else {
                userBody.setName(username);
                userBody.setEmailL(email);
                userBody.setMobile(mobile);
                userBody.setToken(user.getToken());
                userBody.setUser_id(user.getId());
                userBody.setCountryId(preferencesHelper.getCountry().getId());
                updatePresenter.update_profile(this, userBody);

            }
        } else

            hasActiveInternetConnection(false);

    }


}

