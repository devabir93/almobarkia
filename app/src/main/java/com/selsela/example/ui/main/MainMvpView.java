package com.selsela.example.ui.main;


import com.selsela.example.data.model.about.AboutData;
import com.selsela.example.data.model.config.ConfigData;
import com.selsela.example.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showSettingData(ConfigData settingData);

}
