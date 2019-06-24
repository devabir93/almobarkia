package com.selsela.example.ui.productdeatials;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
    boolean isSelected = false;


    public DialogSizeRecyclerViewAdapter(Context context, Callback callBack) {
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.size_gallery_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Size size = sizeList.get(position);
        if (size == null)
            return;


        if (isSelected) {
            holder.selectedSize.setVisibility(View.VISIBLE);
            holder.selectedSize.setText(size.getName());

            holder.unselectedSize.setVisibility(View.GONE);
        } else if (!isSelected) {
            holder.selectedSize.setVisibility(View.GONE);
            holder.selectedSize.setText(size.getName());

            holder.unselectedSize.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onSizeClick(size);
                if (!isSelected) {
                    holder.selectedSize.setVisibility(View.VISIBLE);
                    holder.selectedSize.setText(size.getName());

                    holder.unselectedSize.setVisibility(View.GONE);
                    isSelected = true;
                } else if (isSelected) {
                    holder.selectedSize.setVisibility(View.GONE);
                    holder.selectedSize.setText(size.getName());

                    holder.unselectedSize.setVisibility(View.VISIBLE);
                    isSelected = false;
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
        @BindView(R.id.unselected_size)
        TextView unselectedSize;
        @BindView(R.id.selected_size)
        TextView selectedSize;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    interface Callback {
        void onSizeClick(Size size);
    }
}
