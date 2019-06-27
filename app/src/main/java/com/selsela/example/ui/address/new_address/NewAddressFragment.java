package com.selsela.example.ui.address.new_address;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.selsela.example.R;
import com.selsela.example.data.local.DataHolder;
import com.selsela.example.data.model.address.Address;
import com.selsela.example.data.model.address.Area;
import com.selsela.example.data.model.address.Gov;
import com.selsela.example.data.model.order.OrderBody;
import com.selsela.example.ui.address.AddressMvpView;
import com.selsela.example.ui.address.AddressPresenter;
import com.selsela.example.ui.base.BaseFragment;
import com.selsela.example.util.SpinnerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;


public class NewAddressFragment extends BaseFragment implements AddressMvpView {

    @BindView(R.id.gov_spinner)
    MaterialSpinner govSpinner;
    @BindView(R.id.area_spinner)
    MaterialSpinner areaSpinner;
    @BindView(R.id.block_input)
    TextInputEditText blockInput;
    @BindView(R.id.streetnum_InputEditText)
    TextInputEditText streetInput;
    @BindView(R.id.jada_num_InputEditText)
    TextInputEditText substreetInput;
    Unbinder unbinder;
    int areaId, govId;
    @Inject
    AddressPresenter addressPresenter;
    @BindView(R.id.block)
    TextView block;

    private List<Gov> govList;
    private List<Area> areaList;
    private SpinnerAdapter areaSpinnerAdapter;
    private SpinnerAdapter govSpinnerAdapter;
    private Area area;
    private OrderBody orderBody;
    private Gov gov;

    // TODO: Rename and change types and number of parameters
    public static NewAddressFragment newInstance() {
        NewAddressFragment fragment = new NewAddressFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.new_address_fragment, container, false);
        getActivityComponent().inject(this);
        addressPresenter.attachView(this);
        addressPresenter.getGov();
        unbinder = ButterKnife.bind(this, view);
        pullToRefresh.setColorSchemeResources(R.color.blue, R.color.accent);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addressPresenter.getGov();
            }
        });

        orderBody = DataHolder.getOrder();


        if (preferencesHelper.getCountry().getPrefix().equals(965)) {
            blockInput.setHint(getString(R.string.block_input));
            block.setHint(getString(R.string.peicenum_label));
        }
        return view;

    }

    private void initSpinner() {

        if (govSpinnerAdapter != null) {
            govSpinnerAdapter.setData((List<Object>) (List<?>) govList);
            govSpinnerAdapter.notifyDataSetChanged();
        } else {
            govSpinnerAdapter = new SpinnerAdapter(getContext(), (List<Object>) (List<?>) govList);
            govSpinnerAdapter.setObjectType(Gov.class);
            govSpinnerAdapter.setIsArabic(isArabic());
            govSpinner.setAdapter(govSpinnerAdapter);
        }

        govSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<Gov>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Gov obj) {
                govSpinner.setText(obj.getName());
                govId = obj.getId();
                gov = obj;
                orderBody.setGov_id(govId);
                showAreas(gov.getAreas());
            }
        });

        areaSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<Area>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Area obj) {
                areaSpinner.setText(obj.getName());
                area = obj;
                areaId = obj.getId();
                orderBody.setArea_id(areaId);
                orderBody.setTransport_cost(area.getTransportCost());

            }
        });
    }

    public OrderBody getNewAddress() {

        if (govId < 0) {
            Timber.d("govPosition < 0");
            ((TextView) govSpinner.getRootView()).setError(getContext().getString(R.string.empty_label));
            return null;
        }

        if (areaId < 0) {
            Timber.d("region < 0");
            ((TextView) areaSpinner.getRootView()).setError(getContext().getString(R.string.empty_label));

            return null;
        }
        orderBody.setGov_id(govId);
        orderBody.setArea_id(areaId);

        orderBody.setBlock(blockInput.getEditableText().toString());

        orderBody.setStreet(streetInput.getEditableText().toString());
        orderBody.setSub_street(substreetInput.getEditableText().toString());
        if (blockInput.getEditableText().length() < 1) {
            blockInput.setError(getContext().getString(R.string.empty_label));
            return null;

        }
        if (streetInput.getEditableText().length() < 1) {
            streetInput.setError(getContext().getString(R.string.empty_label));
            return null;

        }
        String govName = gov.getName();
        String areaName = area.getName();
        String blockName = this.getString(R.string.peicenum_label) + " " + blockInput.getEditableText().toString();
        String text_1 = govName + ", " + areaName + ", " + blockName;
        String text_2 = text_1 + ", " + this.getString(R.string.streetnum_label) + " " + streetInput.getEditableText().toString();
        if (substreetInput.getEditableText().length() > 0) {
            text_2 = text_2 + ", " + this.getString(R.string.jadnum_label) + " " + substreetInput.getEditableText().toString();
        }
        Timber.d("text_2 %s", text_2);
        orderBody.setFull_address(text_2);
        return orderBody;
    }

    @Override
    public void showGov(List<Gov> govList) {
        this.govList = govList;
        if (govList != null)
            initSpinner();
    }

    @Override
    public void showAddress(List<Address> addresses) {

    }

    public void showAreas(List<Area> areas) {
        areaList = areas;
        if (areaSpinnerAdapter != null && areaList != null && areaList.size() > 0) {
            areaSpinnerAdapter.setData((List<Object>) (List<?>) areaList);
            areaSpinnerAdapter.notifyDataSetChanged();
        } else {
            areaSpinnerAdapter = new SpinnerAdapter(getContext(), (List<Object>) (List<?>) areaList);
            areaSpinnerAdapter.setObjectType(Area.class);
            areaSpinner.setAdapter(areaSpinnerAdapter);
            areaSpinnerAdapter.setIsArabic(isArabic());
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
