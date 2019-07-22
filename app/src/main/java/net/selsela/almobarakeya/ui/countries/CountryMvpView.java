package net.selsela.almobarakeya.ui.countries;


import net.selsela.almobarakeya.data.model.country.Country;
import net.selsela.almobarakeya.ui.base.MvpView;

import java.util.List;

public interface CountryMvpView extends MvpView {

    void showCountries(List<Country> countries);

}
