package net.selsela.almobarakeya.ui.address;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.address.Address;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class OldAddressAdapter extends RecyclerView.Adapter<OldAddressAdapter.SimpleViewHolder> {


    public Context mcontext;
    List<Address> addresses;

    boolean isArabic;

    private String type;
    private int selectedItem;
    private ViewHolderCallback mViewHolderCallback;

    //  @Inject
    public OldAddressAdapter() {
        addresses = new ArrayList<>();
    }

    public void setData(Context context, List<Address> addresses, ViewHolderCallback viewHolderCallback) {
        this.addresses = addresses;
        this.mcontext = context;
        this.mViewHolderCallback = viewHolderCallback;
    }

    public void setIsArabic(boolean isArabic) {
        this.isArabic = isArabic;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addresslist, parent, false);
        return new SimpleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, final int position) {
        final Address address = addresses.get(position);
        Timber.d("pressed %s", address);
        holder.addressTextview.setText(address.getFullAddress());
        if (selectedItem == (holder.getAdapterPosition())) {
            holder.addressLayout.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.colorprimary));
        } else {
            holder.addressLayout.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.grey));
        }
        holder.deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewHolderCallback.onAddressDeleted(address, position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return addresses != null ? addresses.size() : 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void selected(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        notifyDataSetChanged();
    }

    public interface ViewHolderCallback {

        void onAddressSelected(Address address, int adapterPosition);

        void onAddressDeleted(Address address, int adapterPosition);
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.delete_layout)
        FrameLayout deleteLayout;
        @BindView(R.id.address_textview)
        TextView addressTextview;
        @BindView(R.id.address_layout)
        ConstraintLayout addressLayout;
        @BindView(R.id.swipe_layout)
        SwipeLayout swipeLayout;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (selectedItem != getAdapterPosition()) {
                notifyItemChanged(selectedItem);
                addressLayout.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.colorprimary));
                mViewHolderCallback.onAddressSelected(addresses.get(getAdapterPosition()), getAdapterPosition());
            }
        }
    }
}
