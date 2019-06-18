package com.selsela.example.ui.orders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.ui.orderdetails.OrderdeatailsActivity;
import com.selsela.example.ui.orders.OrdersFragment.OnListFragmentInteractionListener;
import com.selsela.example.ui.orders.dummy.DummyContent.DummyItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyOrdersRecyclerViewAdapter extends RecyclerView.Adapter<MyOrdersRecyclerViewAdapter.ViewHolder> {



    public MyOrdersRecyclerViewAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_orders_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

            }



    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        @BindView(R.id.status_label)
        TextView statuslabel;
        // public final TextView mIdView;

        //public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
           // mIdView = (TextView) view.findViewById(R.id.status_label);
            //mContentView = (TextView) view.findViewById(R.id.content);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mView.getContext(), OrderdeatailsActivity.class);
            mView.getContext();
            mView.getContext().startActivity(intent);
        }
    }
}
