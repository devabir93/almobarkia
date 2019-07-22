package net.selsela.almobarakeya.ui.notifications;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.notifications.Notification;
import net.selsela.almobarakeya.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends BaseActivity implements NotificationsMvpView {
    @Inject
    NotificationsPresenter notificationsPresenter;
    @BindView(R.id.notifications_recycleView)
    RecyclerView notificationsRecycleView;
    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout pullToRefresh;
    @BindView(R.id.empty_view)
    TextView emptyView;

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
        notificationsRecycleView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
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


    @Override
    public void showEmpty() {
        super.showEmpty();
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText(getString(R.string.empty_result_search));
        notificationsRecycleView.setVisibility(View.GONE);
    }
}
