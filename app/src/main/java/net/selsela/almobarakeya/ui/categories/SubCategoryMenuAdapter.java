package net.selsela.almobarakeya.ui.categories;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.MainCategory;
import net.selsela.almobarakeya.data.model.home.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class SubCategoryMenuAdapter extends RecyclerView.Adapter<SubCategoryMenuAdapter.CategoryViewHolder> {

    private List<MainCategory> mCategories;
    Context context;
    private static UpdateDataClickListener sClickListener;
    private int sPosition;
    private int selectedItem;
    boolean isSelected = false;

    public SubCategoryMenuAdapter(Context context, List<MainCategory> categories, UpdateDataClickListener clickListener) {
        mCategories = categories;
        this.context = context;
        sClickListener = clickListener;
        selectedItem = 0;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_category_item_list, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        MainCategory mainCategory = mCategories.get(position);

        if (mainCategory == null)
            return;
        Timber.d("onBindViewHolder %s", mainCategory.getName());

        holder.textView.setText(mCategories.get(position).getName());
        if (selectedItem == holder.getAdapterPosition()
        ) {
            holder.textView.setSelected(true);
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.brown));
        } else if (selectedItem != holder.getAdapterPosition()) {
            holder.textView.setSelected(false);
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.app_menu_grey));

        }
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void selected(int position) {
        //int previousItem = selectedItem;
        selectedItem = position;
        notifyDataSetChanged();
//        notifyItemChanged(previousItem);
//        notifyItemChanged(position);
    }

    public void setData(List<MainCategory> subCategories) {
        this.mCategories = subCategories;
        selectedItem = 0;
        notifyDataSetChanged();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.sub_category_name)
        TextView textView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (selectedItem != getAdapterPosition()) {
                notifyItemChanged(selectedItem);
                selectedItem = getAdapterPosition();
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.brown));
                textView.setSelected(true);
                sClickListener.onItemClick(getAdapterPosition(), mCategories.get(getAdapterPosition()).getProducts());
            }
        }
    }

    interface UpdateDataClickListener {
        void onItemClick(int position, List<Product> subCategories);
    }
}
