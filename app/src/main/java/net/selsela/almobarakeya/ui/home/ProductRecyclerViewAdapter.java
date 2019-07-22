package net.selsela.almobarakeya.ui.home;

import android.content.Context;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.remote.SelselaService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {
    int type;
    String currency;

    private Context context;
    private List<Product> products;
    UpdateDataClickListener updateDataClickListener;
    private boolean isSet;


    public ProductRecyclerViewAdapter(List<Product> products, Context context, UpdateDataClickListener updateDataClickListener) {
        this.products = products;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;

    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();

    }

    public void setLayout(int type) {
        Timber.d("type %s", type);
        this.type = type;
    }

    public void setLayoutWidth(boolean isSet) {
        this.isSet = isSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producgridt, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Product product = products.get(position);
        if (product == null)
            return;
        if (isSet) {
            holder.constraintLayout.getLayoutParams().width = (int) context.getResources().getDimension(R.dimen._130sdp);
            holder.constraintLayout.requestLayout();
        }
        if (product.getImage() != null)
            Glide.with(context).load(SelselaService.IMAGE_URL + product.getImage()).into(holder.productImageview);
        if (product.getDiscountRatio() > 0) {
            holder.realPrice.setVisibility(View.VISIBLE);
            holder.realPrice.setPaintFlags(holder.realPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.realPrice.setText(product.getRealPrice() + "");
        } else if (product.getDiscountRatio() == 0) {
            holder.realPrice.setVisibility(View.GONE);

        }
        if (product.getDiscountRatio() > 0) {
            holder.discountLabel.setVisibility(View.VISIBLE);
            holder.discountLabel.setText(product.getDiscountRatio() + "" + "%");
        } else if (product.getDiscountRatio() == 0) {
            holder.discountLabel.setVisibility(View.GONE);

        }
        holder.rateLabel.setText(product.getRate());
        holder.productDescription.setText(product.getName());

        holder.productPrice.setText(product.getPrice() + "" + currency);
        if (product.getRate() != null)
            holder.ratingBar.setRating(Float.parseFloat(product.getRate()));

        if (product.getInFavorite() != null) {
            if (product.getInFavorite() == 1) {
                holder.like.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_likedheart));
            } else if (product.getInFavorite() == 0) {
                holder.like.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_like));
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataClickListener.onproductSelected(product, position);
            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataClickListener.onFavProduct(product, position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

//    public void removeAt(int position) {
//        if (position < products.size()) {
//            products.remove(position);
//            notifyItemRemoved(position);
//            notifyItemRangeChanged(position, products.size());
//        }
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.like)
        ImageView like;
        @BindView(R.id.productImageview)
        ImageView productImageview;
        @BindView(R.id.discount_label)
        TextView discountLabel;
        @BindView(R.id.rating_label)
        TextView rateLabel;
        @BindView(R.id.product_descrption)
        TextView productDescription;
        @BindView(R.id.productprice_label)
        TextView productPrice;
        @BindView(R.id.rating_bar)
        RatingBar ratingBar;
        @BindView(R.id.prprice_label)
        TextView realPrice;
        @BindView(R.id.layout)
        ConstraintLayout constraintLayout;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }

    }

    public interface UpdateDataClickListener {
        void onproductSelected(Product product, int position);

        void onFavProduct(Product product, int pos);


    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
