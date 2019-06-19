package com.selsela.example.ui.orderdetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.selsela.example.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderDeatailsRecyclerViewAdapter extends RecyclerView.Adapter<OrderDeatailsRecyclerViewAdapter.ViewHolder> {


    CallBack callBack;
    Context context;
    @BindView(R.id.product_imageView)
    ImageView productImageView;
    @BindView(R.id.aboutproduct_label)
    TextView aboutproductLabel;
    @BindView(R.id.product_color)
    TextView productColor;
    @BindView(R.id.product_size)
    TextView productSize;
    @BindView(R.id.product_pice_label)
    TextView productPiceLabel;
    @BindView(R.id.currency_label)
    TextView currencyLabel;
    @BindView(R.id.availablesize_textView)
    TextView availablesizeTextView;
    @BindView(R.id.ordercount_label)
    TextView ordercountLabel;
    @BindView(R.id.evaluate_btt)
    TextView evaluateBtt;

    public OrderDeatailsRecyclerViewAdapter(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderdeatils_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return 5;
    }


    public interface CallBack {
        void onEmployeClick();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.product_imageView)
        ImageView productImageView;
        @BindView(R.id.aboutproduct_label)
        TextView aboutproductLabel;
        @BindView(R.id.product_color)
        TextView productColor;
        @BindView(R.id.product_size)
        TextView productSize;
        @BindView(R.id.product_pice_label)
        TextView productPiceLabel;
        @BindView(R.id.currency_label)
        TextView currencyLabel;
        @BindView(R.id.availablesize_textView)
        TextView availablesizeTextView;
        @BindView(R.id.ordercount_label)
        TextView ordercountLabel;
        @BindView(R.id.evaluate_btt)
        TextView evaluateBtt;

        public final ImageView mIdView;

        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
            mIdView = (ImageView) view.findViewById(R.id.product_imageView);

            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @OnClick(R.id.evaluate_btt)
        public void onViewClicked(View view) {
            callBack.onEmployeClick();
        }
    }

}
