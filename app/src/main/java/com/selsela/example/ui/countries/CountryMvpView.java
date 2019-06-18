package com.selsela.example.ui.countries;


import com.selsela.example.data.model.country.Country;
import com.selsela.example.ui.base.MvpView;

import java.util.List;

public interface CountryMvpView extends MvpView {

    void showCountries(List<Country> countries);

}
