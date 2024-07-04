package net.selsela.almobarakeya.ui.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.local.DataHolder;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.address.Address;
import net.selsela.almobarakeya.data.model.address.Gov;
import net.selsela.almobarakeya.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class OldAddressFragment extends BaseFragment implements OldAddressAdapter.ViewHolderCallback, AddressMvpView {

    public Address adress;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.oldaddress)
    ConstraintLayout oldaddress;
    Unbinder unbinder;
    @BindView(R.id.empty_view)
    TextView emptyView;
    private OldAddressAdapter adapter;
    List<Address> addresses;

    @Inject
    AddressPresenter addressPresenter;

    public static OldAddressFragment newInstance() {
        OldAddressFragment oldAddressFragment = new OldAddressFragment();
        return oldAddressFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_old_address, container, false);
        unbinder = ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        addressPresenter.attachView(this);
        initRecyclerview();
        addressPresenter.getAddress();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // getGovs();
                addressPresenter.getAddress();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.d("onStart");
//        initRecyclerview();
    }

    private void initRecyclerview() {

        adapter = new OldAddressAdapter();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAddressSelected(Address address, int adapterPosition) {
        adapter.selected(adapterPosition);
        this.adress = address;
        Timber.d("onAddressSelected %s", address);
        DataHolder.getOrder().setTransport_cost(address.getArea().getTransportCost());
        DataHolder.getOrder().setOld_address(address.getId());
        DataHolder.getOrder().setFull_address(address.getFullAddress());
    }

    @Override
    public void onAddressDeleted(Address address, int adapterPosition) {

    }

    public Address getSelectedAddress() {
        return adress;
    }

    @Override
    public void showMessageDialog(BaseResponse response) {

    }

    @Override
    public void showGov(List<Gov> productOrderList) {

    }

    @Override
    public void showAddress(List<Address> addresses) {
        this.addresses = addresses;
        if (addresses != null && addresses.size() > 0) {

            adapter.setData(getContext(), addresses, this);
            adapter.notifyDataSetChanged();

        } else {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }
}
