package net.selsela.almobarakeya.ui.about;


import net.selsela.almobarakeya.data.model.about.AboutData;
import net.selsela.almobarakeya.ui.base.MvpView;

public interface AboutMvpView extends MvpView {

    void showData(AboutData data);
}
