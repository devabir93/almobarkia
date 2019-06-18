package com.selsela.example.ui.privacypolicy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.selsela.example.R;
import com.selsela.example.ui.base.BaseActivity;

public class PrivacyPolicyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        getActivityComponent().inject(this);

    }
}
