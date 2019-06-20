package com.selsela.example.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.favorites.FavoritesFragment;
import com.selsela.example.ui.home.HomeFragment;
import com.selsela.example.ui.orders.OrdersFragment;
import com.selsela.example.ui.settings.SettingsFragment;
import com.selsela.example.ui.specialorder.SpecialOrderFragment;
import com.selsela.example.util.DialogFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @BindView(R.id.container)
    CoordinatorLayout container;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("");
                    toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.LightGray));
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.navigation_settings:
                    toolbar.setTitle("");
                    toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));
                    loadFragment(new SettingsFragment());
                    return true;
                case R.id.navigation_orders:
                    if (isUserLogged()) {
                        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));
                        toolbar.setTitle(R.string.title_myorders);
                        loadFragment(new OrdersFragment());
                        return true;
                    } else
                        DialogFactory.showAlertDialog(MainActivity.this, getString(R.string.signin_confrimation));

                    return false;

                case R.id.navigation_favourites:
                    if (isUserLogged()) {
                        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));
                        toolbar.setTitle(R.string.title_fav);
                        loadFragment(new FavoritesFragment());
                        return true;
                    } else
                        DialogFactory.showAlertDialog(MainActivity.this, getString(R.string.signin_confrimation));

                    return false;
                case R.id.navigation_specialorder:
                    toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));
                    toolbar.setTitle(R.string.title_specialorder);
                    loadFragment(new SpecialOrderFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar();
        toolbar.setTitle("");
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.LightGray));
        loadFragment(new HomeFragment());
    }


    public void toolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));
        }
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
