package net.selsela.almobarakeya.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.MainCategory;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.categories.ProductListFragment;
import net.selsela.almobarakeya.ui.home.HomeFragment;
import net.selsela.almobarakeya.ui.orders.OrdersFragment;
import net.selsela.almobarakeya.ui.settings.SettingsFragment;
import net.selsela.almobarakeya.ui.specialorder.SpecialOrderFragment;
import net.selsela.almobarakeya.util.DialogFactory;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @BindView(R.id.container)
    CoordinatorLayout container;
    @BindView(R.id.pos0)
    View pos0;
    @BindView(R.id.pos1)
    View pos1;
    @BindView(R.id.pos2)
    View pos2;
    @BindView(R.id.pos3)
    View pos3;
    @BindView(R.id.pos4)
    View pos4;
    @BindView(R.id.line_layout)
    LinearLayout lineLayout;
//    @BindView(R.id.main_toolbar)
//    Toolbar homeToolbar;

    private TextView mTextMessage;
    String FRAGMENT_1_TAG = "FRAGMENT_1_TAG";
    String FRAGMENT_2_TAG = "FRAGMENT_2_TAG";
    String FRAGMENT_3_TAG = "FRAGMENT_3_TAG";
    String FRAGMENT_4_TAG = "FRAGMENT_4_TAG";
    String FRAGMENT_5_TAG = "FRAGMENT_5_TAG";
    private String menuTag;


    private int postion = 0;
    Fragment fragment1 = new HomeFragment();
    Fragment fragment2 = ProductListFragment.newInstance(postion);
    Fragment fragment3 = new OrdersFragment();
    Fragment fragment4 = new SpecialOrderFragment();
    Fragment fragment5 = new SettingsFragment();
    Fragment active = fragment1;


    private void hideOtherViews() {
        for (int index = 0; index < ((ViewGroup) lineLayout).getChildCount(); ++index) {
            View nextChild = ((ViewGroup) lineLayout).getChildAt(index);
            nextChild.setVisibility(View.INVISIBLE); // This will somehow remove the view from Ui
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment5, "5").hide(fragment5).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment4, "4").hide(fragment4).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment3, "3").hide(fragment3).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment2, "2").hide(fragment2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment1, "1").commit();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        hideToolbar();
        getActivityComponent().inject(this);
        navView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                hideToolbar();
                hideOtherViews();
                pos0.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().hide(active).show(fragment1).commit();
                active = fragment1;
                return true;
            case R.id.navigation_categories:
                showToolbar();
                toolbar.setTitle(R.string.categories_label);
                hideOtherViews();
                pos1.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().hide(active).show(fragment2).commit();
                active = fragment2;
                return true;
            case R.id.navigation_orders:
                if (isUserLogged()) {
                    showToolbar();
                    toolbar.setTitle(R.string.title_myorders);
                    hideOtherViews();
                    pos2.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;
                } else {
                    DialogFactory.showAlertDialog(MainActivity.this, getString(R.string.signin_confrimation));
                    return false;
                }

            case R.id.navigation_specialorder:
                if (isUserLogged()) {
                    showToolbar();
                    toolbar.setTitle(R.string.title_specialorder);
                    hideOtherViews();
                    pos3.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().hide(active).show(fragment4).commit();
                    active = fragment4;
                    return true;
                } else {
                    DialogFactory.showAlertDialog(MainActivity.this, getString(R.string.signin_confrimation));
                    return false;
                }
            case R.id.navigation_settings:
                hideToolbar();
                hideOtherViews();
                pos4.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().hide(active).show(fragment5).commit();
                active = fragment5;
                return true;
        }
        return false;
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }

    public void hideToolbar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

    }


    public void showToolbar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().show();

    }

    @Override
    public void onBackPressed() {

    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goToSpecificFragment(int pos) {
        Timber.d("goToSpecificFragment");
        hideOtherViews();
        pos1.setVisibility(View.VISIBLE);
        MainActivity.this.setTitle(R.string.categories_label);
        getSupportFragmentManager().beginTransaction().hide(active).show(fragment2).commit();
        active = fragment2;

        Menu menu = navView.getMenu();
        navView.setSelectedItemId(R.id.navigation_categories);
        //this.onNavigationItemSelected(menu.findItem(R.id.navigation_categories));
    }

    @SuppressLint("ResourceType")
    public void goToCategoriesFragment(int i, MainCategory category) {
        navView.setSelectedItemId(R.id.navigation_categories);
        hideOtherViews();
        pos1.setVisibility(View.VISIBLE);
        MainActivity.this.setTitle(R.string.categories_label);
        postion = i;
        //fragment2 = ProductListFragment.newInstance(postion);
        getSupportFragmentManager().beginTransaction().hide(active).show(fragment2).commit();
        EventBus.getDefault().postSticky(new UpdateCategories(i));

        active = fragment2;
    }


}
