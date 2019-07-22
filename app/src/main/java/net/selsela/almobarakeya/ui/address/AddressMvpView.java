package net.selsela.almobarakeya.ui.address;


import net.selsela.almobarakeya.data.model.address.Address;
import net.selsela.almobarakeya.data.model.address.Gov;
import net.selsela.almobarakeya.ui.base.MvpView;

import java.util.List;

public interface AddressMvpView extends MvpView {

    void showGov(List<Gov> productOrderList);

    void showAddress(List<Address> addresses);

}
