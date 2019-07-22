
package net.selsela.almobarakeya.data.model.config;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaymentPeriod implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("months")
    @Expose
    private Integer months;
    @SerializedName("discount_ratio")
    @Expose
    private Integer discountRatio;
    @SerializedName("months_text_ar")
    @Expose
    private String monthsTextAr;
    @SerializedName("months_text_en")
    @Expose
    private String monthsTextEn;
    public final static Creator<PaymentPeriod> CREATOR = new Creator<PaymentPeriod>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PaymentPeriod createFromParcel(Parcel in) {
            return new PaymentPeriod(in);
        }

        public PaymentPeriod[] newArray(int size) {
            return (new PaymentPeriod[size]);
        }

    }
    ;

    protected PaymentPeriod(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.months = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.discountRatio = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.monthsTextAr = ((String) in.readValue((String.class.getClassLoader())));
        this.monthsTextEn = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PaymentPeriod() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(Integer discountRatio) {
        this.discountRatio = discountRatio;
    }

    public String getMonthsTextAr() {
        return monthsTextAr;
    }

    public void setMonthsTextAr(String monthsTextAr) {
        this.monthsTextAr = monthsTextAr;
    }

    public String getMonthsTextEn() {
        return monthsTextEn;
    }

    public void setMonthsTextEn(String monthsTextEn) {
        this.monthsTextEn = monthsTextEn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("months", months).append("discountRatio", discountRatio).append("monthsTextAr", monthsTextAr).append("monthsTextEn", monthsTextEn).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(months).append(monthsTextEn).append(monthsTextAr).append(discountRatio).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentPeriod) == false) {
            return false;
        }
        PaymentPeriod rhs = ((PaymentPeriod) other);
        return new EqualsBuilder().append(id, rhs.id).append(months, rhs.months).append(monthsTextEn, rhs.monthsTextEn).append(monthsTextAr, rhs.monthsTextAr).append(discountRatio, rhs.discountRatio).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(months);
        dest.writeValue(discountRatio);
        dest.writeValue(monthsTextAr);
        dest.writeValue(monthsTextEn);
    }

    public int describeContents() {
        return  0;
    }

}
