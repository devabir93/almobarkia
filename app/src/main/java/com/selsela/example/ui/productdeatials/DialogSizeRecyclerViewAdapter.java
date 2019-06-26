package com.selsela.example.ui.productdeatials;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.data.model.home.Size;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DialogSizeRecyclerViewAdapter extends RecyclerView.Adapter<DialogSizeRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Size> sizeList;
    Callback callBack;
    private int selectedItem;
    private int sPosition;
    private boolean isSelected;
    private SparseBooleanArray sSelectedItems;

    public DialogSizeRecyclerViewAdapter(Context context, Callback callBack) {
        this.callBack = callBack;
        this.context = context;
        sSelectedItems = new SparseBooleanArray();
        sPosition = 0;
        selectedItem = 0;
        isSelected = false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.size_gallery_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Size size = sizeList.get(position);
        if (size == null)
            return;
        if (selectedItem == holder.getAdapterPosition() && !isSelected) {
            holder.selectedSize.setText(size.getName());
            holder.selectedSize.setBackground(ContextCompat.getDrawable(context, R.drawable.textview_rectanglesolidl));
        } else {
            if (sSelectedItems.get(holder.getAdapterPosition())) {
                holder.selectedSize.setText(size.getName());
                holder.selectedSize.setBackground(ContextCompat.getDrawable(context, R.drawable.textview_rectanglesolidl));
            } else {
                holder.selectedSize.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_with_stroke));
                holder.selectedSize.setText(size.getName());
            }
        }

        holder.selectedSize.setSelected(sSelectedItems.get(holder.getAdapterPosition(), false));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onSizeClick(size, position);
                isSelected = true;
                if (sSelectedItems.get(holder.getAdapterPosition(), false)) {
                    holder.selectedSize.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_with_stroke));
                    holder.selectedSize.setText(size.getName());
                    sSelectedItems.put(sPosition, false);
                    sSelectedItems.put(holder.getAdapterPosition(), true);
                    holder.selectedSize.setSelected(false);
                } else {
                    sSelectedItems.delete(holder.getAdapterPosition());
                    holder.selectedSize.setText(size.getName());
                    holder.selectedSize.setBackground(ContextCompat.getDrawable(context, R.drawable.textview_rectanglesolidl));
                    holder.selectedSize.setSelected(true);


                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return sizeList != null ? sizeList.size() : 0;
    }

    public void setData(List<Size> sizeList) {
        this.sizeList = sizeList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.selected_size)
        TextView selectedSize;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public void selected(int position) {
        int previousItem = selectedItem;
        sPosition = position;
        notifyDataSetChanged();
        notifyItemChanged(previousItem);
        notifyItemChanged(position);
    }

    interface Callback {
        void onSizeClick(Size size, int pos);
    }
}
