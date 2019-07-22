package net.selsela.almobarakeya.ui.shoppingbasket;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.send_order.ProductOrderBody;
import net.selsela.almobarakeya.util.AppUtils;
import net.selsela.almobarakeya.util.SomeDrawable;
import net.selsela.almobarakeya.util.Utils;
import net.selsela.almobarakeya.util.ViewUtil;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.LimitExceededListener;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {

    String newPrice;


    private List<ProductOrderBody> productList;
    private Context context;
    private String currency;
    private UpdateDataClickListener updateDataClickListener;
    private Boolean isExceed = false;
    private boolean isArabic;

    public CartRecyclerViewAdapter(Context context, UpdateDataClickListener updateDataClickListener) {
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
    }

    public void setList(List<ProductOrderBody> list) {
        this.productList = list;
        notifyDataSetChanged();
    }

    public void isExceed(Boolean aBoolean) {
        Timber.d("isExceed %s", aBoolean);
        this.isExceed = aBoolean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shoppingbasket_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ProductOrderBody productOrder = productList.get(position);
        final ProductOrderBody productOrderBodyFinal = productOrder;
        if (productOrder == null)
            return;
        Product product = productOrder.getProduct();
        holder.productName.setText(product.getName());
        holder.currency.setText(currency);
        holder.price.setText(AppUtils.calculateEquation(Utils.arabicToDecimal(String.valueOf(productOrder.getPriceForSingleItem()))
                + "*" + productOrder.getQuantity()));
        holder.numberPicker.setValue(productOrder.getQuantity());
        holder.numberPicker.setMax(product.getAmount());

        Glide.with(context).load(productOrder.getImageUrl()).thumbnail(.7f).into(holder.productImage);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateDataClickListener.onDeleteOrder(holder.getAdapterPosition());
            }

        });

        if (productOrder.getColor() != null) {
            SomeDrawable someDrawable = new SomeDrawable(ViewUtil.getHexColor(productOrder.getColor().getColorHexa()), GradientDrawable.OVAL);
            holder.productColor.setBackground(someDrawable);
        }

        if (productOrder.getSize() != null) {
            holder.productSize.setText(productOrder.getSize().getName());
        }

//        if (isExceed) {
//            holder.numberPicker.setActionEnabled(ActionEnum.INCREMENT, false);
//        } else if (!isExceed)
//            holder.numberPicker.setActionEnabled(ActionEnum.INCREMENT, true);
        //holder.quantitiy.setText("x" + productOrder.getQuantity());
        if (productOrder.getSize() != null && productOrder.getSize().getPivot() != null) {
            Integer amount = (productOrder.getSize().getPivot().getAmount());
            holder.numberPicker.setMax(amount);
        } else {
            if (product.getMaxOrder() > 0) {
                holder.numberPicker.setMax(product.getMaxOrder());

            }
        }

        holder.numberPicker.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                if (value > 0) {
                    if (action.equals(ActionEnum.INCREMENT))
                        newPrice = AppUtils.calculateEquation(Utils.arabicToDecimal(String.valueOf(productOrderBodyFinal.getPrice()))
                                + "+" + Utils.arabicToDecimal(String.valueOf(productOrderBodyFinal.getPriceForSingleItem())));
                    else if (action.equals(ActionEnum.DECREMENT)) {
                        newPrice = AppUtils.calculateEquation(Utils.arabicToDecimal(String.valueOf(productOrderBodyFinal.getPrice()))
                                + "-" + Utils.arabicToDecimal(String.valueOf(productOrderBodyFinal.getPriceForSingleItem())));
                    }
                    updateDataClickListener.onPickQuantity(holder.getAdapterPosition(), value, newPrice, action);
                }
            }
        });

        holder.numberPicker.setLimitExceededListener(new LimitExceededListener() {
            @Override
            public void limitExceeded(int limit, int exceededValue) {
                Timber.d("limit %s , exceededvalue %s pos %s", limit, exceededValue, holder.getAdapterPosition());
                if (exceededValue > limit) {
                    updateDataClickListener.showMessage(context.getString(R.string.exceed_amount_left));
                }
            }
        });
    }


    public void removeAt(int position) {
        productList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productList.size());
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setArabic(boolean arabic) {
        this.isArabic = arabic;
    }

    interface UpdateDataClickListener {
        void onDeleteOrder(int pos);

        void onPickQuantity(int pos, int quantity, String newPrice, ActionEnum actionEnum);

        void showMessage(String string);
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_imageView)
        ImageView productImage;
        @BindView(R.id.product_name)
        AppCompatTextView productName;
        @BindView(R.id.product_pice_label)
        AppCompatTextView price;
        @BindView(R.id.currency_label)
        AppCompatTextView currency;
        @BindView(R.id.delete)
        ImageView delete;
        @BindView(R.id.number_picker)
        NumberPicker numberPicker;
        @BindView(R.id.product_color)
        TextView productColor;
        @BindView(R.id.product_size)
        TextView productSize;
        @BindView(R.id.quantitiy)
        TextView quantitiy;
        @BindView(R.id.swipe_layout)
        SwipeLayout swipeLayout;
        @BindView(R.id.celllayout)
        ConstraintLayout celllayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

    }
}
