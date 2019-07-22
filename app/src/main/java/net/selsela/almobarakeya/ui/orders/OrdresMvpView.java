package net.selsela.almobarakeya.ui.orders;

import net.selsela.almobarakeya.data.model.order.Order;
import net.selsela.almobarakeya.ui.base.MvpView;

import java.util.List;

public interface OrdresMvpView extends MvpView {

    void showOrders(List<Order> orders);
}
