package com.selsela.example.ui.base;


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


    void isExceedWeight(Boolean aBoolean);
}
