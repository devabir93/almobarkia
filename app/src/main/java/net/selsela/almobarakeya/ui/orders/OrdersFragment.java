package net.selsela.almobarakeya.ui.orders;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.order.Order;
import net.selsela.almobarakeya.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OrdersFragment extends BaseFragment implements OrdresMvpView {

    private static final String ARG_COLUMN_COUNT = "column-count";
    @Inject
    OrdersPresenter ordersPresenter;
    @BindView(R.id.orders_list)
    RecyclerView ordersList;
    @BindView(R.id.empty_view)
    TextView emptyView;
    Unbinder unbinder;
    private boolean isEmpty;

    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;


    public OrdersFragment() {
    }

    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders_list, container, false);
        ButterKnife.bind(this, view);
        ordersPresenter.attachView(this);
        if (isUserLogged())
            ordersPresenter.get_orders();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ordersPresenter.get_orders();
            }
        });

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showOrders(List<Order> orders) {

        ordersList.setLayoutManager(new LinearLayoutManager(getContext()));
        ordersList.setAdapter(new MyOrdersRecyclerViewAdapter(orders, getContext(), new MyOrdersRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onorderSelected(Order order, int position) {
            }
        }));
    }


    public interface OnListFragmentInteractionListener {
    }

    @Override
    public void showEmpty() {
        stopRefreshing();
        isEmpty = true;
        ordersList.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }
}
