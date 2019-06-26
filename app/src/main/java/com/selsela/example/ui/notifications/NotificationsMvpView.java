package com.selsela.example.ui.notifications;


import com.selsela.example.data.model.home.MainCategory;
import com.selsela.example.data.model.notifications.Notification;
import com.selsela.example.ui.base.MvpView;

import java.util.List;

public interface NotificationsMvpView extends MvpView {

    void showNotifications(List<Notification> notificationsList);



}
