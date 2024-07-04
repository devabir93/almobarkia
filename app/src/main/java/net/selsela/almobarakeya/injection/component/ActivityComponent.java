package net.selsela.almobarakeya.injection.component;

import net.selsela.almobarakeya.injection.PerActivity;
import net.selsela.almobarakeya.injection.module.ActivityModule;
import net.selsela.almobarakeya.ui.about.AboutActivity;
import net.selsela.almobarakeya.ui.address.AddressActivity;
import net.selsela.almobarakeya.ui.address.OldAddressFragment;
import net.selsela.almobarakeya.ui.address.new_address.NewAddressFragment;
import net.selsela.almobarakeya.ui.address.new_address.NewAddressMoreFragment;
import net.selsela.almobarakeya.ui.auoth.GuestScreenActivity;
import net.selsela.almobarakeya.ui.auoth.LoginActivity;
import net.selsela.almobarakeya.ui.auoth.RegisterActivity;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.base.BaseFragment;
import net.selsela.almobarakeya.ui.categories.ProductListFragment;
import net.selsela.almobarakeya.ui.contact.ContactActivity;
import net.selsela.almobarakeya.ui.countries.CountriesActivity;
import net.selsela.almobarakeya.ui.favorites.FavoritesActivity;
import net.selsela.almobarakeya.ui.home.HomeFragment;
import net.selsela.almobarakeya.ui.main.MainActivity;
import net.selsela.almobarakeya.ui.main.SplachScreenActivity;
import net.selsela.almobarakeya.ui.notifications.NotificationsActivity;
import net.selsela.almobarakeya.ui.orderdetails.OrderdeatailsActivity;
import net.selsela.almobarakeya.ui.orders.OrdersFragment;
import net.selsela.almobarakeya.ui.payment.PaymentActivity;
import net.selsela.almobarakeya.ui.previeworder.PreviewActivity;
import net.selsela.almobarakeya.ui.privacypolicy.PrivacyPolicyActivity;
import net.selsela.almobarakeya.ui.productdeatials.ProductDetailsActivity;
import net.selsela.almobarakeya.ui.productlist.ProductListActivity;
import net.selsela.almobarakeya.ui.shoppingbasket.ShoppingBasketActivity;
import net.selsela.almobarakeya.ui.specialorder.SpecialOrderFragment;
import net.selsela.almobarakeya.ui.updateprofile.UpdateProfileActivity;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(BaseActivity baseActivity);

    void inject(OrderdeatailsActivity orderdeatailsActivity);

    void inject(ContactActivity contactActivity);

    void inject(UpdateProfileActivity updateProfileActivity);

    void inject(BaseFragment baseFragment);

    void inject(FavoritesActivity favoritesActivity);

    void inject(OrdersFragment ordersFragment);

    void inject(LoginActivity loginActivity);

    void inject(NotificationsActivity notificationsActivity);

    void inject(GuestScreenActivity guestScreenActivity);

    void inject(RegisterActivity registerActivity);

    void inject(AboutActivity aboutActivity);

    void inject(MainActivity mainActivity);

    void inject(CountriesActivity countriesActivity);

    void inject(SplachScreenActivity splachScreenActivity);

    void inject(HomeFragment homeFragment);

    void inject(ProductDetailsActivity productDetailsActivity);

    void inject(ShoppingBasketActivity shoppingBasketActivity);

    void inject(OldAddressFragment oldAddressFragment);

    void inject(NewAddressFragment newAddressFragment);

    void inject(NewAddressMoreFragment newAddressMoreFragment);

    void inject(PreviewActivity previewActivity);

    void inject(PaymentActivity paymentActivity);

    void inject(AddressActivity addressActivity);

    void inject(PrivacyPolicyActivity privacyPolicyActivity);

    void inject(ProductListActivity productDetailsActivity);

    void inject(SpecialOrderFragment specialOrderFragment);

    void inject(ProductListFragment productListFragment);


}
