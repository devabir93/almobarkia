package net.selsela.almobarakeya.ui.base;


import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.send_order.ProductOrderBody;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView {

    void showMessageDialog(String responseMessage);

    void showProgressView(boolean show);

    void onRequestStart();

    void onRequestEnd();

    void showEmpty();

    void showMessageDialog(BaseResponse response);

    void isExceedWeight(Boolean aBoolean);

    void showCartBadge(Integer integer);

    void showSavedProductOrder(ProductOrderBody productOrder);

    void showCartPrice(Double price);

    void isSuccess(Boolean status);

    void showSuccessDialog(String message);

    void showAlertDialog(String message);
}
