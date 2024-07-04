package net.selsela.almobarakeya.ui.productlist;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Color;
import net.selsela.almobarakeya.util.SomeDrawable;
import net.selsela.almobarakeya.util.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ColorRecyclerViewAdapter extends RecyclerView.Adapter<ColorRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<Color> colors;
    UpdateDataClickListener updateDataClickListener;
    private int selectedItem;
    private int sPosition;
    private boolean isSelected;
    private SparseBooleanArray sSelectedItems;
    private boolean clickable = true;

    public ColorRecyclerViewAdapter(List<Color> colors, Context context, UpdateDataClickListener updateDataClickListener) {
        this.colors = colors;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
        sSelectedItems = new SparseBooleanArray();
        sPosition = 0;
        selectedItem = 0;
        isSelected = false;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.colorslist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Color color = colors.get(position);
        if (color == null)
            return;

        holder.itemView.setClickable(clickable);
        holder.itemView.setEnabled(clickable);
        if (color.getColorHexa() != null) {
            SomeDrawable someDrawable = new SomeDrawable(ViewUtil.getHexColor(color.getColorHexa()), GradientDrawable.OVAL);
            holder.color.setBackground(someDrawable);
            holder.selectedColor.setBackground(someDrawable);
        }

        if (selectedItem == holder.getAdapterPosition()) {
            holder.selectedColor.setSelected(true);
            holder.selectedColor.setVisibility(View.VISIBLE);
            holder.color.setVisibility(View.INVISIBLE);
        } else if (selectedItem != holder.getAdapterPosition()) {
            holder.selectedColor.setSelected(false);
            holder.color.setVisibility(View.VISIBLE);
            holder.selectedColor.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != holder.getAdapterPosition()) {
                    ///unselected
                    notifyItemChanged(selectedItem);
                    holder.selectedColor.setVisibility(View.INVISIBLE);
                    holder.color.setVisibility(View.VISIBLE);
                    holder.color.setSelected(true);
                    updateDataClickListener.oncolorSelected(color, position);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return colors.size();
    }

    public void setClickable(boolean b) {
        this.clickable = b;
        if (!b)
            selectedItem = -1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.color)
        TextView color;

        @BindView(R.id.selected_color)
        ImageView selectedColor;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;

        }


    }


    public void selected(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }

    public interface UpdateDataClickListener {
        void oncolorSelected(Color color, int position);


    }

}
