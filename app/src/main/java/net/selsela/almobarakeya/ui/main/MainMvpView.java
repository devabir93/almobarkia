package net.selsela.almobarakeya.ui.main;


import net.selsela.almobarakeya.data.model.config.ConfigData;
import net.selsela.almobarakeya.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showSettingData(ConfigData settingData);

}
