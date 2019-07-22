package net.selsela.almobarakeya.ui.previeworder;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.local.DataHolder;
import net.selsela.almobarakeya.data.model.boxes.Box;
import net.selsela.almobarakeya.data.model.country.Country;
import net.selsela.almobarakeya.data.model.coupon.Copone;
import net.selsela.almobarakeya.data.model.order.Order;
import net.selsela.almobarakeya.data.model.order.OrderBody;
import net.selsela.almobarakeya.data.model.send_order.ProductOrderBody;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.shoppingbasket.PaymentMvpView;
import net.selsela.almobarakeya.ui.shoppingbasket.PaymentPresenter;
import net.selsela.almobarakeya.util.AppUtils;
import net.selsela.almobarakeya.util.Const;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static net.selsela.almobarakeya.ui.payment.PaymentActivity.CASH;
import static net.selsela.almobarakeya.ui.payment.PaymentActivity.KNET;

public class PreviewActivity extends BaseActivity implements PaymentMvpView {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.checked_ic)
    ImageView checkedIc;
    @BindView(R.id.dottline)
    View dottline;
    @BindView(R.id.ic_cridet)
    ImageView icCridet;
    @BindView(R.id.dottline2)
    View dottline2;
    @BindView(R.id.billdetails_label)
    TextView billdetailsLabel;
    @BindView(R.id.product_label)
    TextView productLabel;
    @BindView(R.id.paymentmethod_label)
    TextView paymentmethodLabel;
    @BindView(R.id.service_label)
    TextView serviceLabel;
    @BindView(R.id.deliverycost_label)
    TextView deliverycostLabel;
    @BindView(R.id.discountcode_label)
    TextView discountcodeLabel;
    @BindView(R.id.orderstaotal_label)
    TextView orderstaotalLabel;
    @BindView(R.id.discointvalue_label)
    TextView discointvalueLabel;
    @BindView(R.id.discountvalue_textView)
    TextView discountvalueTextView;
    @BindView(R.id.Paymentamount_label)
    TextView PaymentamountLabel;
    @BindView(R.id.back_btt_textView)
    TextView backBttTextView;
    @BindView(R.id.confirm_textView)
    TextView confirmTextView;
    @BindView(R.id.product_size_value)
    TextView productSizeValue;
    @BindView(R.id.payemnt_type_value)
    TextView payemntTypeValue;
    @BindView(R.id.service_cost_value)
    TextView serviceCostValue;
    @BindView(R.id.transport_cost_value)
    TextView transportCostValue;
    @BindView(R.id.coupon_value)
    TextView couponValue;
    @BindView(R.id.total_cost_value)
    TextView totalCostValue;
    @BindView(R.id.total_value)
    TextView totalValue;
    @BindView(R.id.pin_iv)
    ImageView pinIv;
    @BindView(R.id.coupon_label)
    TextView couponLabel;
    @BindView(R.id.discount_label)
    TextView discountLabel;
    private OrderBody orderBody;
    double min = Double.MAX_VALUE;
    double max = Double.MIN_VALUE;
    double finalCost;
    Box minBox, MaxBox;
    private Box mBox;
    double transportCost;
    String totalPrice;
    private Country country;

    @Inject
    PaymentPresenter paymentPresenter;
    String price;
    private String service;
    private List<Box> boxes;
    private String discountValue = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        paymentPresenter.attachView(this);
        activityTitle = getString(R.string.confirm_payment_activity);
        initToolbar();
        fillWithData();
        country = preferencesHelper.getCountry();

        if (getShippingBoxes() != null && getShippingBoxes().size() > 0) {
            Timber.d("getShippingBoxes() != null");
            boxes = preferencesHelper.getShippingBoxes().getBoxs();
            paymentPresenter.getSavedOrders();
        }

    }

    private void fillWithData() {
        orderBody = DataHolder.getOrder();
        if (orderBody.getPayment_type() == CASH) {
            payemntTypeValue.setText(getString(R.string.cash));
        } else if (orderBody.getPayment_type() == KNET) {
            payemntTypeValue.setText(getString(R.string.knet));
        }

        transportCost = orderBody.getTransport_cost();
        service = AppUtils.calculateEquation(preferencesHelper.getCurrentUserSetting().getConfig().getService_cost()
                + "*" + preferencesHelper.getCountry().getConversionFactor());

        price = String.valueOf(orderBody.getPrice());
        totalCostValue.setText(orderBody.getPrice() + " " + getCurrency());
        if (orderBody.getCoupon() != null) {
            String ratioPrice = AppUtils.calculateDiscount(orderBody.getPrice(), orderBody.getCoupon().getRatio());
            couponLabel.setVisibility(View.VISIBLE);
            discountcodeLabel.setVisibility(View.VISIBLE);
            discointvalueLabel.setVisibility(View.VISIBLE);
            discountLabel.setVisibility(View.VISIBLE);
            discountvalueTextView.setVisibility(View.VISIBLE);
            couponValue.setVisibility(View.VISIBLE);
            couponValue.setText(orderBody.getCoupon().getCode());
            discountValue = AppUtils.calculateEquation(orderBody.getPrice() + "-" + ratioPrice);
            discountvalueTextView.setText("- " + discountValue + " " + getCurrency());
        }
        calculatePrice();
    }

    private void calculatePrice() {
        transportCostValue.setText(transportCost + " " + getCurrency());
        serviceCostValue.setText(service + " " + getCurrency());
        price = AppUtils.calculateEquation(orderBody.getPrice() + " + " + transportCost + "+" + service + "-" + discountValue);
        totalValue.setText(price + " " + getCurrency());
    }

    @OnClick({R.id.back_btt_textView, R.id.confirm_textView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_textView:
                orderBody.setTransport_cost(transportCost);
                orderBody.setPrice(Double.parseDouble(price));
                orderBody.setBox_id(mBox != null ? mBox.getId() : 0);
                paymentPresenter.sendOrder(orderBody);
                break;
            case R.id.back_btt_textView:
                onBackPressed();
                break;
        }
    }

    @Override
    public void showSuccess(String msg) {

        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_paymentsuccess, false)
                .build();
        View view = dialog.getCustomView();
        TextView cancel_action = view.findViewById(R.id.cancel_action);
        TextView textView = view.findViewById(R.id.feedback_textView);
        textView.setText(msg);
        cancel_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                goToMain();
            }
        });

        dialog.show();
    }


    @Override
    public void showSavedOrders(List<ProductOrderBody> productOrderList) {
        calculateBoxWeight(productOrderList);

    }

    @Override
    public void doPayment(Order order) {

    }

    @Override
    public void addedToBag(Integer aBoolean) {

    }

    @Override
    public void showCoupone(Copone copone) {

    }


    public void calculateBoxWeight(List<ProductOrderBody> productOrderList) {
        double totalWeight = 0;
        if (boxes != null) {
            if (productOrderList != null && productOrderList.size() > 0) {
                for (ProductOrderBody productOrderBody : productOrderList) {
                    totalWeight += productOrderBody.getProduct().getWeight() * productOrderBody.getQuantity();
                }

                if (!country.getPrefix().equals(Const.KUWAIT)) {
                    if (boxes != null && boxes.size() > 0) {

                        for (Box box : boxes) {
                            if (box.getWeight() < min) {
                                minBox = box;
                                min = box.getWeight();
                                Timber.d("min %s", minBox);
                            }
                            if (box.getWeight() > max) {
                                max = box.getWeight();
                                MaxBox = box;
                                Timber.d("MaxBox %s", MaxBox);
                            }
                        }
                        transportCost = getMinBox(totalWeight)
                        //   * preferencesHelper.getCountry().getConversionFactor()
                        ;

                        calculatePrice();
                    }
                }
            } else
                Timber.d("null saved");
        } else
            Timber.d("null boxes");
    }

    Double getMinBox(double totalWeight) {
        Timber.d("getMinBox %s", totalWeight);
        List<Box> minboxes = new ArrayList<>();
        if (totalWeight <= min) {
            mBox = minBox;
            return minBox.getCost();
        } else if (totalWeight == max) {
            mBox = MaxBox;
            return MaxBox.getCost();
        }
        for (Box box : boxes) {
            if (box.getWeight() > totalWeight
                //&& box.getWeight() <= max
            ) {
                minboxes.add(box);
            }
        }
        mBox = getMin(minboxes);
        Timber.d("getMin(minboxes) %s ", getMin(minboxes));
        return getMin(minboxes).getCost();
    }

    public Box getMin(List<Box> boxes) {
        Timber.d("getMin %s", boxes);
        double min = Double.MAX_VALUE;
        Box minbox = new Box();
        for (Box box : boxes) {
            if (box.getWeight() < min) {
                min = box.getWeight();
                minbox = box;
            }
        }
        return minbox;
    }
}
