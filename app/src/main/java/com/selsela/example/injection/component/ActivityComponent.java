package com.selsela.example.injection.component;

import com.selsela.example.injection.PerActivity;
import com.selsela.example.injection.module.ActivityModule;
import com.selsela.example.ui.about.AboutActivity;
import com.selsela.example.ui.auoth.LoginActivity;
import com.selsela.example.ui.auoth.RegisterActivity;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.base.BaseFragment;
import com.selsela.example.ui.categories.CategoriesActivity;
import com.selsela.example.ui.countries.CountriesActivity;
import com.selsela.example.ui.favorites.FavoritesFragment;
import com.selsela.example.ui.home.HomeFragment;
import com.selsela.example.ui.main.MainActivity;
import com.selsela.example.ui.main.SplachScreenActivity;
import com.selsela.example.ui.orderdetails.OrderdeatailsActivity;
import com.selsela.example.ui.orders.OrdersFragment;
import com.selsela.example.ui.productdeatials.ProductDetailsActivity;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(BaseActivity baseActivity);
    void inject(OrderdeatailsActivity orderdeatailsActivity);

    void inject(BaseFragment baseFragment);
    void inject(FavoritesFragment favoritesFragment);
    void inject(OrdersFragment  ordersFragment);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(AboutActivity aboutActivity);

    void inject(MainActivity mainActivity);

    void inject(CountriesActivity countriesActivity);

    void inject(SplachScreenActivity splachScreenActivity);
    void inject(CategoriesActivity categoriesActivity);

    void inject(HomeFragment homeFragment);
    void inject(ProductDetailsActivity productDetailsActivity);


}
