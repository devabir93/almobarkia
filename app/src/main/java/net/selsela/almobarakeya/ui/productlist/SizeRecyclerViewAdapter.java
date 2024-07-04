package net.selsela.almobarakeya.ui.productlist;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Size;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SizeRecyclerViewAdapter extends RecyclerView.Adapter<SizeRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Size> sizes;
    UpdateDataClickListener updateDataClickListener;
    private int selectedItem;
    private int sPosition;
    private boolean isSelected;
    private SparseBooleanArray sSelectedItems;
    private boolean clickable = true;

    public SizeRecyclerViewAdapter(List<Size> sizes, Context context, UpdateDataClickListener updateDataClickListener) {
        this.sizes = sizes;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
        sSelectedItems = new SparseBooleanArray();
        sPosition = 0;
        selectedItem = 0;
        isSelected = false;
    }

    public void setClickable(boolean b) {
        this.clickable = b;
        if (!b)
            selectedItem = -1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sizelist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Size size = sizes.get(position);
        if (size == null)
            return;

        holder.itemView.setClickable(clickable);
        holder.itemView.setEnabled(clickable);

        holder.size.setText(size.getName());
        if (selectedItem == holder.getAdapterPosition()) {
            //selected
            sSelectedItems.put(selectedItem, false);
            holder.size.setBackgroundColor(ContextCompat.getColor(context, R.color.colorprimary));
            holder.size.setTextColor(ContextCompat.getColor(context, R.color.white));
        } else {
            ///unselected
            holder.size.setBackground(ContextCompat.getDrawable(context, R.drawable.textview_rectanglel));
            holder.size.setTextColor(ContextCompat.getColor(context, R.color.colorprimary));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != holder.getAdapterPosition()) {
                    //selected
                    notifyItemChanged(selectedItem);
                    selectedItem = holder.getAdapterPosition();
                    holder.size.setBackgroundColor(ContextCompat.getColor(context, R.color.colorprimary));
                    holder.size.setTextColor(ContextCompat.getColor(context, R.color.white));

                    updateDataClickListener.onSizeSelected(size, position);

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return sizes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.size)
        TextView size;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }

    public void selected(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }

    public interface UpdateDataClickListener {
        void onSizeSelected(Size size, int position);


    }
}
