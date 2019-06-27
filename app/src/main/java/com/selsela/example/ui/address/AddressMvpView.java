package com.selsela.example.ui.address;


import com.selsela.example.data.model.address.Address;
import com.selsela.example.data.model.address.Gov;
import com.selsela.example.ui.base.MvpView;

import java.util.List;

public interface AddressMvpView extends MvpView {

    void showGov(List<Gov> productOrderList);

    void showAddress(List<Address> addresses);

}
