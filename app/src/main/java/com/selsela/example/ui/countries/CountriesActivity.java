package com.selsela.example.ui.countries;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.data.model.country.Country;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CountriesActivity extends BaseActivity implements CountryMvpView {
    @Inject
    CountryPresenter countryPresenter;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.language_label)
    TextView languageLabel;
    @BindView(R.id.languageenglish_label)
    TextView languageenglishLabel;
    @BindView(R.id.root_layout)
    ConstraintLayout rootLayout;
    @BindView(R.id.arlanguage_label)
    TextView arlanguageLabel;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.enlanguage_label)
    TextView enlanguageLabel;
    @BindView(R.id.switch_layout)
    ConstraintLayout switchLayout;
    @BindView(R.id.choosecountry_label)
    TextView choosecountryLabel;
    @BindView(R.id.choosecountrydesc)
    TextView choosecountrydesc;
    @BindView(R.id.country_list)
    RecyclerView countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        countryPresenter.attachView(this);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                countryPresenter.getCountries();
            }
        });

        countryPresenter.getCountries();

    }

    @Override
    public void showCountries(List<Country> countries) {
        Timber.d("showCountries %s", countries);
        countryList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        countryList.setAdapter(new ContriesRecyclerViewAdapter(countries, this, new ContriesRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onCountrySelected(Country country, int position) {
                preferencesHelper.addCountry(country);
                setFirstRun(false);

                Intent intent = new Intent(CountriesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }));
    }
}
