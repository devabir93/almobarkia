package com.selsela.example.ui.about;


import com.selsela.example.data.model.about.AboutData;
import com.selsela.example.ui.base.MvpView;

public interface AboutMvpView extends MvpView {

    void showData(AboutData data);
}
