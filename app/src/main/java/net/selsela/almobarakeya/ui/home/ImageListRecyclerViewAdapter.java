package net.selsela.almobarakeya.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.remote.SelselaService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageListRecyclerViewAdapter extends RecyclerView.Adapter<ImageListRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Product> products;
    UpdateDataClickListener updateDataClickListener;
    private static final int TYPE_FULL = 0;
    private static final int TYPE_HALF = 1;
    private static final int TYPE_QUARTER = 2;

    public ImageListRecyclerViewAdapter(List<Product> products, Context context, UpdateDataClickListener updateDataClickListener) {
        this.products = products;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list, parent, false);
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                final int type = viewType;
                final ViewGroup.LayoutParams lp = view.getLayoutParams();
                if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams sglp =
                            (StaggeredGridLayoutManager.LayoutParams) lp;
                    switch (type) {
                        case TYPE_FULL:
                            sglp.setFullSpan(true);
                            break;
                        case TYPE_HALF:
                            sglp.setFullSpan(false);
                            sglp.width = view.getWidth() / 2;
                            break;
                        case TYPE_QUARTER:
                            sglp.setFullSpan(false);
                            sglp.width = view.getWidth() / 2;
                            sglp.height = view.getHeight() / 2;
                            break;
                    }
                    view.setLayoutParams(sglp);
                    final StaggeredGridLayoutManager lm =
                            (StaggeredGridLayoutManager) ((RecyclerView) parent).getLayoutManager();
                    lm.invalidateSpanAssignments();
                }
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
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
    public int getItemViewType(int position) {
        final int modeEight = position % 4;
        switch (modeEight) {
            case 0:
                return TYPE_HALF;
            case 1:
            case 2:
                return TYPE_QUARTER;
        }
        return TYPE_FULL;
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
