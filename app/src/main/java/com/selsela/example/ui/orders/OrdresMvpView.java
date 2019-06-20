package com.selsela.example.ui.orders;

import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.model.order.Order;
import com.selsela.example.ui.base.MvpView;
import java.util.List;

public interface OrdresMvpView extends MvpView {

    void showOrders(List<Order> orders);
    void showProducts(List<Product> products);


}
