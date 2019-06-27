package com.selsela.example.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selsela.example.R;
import com.selsela.example.data.model.error.Error;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ErrorResponseAdapter extends RecyclerView.Adapter<ErrorResponseAdapter.ViewHolder> {
    List<Error> errorList;


    public ErrorResponseAdapter(List<Error> errorList) {
        this.errorList = errorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_error, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Error error = errorList.get(i);
        if (error == null)
            return;
        viewHolder.textView.setText("- " + error.getError());

    }

    @Override
    public int getItemCount() {
        return errorList != null ? errorList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view)
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
