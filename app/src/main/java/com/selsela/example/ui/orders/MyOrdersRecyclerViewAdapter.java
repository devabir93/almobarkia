package com.selsela.example.ui.orders;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.data.model.order.Order;
import com.selsela.example.ui.orderdetails.OrderdeatailsActivity;
import com.selsela.example.util.Const;
import com.selsela.example.util.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrdersRecyclerViewAdapter extends RecyclerView.Adapter<MyOrdersRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Order> orders;
    UpdateDataClickListener updateDataClickListener;

    public MyOrdersRecyclerViewAdapter(List<Order> orders, Context context, UpdateDataClickListener updateDataClickListener) {
        this.orders = orders;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_orders_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Order order = orders.get(position);
        if (order == null)
            return;
        holder.ordersNumberLabel.setText(order.getOrderNumber() + "");
        holder.ordershourLabel.setText(order.getStatusId() + "");
        holder.statusType.setText(order.getStatus().getName());
        holder.statusType.setTextColor(ViewUtil.getHexColor(order.getStatus().getColorHex()));
        holder.ordershourLabel.setText(order.getCreatedAtText());
        if (order.getProducts() != null)
            holder.productSumtexView.setText(order.getProducts().size() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderdeatailsActivity.class);
                intent.putExtra(Const.Details, order);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.status_label)
        TextView statusLabel;
        @BindView(R.id.constlayout)
        ConstraintLayout constlayout;
        @BindView(R.id.ordershour_label)
        TextView ordershourLabel;
        @BindView(R.id.orders_number_label)
        TextView ordersNumberLabel;
        @BindView(R.id.products_total_label)
        TextView productsTotalLabel;
        @BindView(R.id.product_sumtexView)
        TextView productSumtexView;
        @BindView(R.id.status_type)
        TextView statusType;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }


    }

    public interface UpdateDataClickListener {
        void onorderSelected(Order order, int position);


    }
}
