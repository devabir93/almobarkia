package net.selsela.almobarakeya.ui.orderdetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.order.ProductData;
import net.selsela.almobarakeya.data.remote.SelselaService;
import net.selsela.almobarakeya.ui.productdeatials.ProductDetailsActivity;
import net.selsela.almobarakeya.util.Const;
import net.selsela.almobarakeya.util.SomeDrawable;
import net.selsela.almobarakeya.util.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;


public class OrderDeatailsRecyclerViewAdapter extends RecyclerView.Adapter<OrderDeatailsRecyclerViewAdapter.ViewHolder> {
    private String currency;

    private Context context;
    private List<ProductData> products;

    private CallBack callBack;

    public OrderDeatailsRecyclerViewAdapter(List<ProductData> products, Context context, CallBack callBack) {
        Timber.d("products %s", products);
        this.context = context;
        this.callBack = callBack;
        this.products = products;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderdeatils_list, parent, false);
        Timber.d("onCreateViewHolder");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Timber.d("onBindViewHolder");
        final ProductData product = products.get(position);
        Timber.d("product %s", product);
        if (product == null)
            return;
        if (product.getProduct().getImage() != null)
            Glide.with(context).load(SelselaService.IMAGE_URL + product.getProduct().getImage()).into(holder.productImageView);
        holder.aboutproductLabel.setText(product.getProduct().getName());
        if (product.getColor() != null) {
            SomeDrawable someDrawable = new SomeDrawable(ViewUtil.getHexColor(product.getColor().getColorHexa()), GradientDrawable.OVAL);
            holder.productColor.setBackground(someDrawable);
        }
        if (product.getSize() != null)
            holder.productSize.setText(product.getSize().getName());
        holder.productPiceLabel.setText(product.getProductPrice() + "");
        holder.currencyLabel.setText(currency);
        holder.ordercountLabel.setText(product.getQuantity() + "");
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProductDetailsActivity.class).putExtra(Const.Details, product.getProduct()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return products.size();
    }


    public interface CallBack {
        void onEmployeClick(Product product);
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


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;

        }

        @OnClick(R.id.evaluate_btt)
        public void onViewClicked() {
            callBack.onEmployeClick(products.get(getAdapterPosition()).getProduct());
        }
    }


}
