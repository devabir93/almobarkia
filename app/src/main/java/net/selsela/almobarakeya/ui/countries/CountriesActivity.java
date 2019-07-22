package net.selsela.almobarakeya.ui.countries;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.country.Country;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.main.MainActivity;
import net.selsela.almobarakeya.util.Const;
import net.selsela.almobarakeya.util.language.Language;

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
    private Country selectedCountry;

    boolean isFromUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        activityTitle = "";
        initToolbar();
        countryPresenter.attachView(this);
        if (getIntent().hasExtra("update")) {
            isFromUpdate = true;
        }
        countryPresenter.getCountries();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                countryPresenter.getCountries();
            }
        });
        switch1.setChecked(languageUtils.getCurrentLang().equals(Language.ENGLISH()));

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    languageUtils.setEnglishLocale();
                else
                    languageUtils.setArabicLocale();
                if (isFromUpdate)
                    goToMain();
                else
                    CountriesActivity.this.recreate();

            }
        });


    }

    @Override
    public void showCartBadge(Integer i) {
        if (i > 0)
            showChangeCountryConfirmationDialog();
        else {
            logout();
            setSelected();
        }
    }

    public void showChangeCountryConfirmationDialog() {
        MaterialDialog materialDialog = new MaterialDialog.Builder(this)
                .title(R.string.change_country)
                .content(R.string.change_country_confirmation)
                .positiveText(R.string.delete_confirm)
                .negativeText(R.string.dialog_action_cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        logout();
                        setSelected();
                        goToMain();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                }).build();
        materialDialog.show();
    }

    private void setSelected() {
        preferencesHelper.addCountry(selectedCountry);
        if (!preferencesHelper.getCountry().getPrefix().equals(Const.KUWAIT))
            countryPresenter.getShippingBoxes();
        setFirstRun(false);
        Intent intent = new Intent(CountriesActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void showCountries(List<Country> countries) {
        Timber.d("showCountries %s", countries);
        countryList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        countryList.setAdapter(new ContriesRecyclerViewAdapter(countries, this, new ContriesRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onCountrySelected(Country country, int position) {
                selectedCountry = country;
                if (isFromUpdate) {
                    if (!country.getId().equals(preferencesHelper.getCountry().getId())) {
                        countryPresenter.getCartBadge();

                    } else {
                        setSelected();
                    }

                } else {
                    setSelected();
                }
            }
        }));
    }
}
