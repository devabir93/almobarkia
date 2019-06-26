package com.selsela.example.ui.productlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.data.model.filter.Color;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ColorRecyclerViewAdapter extends RecyclerView.Adapter<ColorRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<Color> colors;
    UpdateDataClickListener updateDataClickListener;

    public ColorRecyclerViewAdapter(List<Color> colors, Context context, UpdateDataClickListener updateDataClickListener) {
        this.colors = colors;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.colorslist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Color color = colors.get(position);
        if (color==null)
            return;
       // holder.color.setText(color.getColorHexa());
        holder.color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return colors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.color)
        TextView color;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;

        }


    }

    public interface UpdateDataClickListener {
        void oncolorSelected(Color color, int position);


    }

}
