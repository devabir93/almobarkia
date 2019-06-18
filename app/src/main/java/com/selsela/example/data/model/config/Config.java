
package com.selsela.example.data.model.config;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Config implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("whatsapp")
    @Expose
    private String whatsapp;
    @SerializedName("ios")
    @Expose
    private String ios;
    @SerializedName("android")
    @Expose
    private String android;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("cur")
    @Expose
    private String currency;
    @SerializedName("cur_en")
    @Expose
    private String currencyEn;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("iban")
    @Expose
    private String iban;
    @SerializedName("bank")
    @Expose
    private String bank;
    @SerializedName("iban_owner")
    @Expose
    private String ibanOwner;
    @SerializedName("map_key_ios")
    @Expose
    private String mapKeyIos;
    @SerializedName("map_key_android")
    @Expose
    private String mapKeyAndroid;
    @SerializedName("map_geolocation_key")
    @Expose
    private String mapGeolocationKey;
    @SerializedName("free_period_start")
    @Expose
    private String freePeriodStart;
    @SerializedName("free_period_end")
    @Expose
    private String freePeriodEnd;
    @SerializedName("free_period_months")
    @Expose
    private String freePeriodMonths;
    @SerializedName("free_renew_subscriptin_period")
    @Expose
    private String freeRenewSubscriptinPeriod;
    @SerializedName("free_register_period")
    @Expose
    private String freeRegisterPeriod;
    @SerializedName("free_add_categories_period")
    @Expose
    private String freeAddCategoriesPeriod;
    @SerializedName("is_free")
    @Expose
    private Integer isFree;
    public final static Creator<Config> CREATOR = new Creator<Config>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Config createFromParcel(Parcel in) {
            return new Config(in);
        }

        public Config[] newArray(int size) {
            return (new Config[size]);
        }

    }
    ;

    protected Config(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.whatsapp = ((String) in.readValue((String.class.getClassLoader())));
        this.ios = ((String) in.readValue((String.class.getClassLoader())));
        this.android = ((String) in.readValue((String.class.getClassLoader())));
        this.facebook = ((String) in.readValue((String.class.getClassLoader())));
        this.twitter = ((String) in.readValue((String.class.getClassLoader())));
        this.instagram = ((String) in.readValue((String.class.getClassLoader())));
        this.currency = ((String) in.readValue((String.class.getClassLoader())));
        this.currencyEn = ((String) in.readValue((String.class.getClassLoader())));
        this.tax = ((String) in.readValue((String.class.getClassLoader())));
        this.iban = ((String) in.readValue((String.class.getClassLoader())));
        this.bank = ((String) in.readValue((String.class.getClassLoader())));
        this.ibanOwner = ((String) in.readValue((String.class.getClassLoader())));
        this.mapKeyIos = ((String) in.readValue((String.class.getClassLoader())));
        this.mapKeyAndroid = ((String) in.readValue((String.class.getClassLoader())));
        this.mapGeolocationKey = ((String) in.readValue((String.class.getClassLoader())));
        this.freePeriodStart = ((String) in.readValue((String.class.getClassLoader())));
        this.freePeriodEnd = ((String) in.readValue((String.class.getClassLoader())));
        this.freePeriodMonths = ((String) in.readValue((String.class.getClassLoader())));
        this.freeRenewSubscriptinPeriod = ((String) in.readValue((String.class.getClassLoader())));
        this.freeRegisterPeriod = ((String) in.readValue((String.class.getClassLoader())));
        this.freeAddCategoriesPeriod = ((String) in.readValue((String.class.getClassLoader())));
        this.isFree = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Config() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getIos() {
        return ios;
    }

    public void setIos(String ios) {
        this.ios = ios;
    }

    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyEn() {
        return currencyEn;
    }

    public void setCurrencyEn(String currencyEn) {
        this.currencyEn = currencyEn;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getIbanOwner() {
        return ibanOwner;
    }

    public void setIbanOwner(String ibanOwner) {
        this.ibanOwner = ibanOwner;
    }

    public String getMapKeyIos() {
        return mapKeyIos;
    }

    public void setMapKeyIos(String mapKeyIos) {
        this.mapKeyIos = mapKeyIos;
    }

    public String getMapKeyAndroid() {
        return mapKeyAndroid;
    }

    public void setMapKeyAndroid(String mapKeyAndroid) {
        this.mapKeyAndroid = mapKeyAndroid;
    }

    public String getMapGeolocationKey() {
        return mapGeolocationKey;
    }

    public void setMapGeolocationKey(String mapGeolocationKey) {
        this.mapGeolocationKey = mapGeolocationKey;
    }

    public String getFreePeriodStart() {
        return freePeriodStart;
    }

    public void setFreePeriodStart(String freePeriodStart) {
        this.freePeriodStart = freePeriodStart;
    }

    public String getFreePeriodEnd() {
        return freePeriodEnd;
    }

    public void setFreePeriodEnd(String freePeriodEnd) {
        this.freePeriodEnd = freePeriodEnd;
    }

    public String getFreePeriodMonths() {
        return freePeriodMonths;
    }

    public void setFreePeriodMonths(String freePeriodMonths) {
        this.freePeriodMonths = freePeriodMonths;
    }

    public String getFreeRenewSubscriptinPeriod() {
        return freeRenewSubscriptinPeriod;
    }

    public void setFreeRenewSubscriptinPeriod(String freeRenewSubscriptinPeriod) {
        this.freeRenewSubscriptinPeriod = freeRenewSubscriptinPeriod;
    }

    public String getFreeRegisterPeriod() {
        return freeRegisterPeriod;
    }

    public void setFreeRegisterPeriod(String freeRegisterPeriod) {
        this.freeRegisterPeriod = freeRegisterPeriod;
    }

    public String getFreeAddCategoriesPeriod() {
        return freeAddCategoriesPeriod;
    }

    public void setFreeAddCategoriesPeriod(String freeAddCategoriesPeriod) {
        this.freeAddCategoriesPeriod = freeAddCategoriesPeriod;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("email", email).append("mobile", mobile).append("whatsapp", whatsapp).append("ios", ios).append("android", android).append("facebook", facebook).append("twitter", twitter).append("instagram", instagram).append("currency", currency).append("currencyEn", currencyEn).append("tax", tax).append("iban", iban).append("bank", bank).append("ibanOwner", ibanOwner).append("mapKeyIos", mapKeyIos).append("mapKeyAndroid", mapKeyAndroid).append("mapGeolocationKey", mapGeolocationKey).append("freePeriodStart", freePeriodStart).append("freePeriodEnd", freePeriodEnd).append("freePeriodMonths", freePeriodMonths).append("freeRenewSubscriptinPeriod", freeRenewSubscriptinPeriod).append("freeRegisterPeriod", freeRegisterPeriod).append("freeAddCategoriesPeriod", freeAddCategoriesPeriod).append("isFree", isFree).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(instagram).append(android).append(freeRegisterPeriod).append(freePeriodStart).append(currencyEn).append(whatsapp).append(currency).append(twitter).append(facebook).append(name).append(freePeriodEnd).append(bank).append(mapKeyIos).append(ibanOwner).append(freePeriodMonths).append(ios).append(mapKeyAndroid).append(mapGeolocationKey).append(freeAddCategoriesPeriod).append(freeRenewSubscriptinPeriod).append(tax).append(isFree).append(email).append(iban).append(mobile).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Config) == false) {
            return false;
        }
        Config rhs = ((Config) other);
        return new EqualsBuilder().append(instagram, rhs.instagram).append(android, rhs.android).append(freeRegisterPeriod, rhs.freeRegisterPeriod).append(freePeriodStart, rhs.freePeriodStart).append(currencyEn, rhs.currencyEn).append(whatsapp, rhs.whatsapp).append(currency, rhs.currency).append(twitter, rhs.twitter).append(facebook, rhs.facebook).append(name, rhs.name).append(freePeriodEnd, rhs.freePeriodEnd).append(bank, rhs.bank).append(mapKeyIos, rhs.mapKeyIos).append(ibanOwner, rhs.ibanOwner).append(freePeriodMonths, rhs.freePeriodMonths).append(ios, rhs.ios).append(mapKeyAndroid, rhs.mapKeyAndroid).append(mapGeolocationKey, rhs.mapGeolocationKey).append(freeAddCategoriesPeriod, rhs.freeAddCategoriesPeriod).append(freeRenewSubscriptinPeriod, rhs.freeRenewSubscriptinPeriod).append(tax, rhs.tax).append(isFree, rhs.isFree).append(email, rhs.email).append(iban, rhs.iban).append(mobile, rhs.mobile).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(email);
        dest.writeValue(mobile);
        dest.writeValue(whatsapp);
        dest.writeValue(ios);
        dest.writeValue(android);
        dest.writeValue(facebook);
        dest.writeValue(twitter);
        dest.writeValue(instagram);
        dest.writeValue(currency);
        dest.writeValue(currencyEn);
        dest.writeValue(tax);
        dest.writeValue(iban);
        dest.writeValue(bank);
        dest.writeValue(ibanOwner);
        dest.writeValue(mapKeyIos);
        dest.writeValue(mapKeyAndroid);
        dest.writeValue(mapGeolocationKey);
        dest.writeValue(freePeriodStart);
        dest.writeValue(freePeriodEnd);
        dest.writeValue(freePeriodMonths);
        dest.writeValue(freeRenewSubscriptinPeriod);
        dest.writeValue(freeRegisterPeriod);
        dest.writeValue(freeAddCategoriesPeriod);
        dest.writeValue(isFree);
    }

    public int describeContents() {
        return  0;
    }

}
