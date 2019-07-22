package net.selsela.almobarakeya.ui.notifications;


import net.selsela.almobarakeya.data.model.notifications.Notification;
import net.selsela.almobarakeya.ui.base.MvpView;

import java.util.List;

public interface NotificationsMvpView extends MvpView {

    void showNotifications(List<Notification> notificationsList);



}
