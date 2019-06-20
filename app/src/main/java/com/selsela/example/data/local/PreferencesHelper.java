package com.selsela.example.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.selsela.example.data.model.boxes.BoxsData;
import com.selsela.example.data.model.config.ConfigData;
import com.selsela.example.data.model.country.Country;
import com.selsela.example.data.model.user.UserData;
import com.selsela.example.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_buffet_pref_file";
    public static final String SHARED_PREF = "ah_firebase_buffet";
    private static final String GUEST_name = "GUEST_name";
    private static final String GUEST_mobile = "GUEST_mobile";
    private final SharedPreferences mPref;
    private final String currentUserKey = "userSession_buffet";
    private final String currentUserSettingKey = "currentUserSettingKey_buffet";
    private final String GUEST = "GUEST";
    private final String currentSelectedCountry = "currentSelectedCountry";
    private final String first_run = "first_run";
    private final String currentCountries = "currentCountries";
    private final String BoxsData = "boxes";

    @NonNull
    Gson gson = new GsonBuilder().create();

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public void addUserSession(UserData user) {
        Timber.d("user %s", user);
        String jsonUser = gson.toJson(user);
        mPref.edit().putString(currentUserKey, jsonUser).apply();
    }

    public UserData getCurrentUser() {
        String jsonUser = mPref.getString(currentUserKey, null);
        return gson.fromJson(jsonUser, UserData.class);
    }

    public void removeUserSession() {
        mPref.edit().putString(currentUserKey, "").apply();
    }

    public void addUserSetting(ConfigData settingData) {
        String jsonUser = gson.toJson(settingData);
        mPref.edit().putString(currentUserSettingKey, jsonUser).apply();
    }

   public ConfigData getCurrentUserSetting() {
        String jsonUser = mPref.getString(currentUserSettingKey, null);
       return gson.fromJson(jsonUser, ConfigData.class);
    }

    public void addCountry(Country countries) {
        String jsonUser = gson.toJson(countries);
        mPref.edit().putString(currentCountries, jsonUser).apply();
    }

    public Country getCountry() {
        String jsonUser = mPref.getString(currentCountries, null);
        return gson.fromJson(jsonUser, Country.class);
    }

    public String getNotifToken() {
        return mPref.getString(SHARED_PREF, "");
    }

    public void setNotifToken(String token) {
        mPref.edit().putString(SHARED_PREF, token).apply();
    }

    public void addWithKey(String key, String value) {
        mPref.edit().putString(key, value).apply();
    }

    public void addWithKey(String key, int value) {
        mPref.edit().putInt(key, value).apply();
    }

    public void deleteWithKey(String key) {
        mPref.edit().putString(key, "").apply();
    }

    @Nullable
    public String getWithKey(String key) {
        return mPref.getString(key, "");
    }

    public boolean isFirstRun() {
        return mPref.getBoolean(first_run, true);
    }

    public void setFirstRun(boolean b) {
        mPref.edit().putBoolean(first_run, b).apply();
    }

    public void setShippingBoxes(BoxsData boxsData) {
        String jsonUser = gson.toJson(boxsData);
        mPref.edit().putString(BoxsData, jsonUser).apply();
    }

    public BoxsData getShippingBoxes() {
        String jsonUser = mPref.getString(BoxsData, null);
        return gson.fromJson(jsonUser, BoxsData.class);
    }
}
