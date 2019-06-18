
package com.selsela.example.data.model.config;

import java.util.HashMap;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ConfigData implements Parcelable
{

    @SerializedName("config")
    @Expose
    private Config config;
    @SerializedName("payment_types")
    @Expose
    private List<PaymentType> paymentTypes = null;
    @SerializedName("payment_periods")
    @Expose
    private List<PaymentPeriod> paymentPeriods = null;
    @SerializedName("days")
    @Expose
    private List<Day> days = null;
    @SerializedName("times")
    @Expose
    private List<Time> times = null;
    public final static Creator<ConfigData> CREATOR = new Creator<ConfigData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ConfigData createFromParcel(Parcel in) {
            return new ConfigData(in);
        }

        public ConfigData[] newArray(int size) {
            return (new ConfigData[size]);
        }

    }
    ;

    protected ConfigData(Parcel in) {
        this.config = ((Config) in.readValue((Config.class.getClassLoader())));
        in.readList(this.paymentTypes, (PaymentType.class.getClassLoader()));
        in.readList(this.paymentPeriods, (PaymentPeriod.class.getClassLoader()));
        in.readList(this.days, (Day.class.getClassLoader()));
        in.readList(this.times, (Time.class.getClassLoader()));
    }

    public ConfigData() {
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public List<PaymentType> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<PaymentType> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public List<PaymentPeriod> getPaymentPeriods() {
        return paymentPeriods;
    }

    public void setPaymentPeriods(List<PaymentPeriod> paymentPeriods) {
        this.paymentPeriods = paymentPeriods;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("config", config).append("paymentTypes", paymentTypes).append("paymentPeriods", paymentPeriods).append("days", days).append("times", times).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(days).append(times).append(paymentPeriods).append(config).append(paymentTypes).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ConfigData) == false) {
            return false;
        }
        ConfigData rhs = ((ConfigData) other);
        return new EqualsBuilder().append(days, rhs.days).append(times, rhs.times).append(paymentPeriods, rhs.paymentPeriods).append(config, rhs.config).append(paymentTypes, rhs.paymentTypes).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(config);
        dest.writeList(paymentTypes);
        dest.writeList(paymentPeriods);
        dest.writeList(days);
        dest.writeList(times);
    }

    public int describeContents() {
        return  0;
    }

}
