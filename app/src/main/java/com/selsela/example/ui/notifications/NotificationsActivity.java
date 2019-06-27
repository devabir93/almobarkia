package com.selsela.example.ui.notifications;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.selsela.example.R;
import com.selsela.example.data.model.notifications.Notification;
import com.selsela.example.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends BaseActivity implements NotificationsMvpView {
    @Inject
    NotificationsPresenter notificationsPresenter;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.notifications_recycleView)
    RecyclerView notificationsRecycleView;
    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        notificationsPresenter.attachView(this);
        notificationsPresenter.get_notifications();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                notificationsPresenter.get_notifications();
            }
        });
        activityTitle = getString(R.string.notifications_label);
        initToolbar();

    }

    @Override
    public void showNotifications(List<Notification> notificationsList) {
        notificationsRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        notificationsRecycleView.setAdapter(new NotificationsRecyclerViewAdapter(notificationsList, this, new NotificationsRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onCategorySelected(Notification notification, int position) {

            }
        }));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
