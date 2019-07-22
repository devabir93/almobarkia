package net.selsela.almobarakeya.ui.categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.MainCategory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainCategoryMenuAdapter extends RecyclerView.Adapter<MainCategoryMenuAdapter.CategoryViewHolder> {


    private List<MainCategory> mCategories;
    Context context;
    private static UpdateDataClickListener sClickListener;
    private int sPosition;
    private int selectedItem;
    boolean isSelected = false;

    public MainCategoryMenuAdapter(Context context, List<MainCategory> categories, UpdateDataClickListener clickListener) {
        mCategories = categories;
        this.context = context;
        sClickListener = clickListener;
        selectedItem = 0;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tab_layout, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        MainCategory mainCategory = mCategories.get(position);

        if (mainCategory == null)
            return;
        Timber.d("onBindViewHolder %s", mainCategory.getName());

        holder.tabTitle.setText(mCategories.get(position).getName());
        if (selectedItem == holder.getAdapterPosition()
        ) {
            holder.tabTitle.setSelected(true);
            holder.selectedLine.setVisibility(View.VISIBLE);
            // holder.tabTitle.setBackgroundColor(context.getResources().getColor(R.color.brown));
        } else if (selectedItem != holder.getAdapterPosition()) {
            holder.tabTitle.setSelected(false);
            holder.selectedLine.setVisibility(View.GONE);
            //holder.tabTitle.setBackgroundColor(context.getResources().getColor(R.color.app_menu_grey));

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
    }

    public void setData(List<MainCategory> subCategories) {
        this.mCategories = subCategories;
        selectedItem = 0;
        notifyDataSetChanged();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tab_title)
        TextView tabTitle;
        @BindView(R.id.selected_line)
        View selectedLine;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (selectedItem != getAdapterPosition()) {
                notifyItemChanged(selectedItem);

                selectedLine.setVisibility(View.VISIBLE);
                selectedItem = getAdapterPosition();
                //tabTitle.setBackgroundColor(ContextCompat.getColor(context, R.color.brown));
                tabTitle.setSelected(true);
                sClickListener.onMainCategorySelect(getAdapterPosition(), mCategories.get(getAdapterPosition()).getSubCategories());
            }
        }
    }

    interface UpdateDataClickListener {
        void onMainCategorySelect(int position, List<MainCategory> subCategories);
    }
}
