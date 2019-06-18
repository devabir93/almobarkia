package com.selsela.example.data.local;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;
import com.selsela.example.data.DataManager;
import com.selsela.example.data.model.user.UserData;

@Singleton
public class UserSession {

    private final DataManager mDataManager;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public UserSession(DataManager mDataManager, PreferencesHelper mPreferencesHelper) {
        this.mDataManager = mDataManager;
        this.mPreferencesHelper = mPreferencesHelper;
    }

    public UserData getCurrentUser() {
        return mPreferencesHelper.getCurrentUser();
    }

    public boolean hasActiveSession() {
        return mPreferencesHelper.getCurrentUser() != null;
    }

    public void createSession(UserData user) {
        mPreferencesHelper.addUserSession(user);
    }

    public void destroySession() {
        mPreferencesHelper.removeUserSession();
    }

    public void addNotificationToken(String token) {
        Timber.d("addNotificationToken %s", token);
        mPreferencesHelper.setNotifToken(token);
    }

    public String getNotificationToken() {
        return mPreferencesHelper.getNotifToken();
    }

}
