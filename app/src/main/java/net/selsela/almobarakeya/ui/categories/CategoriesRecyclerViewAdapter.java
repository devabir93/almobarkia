package net.selsela.almobarakeya.ui.categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.MainCategory;
import net.selsela.almobarakeya.data.remote.SelselaService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.ViewHolder> {
    int type;
    private Context context;
    private List<MainCategory> categories;
    UpdateDataClickListener updateDataClickListener;

    public CategoriesRecyclerViewAdapter(List<MainCategory> categories, Context context, UpdateDataClickListener updateDataClickListener) {
        this.categories = categories;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
    }

    public void setLayout(int type) {
        Timber.d("type %s", type);
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorieslist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MainCategory category = categories.get(position);
        if (category == null)
            return;

//        if (position == 0)
//            updateDataClickListener.onCategorySelected(category, position);

        if (category.getImage() != null)
            Glide.with(context).load(SelselaService.IMAGE_URL + category.getImage()).into(holder.categoryImageView);
        holder.categoryype.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataClickListener.onCategorySelected(category, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.categoriestype_textView)
        TextView categoryype;
        @BindView(R.id.categories_cell)
        ImageView categoryImageView;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

    }


    public interface UpdateDataClickListener {
        void onCategorySelected(MainCategory category, int position);


    }
}
