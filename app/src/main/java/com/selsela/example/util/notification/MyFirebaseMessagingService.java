package com.selsela.example.util.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import javax.inject.Inject;

import timber.log.Timber;
import com.selsela.example.SelselaApplication;
import com.selsela.example.R;
import com.selsela.example.data.DataManager;
import com.selsela.example.data.local.UserSession;
import com.selsela.example.injection.component.DaggerServiceComponent;
import com.selsela.example.injection.component.ServiceComponent;
import com.selsela.example.injection.module.ServiceModule;
import com.selsela.example.ui.auoth.LoginActivity;

/**
 * Created by Ravi Tamada on 08/08/16.
 * www.androidhive.info
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    @Inject
    DataManager manager;
    @Inject
    UserSession userSession;
    private NotificationUtils notificationUtils;
    private ServiceComponent serviceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        getServiceComponent().inject(this);
    }

    public ServiceComponent getServiceComponent() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.builder()
                    .serviceModule(new ServiceModule(this))
                    .applicationComponent(SelselaApplication.get(this).getComponent())
                    .build();
        }
        return serviceComponent;
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Timber.d("no - From: %s", remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            remoteMessage.getSentTime();
//            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
//            handleNotification(remoteMessage.getNotification().getBody(), remoteMessage.getSentTime());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Timber.d("Data Payload: %s ", remoteMessage.getData().toString());

            try {
                String value = new Gson().toJson(remoteMessage.getData());

                JSONObject json = new JSONObject(value);
                handleDataMessage(json);
            } catch (Exception e) {
                Timber.e(e, "no onMessageReceived: ");
            }
        }


    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String title, String messageBody) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);


            CharSequence adminChannelName = getString(R.string.app_name);

            NotificationChannel adminChannel;
            adminChannel = new NotificationChannel(channelId, adminChannelName, NotificationManager.IMPORTANCE_LOW);
            adminChannel.setDescription(title);
            adminChannel.enableLights(true);
            adminChannel.setLightColor(Color.BLUE);
            adminChannel.enableVibration(true);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(adminChannel);
            }


        }
//
//
        int notificationId = new Random().nextInt(60000);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        //.setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);


        notificationManager.notify(notificationId, notificationBuilder.build());


    }

    private void handleDataMessage(JSONObject json) {
        Timber.d("no - push json: %s", json.toString());


        try {
//            JSONObject data = json.getJSONObject("data");

            String priority = json.getString("priority");
            String body = json.getString("body");
            String sound = json.getString("sound");
            String title = json.getString("title");
            String content_available = json.getString("content_available");


            sendNotification(title, body);


        } catch (JSONException e) {
            Timber.e(e, "Json Exception: ");
        } catch (Exception e) {
            Timber.e(e, "Exception: ");
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(int id, Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        notificationUtils.showNotificationMessage(id, title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(int id, Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        notificationUtils.showNotificationMessage(id, title, message, timeStamp, intent, imageUrl);
    }


    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Timber.e("NEW_TOKEN 555:%s", token);
/*        userSession.addNotificationToken(token);
        // sending reg id to your server
        if (manager.getUserSession() != null) {
            UserBody userBody = new UserBody();
            userBody.setKey(token);
            userBody.setUser_id(userSession.getCurrentUser().getId());
            userBody.setToken(userSession.getCurrentUser().getToken());

            manager.updateDeviceKey(userBody).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<LoginResponse>() {

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }


                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(LoginResponse response) {

                            Timber.e("NEW_TOKEN 5555 :%s", response.toString());
                        }


                    });
            ;
        }*/
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", token);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);

    }

}


