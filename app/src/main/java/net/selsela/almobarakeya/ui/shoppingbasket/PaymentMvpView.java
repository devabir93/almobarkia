package net.selsela.almobarakeya.ui.shoppingbasket;


import net.selsela.almobarakeya.data.model.coupon.Copone;
import net.selsela.almobarakeya.data.model.order.Order;
import net.selsela.almobarakeya.data.model.send_order.ProductOrderBody;
import net.selsela.almobarakeya.ui.base.MvpView;

import java.util.List;


public interface PaymentMvpView extends MvpView {

    void showSuccess(String msg);

    void showSavedOrders(List<ProductOrderBody> productOrderList);

    void doPayment(Order order);

    void addedToBag(Integer aBoolean);

    void showCoupone(Copone copone);
}
