package net.selsela.almobarakeya.ui.notifications;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.notifications.Notification;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class NotificationsRecyclerViewAdapter extends RecyclerView.Adapter<NotificationsRecyclerViewAdapter.ViewHolder> {
    int type;

    private Context context;
    private List<Notification> notifications;
    UpdateDataClickListener updateDataClickListener;

    public NotificationsRecyclerViewAdapter(List<Notification> notifications, Context context, UpdateDataClickListener updateDataClickListener) {
        this.notifications = notifications;
        this.context = context;
        this.updateDataClickListener = updateDataClickListener;
    }

    public void setLayout(int type) {
        Timber.d("type %s", type);
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notifications_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Notification notification = notifications.get(position);
        if (notification == null)
            return;


        holder.notifyDate.setText(notification.getCreatedAt());
        holder.status.setText(notification.getMessage());
        holder.textNotify2.setText(notification.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataClickListener.onCategorySelected(notification, position);
            }
        });

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        holder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.delete));
            }
        });
    }


    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        @BindView(R.id.delete_layout)
        LinearLayout deleteLayout;
        @BindView(R.id.notifications_icon)
        ImageView notificationsIcon;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.text_notify2)
        TextView textNotify2;
        @BindView(R.id.notify_date)
        TextView notifyDate;
        @BindView(R.id.lay2)
        ConstraintLayout lay2;
        @BindView(R.id.swipe_layout)
        SwipeLayout swipeLayout;


        ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);


        }

        @Override
        public void onClick(View v) {

        }

    }


    public interface UpdateDataClickListener {
        void onCategorySelected(Notification notification, int position);


    }
}
