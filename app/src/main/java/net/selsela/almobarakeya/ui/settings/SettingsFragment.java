package net.selsela.almobarakeya.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.ui.about.AboutActivity;
import net.selsela.almobarakeya.ui.auoth.LoginActivity;
import net.selsela.almobarakeya.ui.base.BaseFragment;
import net.selsela.almobarakeya.ui.contact.ContactActivity;
import net.selsela.almobarakeya.ui.countries.CountriesActivity;
import net.selsela.almobarakeya.ui.notifications.NotificationsActivity;
import net.selsela.almobarakeya.ui.shoppingbasket.ShoppingBasketActivity;
import net.selsela.almobarakeya.ui.updateprofile.UpdateProfileActivity;
import net.selsela.almobarakeya.util.Const;
import net.selsela.almobarakeya.util.DialogFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SettingsFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.ic_login)
    ImageView icLogin;
    @BindView(R.id.login_label)
    TextView loginLabel;
    @BindView(R.id.notification_textView)
    TextView notificationTextView;
    @BindView(R.id.notifications_num)
    TextView notificationsNum;
    @BindView(R.id.cart_textView)
    TextView cartTextView;
    @BindView(R.id.myaccount_textView)
    TextView myaccountTextView;
    @BindView(R.id.about_textView)
    TextView aboutTextView;
    @BindView(R.id.rules_textView)
    TextView rulesTextView;
    @BindView(R.id.policyusage_textView)
    TextView policyusageTextView;
    @BindView(R.id.policyrecovery_textView)
    TextView policyrecoveryTextView;
    @BindView(R.id.contactus_textView)
    TextView contactusTextView;
    Unbinder unbinder;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.countryandlanguage_textView)
    TextView countryandlanguageTextView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SettingsFragment() {
        // Required empty public constructor
    }


    public static SettingsFragment newInstance() {

        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (isUserLogged()) {
            username.setVisibility(View.VISIBLE);
            loginLabel.setText(getString(R.string.logout));
            username.setText(mUserSession.getCurrentUser().getName());
        }
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.notification_textView, R.id.cart_textView, R.id.myaccount_textView, R.id.countryandlanguage_textView, R.id.about_textView, R.id.rules_textView, R.id.policyusage_textView, R.id.policyrecovery_textView, R.id.contactus_textView, R.id.login_label})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.notification_textView:
                if (isUserLogged()) {
                    intent = new Intent(getContext(), NotificationsActivity.class);
                    getContext().startActivity(intent);
                } else
                    DialogFactory.showAlertDialog(getActivity(), getString(R.string.signin_confrimation));
                break;

            case R.id.cart_textView:
                intent = new Intent(getContext(), ShoppingBasketActivity.class);
                getContext().startActivity(intent);

                break;
            case R.id.myaccount_textView:
                if (isUserLogged()) {
                    intent = new Intent(getContext(), UpdateProfileActivity.class);
                    getContext().startActivity(intent);
                } else
                    DialogFactory.showAlertDialog(getActivity(), getString(R.string.signin_confrimation));
                break;
            case R.id.about_textView:
                intent = new Intent(getContext(), AboutActivity.class);
                intent.putExtra(Const.Type, Const.About);
                getContext().startActivity(intent);
                break;
            case R.id.rules_textView:
                intent = new Intent(getContext(), AboutActivity.class);
                intent.putExtra(Const.Type, Const.Rules);
                getContext().startActivity(intent);
                break;
            case R.id.policyusage_textView:
                intent = new Intent(getContext(), AboutActivity.class);
                intent.putExtra(Const.Type, Const.Usage);
                getContext().startActivity(intent);
                break;
            case R.id.policyrecovery_textView:
                Intent about_intent = new Intent(getContext(), AboutActivity.class);
                about_intent.putExtra(Const.Type, Const.Policy);
                getContext().startActivity(about_intent);
                break;
            case R.id.contactus_textView:
                intent = new Intent(getContext(), ContactActivity.class);
                getContext().startActivity(intent);
                break;

            case R.id.login_label:
                if (!isUserLogged()) {
                    intent = new Intent(getContext(), LoginActivity.class);
                    getContext().startActivity(intent);
                } else {
                    logout();
                    username.setVisibility(View.GONE);
                    loginLabel.setText(getString(R.string.login_label));
                }
                break;


            case R.id.countryandlanguage_textView:
                intent = new Intent(getContext(), CountriesActivity.class);
                intent.putExtra("update", "update");
                getContext().startActivity(intent);
                break;
        }
    }

}
