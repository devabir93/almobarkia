package net.selsela.almobarakeya.ui.countries;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.country.Country;
import net.selsela.almobarakeya.data.remote.SelselaService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ContriesRecyclerViewAdapter extends RecyclerView.Adapter<ContriesRecyclerViewAdapter.ViewHolder> {
    private  Context context;
    private List<Country> countries;
    UpdateDataClickListener updateDataClickListener;

    public ContriesRecyclerViewAdapter(List<Country> countries, Context context, UpdateDataClickListener updateDataClickListener) {
        this.countries = countries;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.countrylist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Country country = countries.get(position);
        if (country == null)
            return;
        Timber.d("onBindViewHolder %s", country);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                //  .transforms(new CenterCrop(), new RoundedCorners(1000))
                .error(R.mipmap.ic_launcher_round);

        if (country.getFlag() != null)
            Glide.with(context).load(SelselaService.IMAGE_URL + country.getFlag()).apply(options).into(holder.countryImageView);
        holder.countryName.setText(country.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataClickListener.onCountrySelected(country, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;

        @BindView(R.id.flag_country)
        ImageView countryImageView;
        @BindView(R.id.country_label)
        TextView countryName;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    interface UpdateDataClickListener {
        void onCountrySelected(Country country, int position);


    }

}
