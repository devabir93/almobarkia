package com.selsela.example.ui.shoppingbasket;


import com.selsela.example.data.model.address.Address;
import com.selsela.example.data.model.coupon.Copone;
import com.selsela.example.data.model.order.Order;
import com.selsela.example.data.model.send_order.ProductOrderBody;
import com.selsela.example.ui.base.MvpView;

import java.util.List;


public interface PaymentMvpView extends MvpView {

    void showSuccess(String msg);

    void showSavedOrders(List<ProductOrderBody> productOrderList);

    void doPayment(Order order);

    void addedToBag(Integer aBoolean);

    void showCoupone(Copone copone);
}
