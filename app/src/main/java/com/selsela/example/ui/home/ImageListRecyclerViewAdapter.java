package com.selsela.example.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.selsela.example.R;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.remote.SelselaService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ImageListRecyclerViewAdapter extends RecyclerView.Adapter<ImageListRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Product> products;
    UpdateDataClickListener updateDataClickListener;

    public ImageListRecyclerViewAdapter(List<Product> products, Context context, UpdateDataClickListener updateDataClickListener) {
        this.products = products;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Product product = products.get(position);
        if (product == null)
            return;
        if (product.getImage() != null)
            Glide.with(context).load(SelselaService.IMAGE_URL + product.getImage()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataClickListener.onproductSelected(product, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            mView = view;

        }

    }

    public interface UpdateDataClickListener {
        void onproductSelected(Product product, int position);
    }
}
