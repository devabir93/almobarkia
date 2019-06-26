package com.selsela.example.ui.specialorder;

import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.data.model.order.Order;
import com.selsela.example.data.model.user.UserBody;
import com.selsela.example.ui.base.BaseFragment;
import com.selsela.example.ui.orders.OrdersPresenter;
import com.selsela.example.ui.orders.OrdresMvpView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SpecialOrderFragment extends BaseFragment implements OrdresMvpView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.add_imageView)
    ImageView addImageView;
    @BindView(R.id.Innerlayout)
    ConstraintLayout Innerlayout;
    @BindView(R.id.textInputEditText)
    TextInputEditText textInputEditText;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.user_data)
    TextView userData;
    @BindView(R.id.phone_number_editText)
    TextInputEditText phoneNumberEditText;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.phone_num)
    TextView phoneNum;
    @BindView(R.id.product_name_editText)
    TextInputEditText productNameEditText;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.topic)
    TextView topic;
    @BindView(R.id.productdetails_editText)
    TextInputEditText productdetailsEditText;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.details_label)
    TextView detailsLabel;
    @BindView(R.id.send_order)
    TextView sendOrder;
    Unbinder unbinder;
    private String mParam1;
    private String mParam2;

    @Inject
    OrdersPresenter ordersPresenter;
    private OnFragmentInteractionListener mListener;
    private UserBody userBody;

    public SpecialOrderFragment() {
        // Required empty public constructor
    }


    public static SpecialOrderFragment newInstance(String param1, String param2) {
        SpecialOrderFragment fragment = new SpecialOrderFragment();
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
        ordersPresenter.attachView(this);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_special_order, container, false);
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
    public void showOrders(List<Order> orders) {

    }

    @Override
    public void isSuccess(boolean isSuccess) {
        textInputEditText.setText("");
        phoneNumberEditText.setText("");
        productNameEditText.setText("");
        productdetailsEditText.setText("");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.send_order)
    public void onViewClicked() {

        if (hasInternetConnection()) {

            if (textInputEditText.getText().length() < 1) {
                textInputEditText.setError(this.getString(R.string.name_erro));
            }
            if (phoneNumberEditText.getText().length() < 1) {
                phoneNumberEditText.setError(this.getString(R.string.phone_empty_label));
            } else if (productNameEditText.getText().length() < 1) {
                productNameEditText.setError(this.getString(R.string.product_erro));
            } else if
               (productdetailsEditText.getText().length() < 1) {
            productdetailsEditText.setError(this.getString(R.string.productde_erro));
        } else {
            userBody = new UserBody();
            String user_name = textInputEditText.getText().toString();
            String phone_num = phoneNumberEditText.getText().toString();
            String product_name = productNameEditText.getText().toString();
            String product_details = productdetailsEditText.getText().toString();
            userBody.setName(user_name);
            userBody.setMobile(phone_num);
            userBody.setProductDetails(product_details);
            userBody.setProductName(product_name);
            userBody.setCountryId(preferencesHelper.getCountry().getId());
            ordersPresenter.specialOrder(getContext(), userBody);
        }

    } else

    hasActiveInternetConnection(false);
}


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}


