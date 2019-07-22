package net.selsela.almobarakeya.ui.productdeatials;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Image;
import net.selsela.almobarakeya.util.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 1/17/2019.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.RecyclerViewHolders> {

    Callback callback;

    private List<Image> sliderList;
    private Context context;

    public GalleryAdapter(Context context, List<Image> list, Callback callback) {
        this.sliderList = list;
        this.context = context;
        this.callback = callback;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_gallery_list, parent, false);
        return new RecyclerViewHolders(layoutView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {

        final Image image = sliderList.get(position);
        if (image == null)
            return;
        Glide.with(context).load(sliderList.get(position).getImageUrl()).thumbnail(.7f).into(holder.productImageView);
        if (sliderList.get(position).getColor() != null && sliderList.get(position).getColor().size() > 0) {
            holder.productImageView.setBackgroundColor(ViewUtil.getHexColor(sliderList.get(position).getColor().get(0).getColorHexa()));
        }
        holder.productImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callback.onImageClick(image);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sliderList != null ? sliderList.size() : 0;

    }

    public interface Callback {
        void onImageClick(Image image);
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        @BindView(R.id.product_imageView)
        ImageView productImageView;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.product_imageView)
        public void onViewClicked() {
        }

    }

}


