package com.selsela.example.ui.productlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.data.model.filter.Size;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SizeRecyclerViewAdapter extends RecyclerView.Adapter<SizeRecyclerViewAdapter.ViewHolder> {

    @BindView(R.id.size_layout)
    LinearLayout sizeLayout;
    private Context context;
    private List<Size> sizes;
    UpdateDataClickListener updateDataClickListener;


    public SizeRecyclerViewAdapter(List<Size> sizes, Context context, UpdateDataClickListener updateDataClickListener) {
        this.sizes = sizes;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sizelist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Size size = sizes.get(position);
        if (size==null)
            return;
        holder.size.setText(size.getName());
        holder.size.setOnClickListener(new View.OnClickListener() {
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
        return sizes.size();
    }

    @OnClick({R.id.size, R.id.size_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.size:
                break;
            case R.id.size_layout:
                break;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.size)
        TextView size;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;

        }


    }

    public interface UpdateDataClickListener {
        void oncolorSelected(Size size, int position);


    }
}
