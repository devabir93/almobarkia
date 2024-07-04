package net.selsela.almobarakeya.ui.productdeatials;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Color;
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

    private List<Color> sliderList;
    private Context context;
    private int selectedItem;

    public GalleryAdapter(Context context, List<Color> list, Callback callback) {
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

        final Color Color = sliderList.get(position);
        if (Color == null)
            return;

        if (selectedItem == holder.getAdapterPosition()) {
            //selected
            holder.selectedView.setVisibility(View.VISIBLE);
        } else if (selectedItem != holder.getAdapterPosition()) {
            ///unselected
            holder.selectedView.setVisibility(View.INVISIBLE);

        }
        if (Color.getImage() != null) {
            Glide.with(context).load(sliderList.get(position).getImage().getImageUrl()).thumbnail(.7f).into(holder.productImageView);

        } else
            holder.productImageView.setBackgroundColor(ViewUtil.getHexColor(sliderList.get(position).getColorHexa()));
        holder.productImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (selectedItem != holder.getAdapterPosition()) {
                    notifyItemChanged(selectedItem);
                    callback.onImageClick(Color,holder.getAdapterPosition());
                    holder.selectedView.setVisibility(View.VISIBLE);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return sliderList != null ? sliderList.size() : 0;

    }


    public void selected(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }

    public interface Callback {
        void onImageClick(Color Color, int adapterPosition);
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        @BindView(R.id.product_imageView)
        ImageView productImageView;
        @BindView(R.id.selected_view)
        View selectedView;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.product_imageView)
        public void onViewClicked() {
        }

    }

}


