package net.selsela.almobarakeya.ui.privacypolicy;

import android.os.Bundle;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.ui.base.BaseActivity;

public class PrivacyPolicyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        getActivityComponent().inject(this);

    }
}
