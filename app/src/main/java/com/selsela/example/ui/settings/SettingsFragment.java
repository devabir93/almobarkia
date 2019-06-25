package com.selsela.example.ui.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.ui.about.AboutActivity;
import com.selsela.example.ui.auoth.LoginActivity;
import com.selsela.example.ui.base.BaseFragment;
import com.selsela.example.ui.contact.ContactActivity;
import com.selsela.example.ui.notifications.NotificationsActivity;
import com.selsela.example.ui.privacypolicy.PrivacyPolicyActivity;
import com.selsela.example.ui.shoppingbasket.ShoppingBasketActivity;
import com.selsela.example.ui.updateprofile.UpdateProfileActivity;
import com.selsela.example.util.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
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

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.notification_textView, R.id.cart_textView, R.id.myaccount_textView, R.id.about_textView, R.id.rules_textView, R.id.policyusage_textView, R.id.policyrecovery_textView, R.id.contactus_textView, R.id.login_label})
    public void onViewClicked(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.notification_textView:
                intent = new Intent(getContext(), NotificationsActivity.class);
                getContext().startActivity(intent);
                break;

            case R.id.cart_textView:
                 intent = new Intent(getContext(), ShoppingBasketActivity.class);
                getContext().startActivity(intent);

                break;
            case R.id.myaccount_textView:
                intent = new Intent(getContext(), UpdateProfileActivity.class);
                getContext().startActivity(intent);
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
                intent = new Intent(getContext(), LoginActivity.class);
                getContext().startActivity(intent);
                break;

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
