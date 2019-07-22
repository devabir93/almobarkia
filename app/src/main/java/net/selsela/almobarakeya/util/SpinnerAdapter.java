package net.selsela.almobarakeya.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.address.Address;
import net.selsela.almobarakeya.data.model.address.Area;
import net.selsela.almobarakeya.data.model.address.Gov;

import java.util.List;

import timber.log.Timber;

public class SpinnerAdapter extends MaterialSpinnerAdapter<Object> {

    LayoutInflater flater;
    Object mObject;
    private List<Object> mItems;
    private Context mContext;
    private boolean isArabic;

    public SpinnerAdapter(Context context, List<Object> objects) {
        super(context, objects);
        Timber.d("objects %s", objects);
        mItems = objects;
        mContext = context;
    }

    public void setData(List<Object> objects) {
        mItems = objects;
    }

    public void setObjectType(Object aClass) {
        this.mObject = aClass;

    }

    public void setIsArabic(boolean arabic) {
        isArabic = arabic;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView = new TextView(mContext);
        if (mItems.get(position) instanceof Address) {
            Timber.d("Address");
            Address addressText = (Address) mItems.get(position);
            textView.setText(addressText.getFullAddress());
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else if (mItems.get(position) instanceof Gov) {
            Timber.d("Address");
            Gov addressText = (Gov) mItems.get(position);
            textView.setText(addressText.getName());
        } else if (mItems.get(position) instanceof Area) {
            Timber.d("Address");
            Area addressText = (Area) mItems.get(position);
            textView.setText(addressText.getName());
        }


        textView.setPadding(16, 16, 16, 16);
        return textView;
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public Object get(int position) {
        return mItems.get(position);
    }

    @Override
    public List<Object> getItems() {
        return mItems;
    }

}

