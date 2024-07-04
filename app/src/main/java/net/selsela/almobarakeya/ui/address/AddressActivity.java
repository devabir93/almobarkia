package net.selsela.almobarakeya.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.local.DataHolder;
import net.selsela.almobarakeya.data.model.address.Address;
import net.selsela.almobarakeya.data.model.order.OrderBody;
import net.selsela.almobarakeya.data.model.user.UserData;
import net.selsela.almobarakeya.ui.address.new_address.NewAddressFragment;
import net.selsela.almobarakeya.ui.address.new_address.NewAddressMoreFragment;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.payment.PaymentActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class AddressActivity extends BaseActivity {
    private static final int NEW_ADDRESS = 2;
    private static final int OLD_ADDRESS = 1;

    @BindView(R.id.address_list)
    RecyclerView addressList;
    @BindView(R.id.address_label)
    TextView addressLabel;
    @BindView(R.id.list_label)
    TextView listLabel;
    private OldAddressFragment oldAddressFragment;
    private NewAddressFragment addNewFrgamnet;
    private NewAddressMoreFragment addMoreAddressFragment;
    private List<Address> addresses;
    private OrderBody orderBody;
    private int flag = NEW_ADDRESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        activityTitle = getString(R.string.aladdress_label);
        initToolbar();
        orderBody = DataHolder.getOrder();
        UserData user = mUserSession.getCurrentUser();
        if (user != null) {
            orderBody.setName(user.getName());
            orderBody.setMobile(String.valueOf(user.getMobile()));
            orderBody.setUserId(String.valueOf(user.getId()));
            orderBody.setToken(user.getToken());
            orderBody.setCountryId(preferencesHelper.getCountry().getId());
        }
        onAddresss();
    }


    @OnClick({R.id.back_btt_textView, R.id.address_label, R.id.list_label, R.id.cont_textView})
    public void onViewClicked2(View view) {
        switch (view.getId()) {
            case R.id.back_btt_textView:
                onBackPressed();
                break;
            case R.id.address_label:
                onAddresss();
                break;
            case R.id.list_label:
                onList();
                break;
            case R.id.cont_textView:
                onNextClick();
                break;

        }
    }

    private void onAddresss() {
        flag = NEW_ADDRESS;
        showNewAddress();
        addressLabel.setTextColor(getResources().getColor(R.color.white));
        addressLabel.setBackgroundColor(getResources().getColor(R.color.colorprimary));

        listLabel.setTextColor(getResources().getColor(R.color.colorprimary));
        listLabel.setBackgroundColor(getResources().getColor(R.color.white));

    }

    private void onList() {
        flag = OLD_ADDRESS;
        if (isUserLogged())
            showOldAddress();

        addressLabel.setTextColor(getResources().getColor(R.color.colorprimary));
        addressLabel.setBackgroundColor(getResources().getColor(R.color.white));
        listLabel.setTextColor(getResources().getColor(R.color.white));
        listLabel.setBackgroundColor(getResources().getColor(R.color.colorprimary));

    }


    private void showOldAddress() {
        oldAddressFragment = OldAddressFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, oldAddressFragment, OldAddressFragment.class.getSimpleName())
                .commitNow();
    }

    private void showNewAddress() {
        addNewFrgamnet = NewAddressFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, addNewFrgamnet, NewAddressFragment.class.getSimpleName())
                .commitNow();
    }

    public void onNextClick() {

        if (getCurrentFragment() != null && !getCurrentFragment().isEmpty() && getCurrentFragment().equals(NewAddressFragment.class.getSimpleName())) {

            orderBody = addNewFrgamnet.getNewAddress();
            if (orderBody != null) {
                Timber.d("orderBody : %s ", orderBody.toString());
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                addMoreAddressFragment = NewAddressMoreFragment.newInstance();
                fragmentTransaction.replace(R.id.container, addMoreAddressFragment, NewAddressMoreFragment.class.getSimpleName());
                fragmentTransaction.addToBackStack(NewAddressMoreFragment.class.getSimpleName());
                fragmentTransaction.commit();
            }
        } else {

            if (addMoreAddressFragment != null) {
                orderBody = addMoreAddressFragment.getMoreNewAddress();
            }
            if (orderBody != null) {

                DataHolder.setOrder(orderBody);
                Timber.d("orderBody : %s ", orderBody.toString());
                startActivity(new Intent(this, PaymentActivity.class)
                );

            }
        }
    }

    public String getCurrentFragment() {
        return isFragmentPresent() ? Objects.requireNonNull(AddressActivity.this.getSupportFragmentManager().findFragmentById(R.id.container)).getClass().getSimpleName() : "";
    }

    public boolean isFragmentPresent() {
        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.container);

        if (frag instanceof NewAddressFragment) {
            if (frag.isAdded() && frag.isVisible() && frag.getUserVisibleHint()) {
                return true;
            }
        }
        return false;
    }

}
