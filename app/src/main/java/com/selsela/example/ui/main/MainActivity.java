package com.selsela.example.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
                    MainActivity.this.setTitle(R.string.title_homear);
                    loadFragment(new HomeFragment());

                    return true;
                case R.id.navigation_settings:
                    MainActivity.this.setTitle(R.string.title_settigs);
                    loadFragment(new SettingsFragment());
                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
                case R.id.navigation_orders:
                    MainActivity.this.setTitle(R.string.title_myorders);
                    loadFragment(new OrdersFragment());
                    return true;

                case R.id.navigation_favourites:
                    MainActivity.this.setTitle(R.string.title_fav);
                    loadFragment(new FavoritesFragment());
                    return true;

                case R.id.navigation_specialorder:
                    MainActivity.this.setTitle(R.string.title_specialorder);
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
        loadFragment(new HomeFragment());
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
