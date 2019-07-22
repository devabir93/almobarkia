package net.selsela.almobarakeya.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.config.ConfigData;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.countries.CountriesActivity;

import javax.inject.Inject;

public class SplachScreenActivity extends BaseActivity implements MainMvpView {

    private final int SPLASH_DISPLAY_LENGTH = 3000;


    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);
        getActivityComponent().inject(this);
        mainPresenter.attachView(this);
        mainPresenter.getSettingData();
    }

    @Override
    public void showSettingData(ConfigData settingData) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if (isFirstRun()) {
                    Intent mainIntent = new Intent(SplachScreenActivity.this, CountriesActivity.class);
                    SplachScreenActivity.this.startActivity(mainIntent);
                    SplachScreenActivity.this.finish();
                } else {
                    Intent intent = new Intent(SplachScreenActivity.this, MainActivity.class);
                    SplachScreenActivity.this.startActivity(intent);
                    SplachScreenActivity.this.finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
