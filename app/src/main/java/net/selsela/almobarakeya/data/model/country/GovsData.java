package net.selsela.almobarakeya.data.model.country;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.selsela.almobarakeya.data.model.address.Gov;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class GovsData implements Parcelable {

    @SerializedName("govs")
    @Expose
    private List<Gov> govs = null;

    /**
     * No args constructor for use in serialization
     */
    public GovsData() {
    }

    /**
     * @param govs
     */
    public GovsData(List<Gov> govs) {
        super();
        this.govs = govs;
    }

    protected GovsData(Parcel in) {
        govs = in.createTypedArrayList(Gov.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(govs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GovsData> CREATOR = new Creator<GovsData>() {
        @Override
        public GovsData createFromParcel(Parcel in) {
            return new GovsData(in);
        }

        @Override
        public GovsData[] newArray(int size) {
            return new GovsData[size];
        }
    };

    public List<Gov> getGovs() {
        return govs;
    }

    public void setGovs(List<Gov> govs) {
        this.govs = govs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("govs", govs).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GovsData)) return false;

        GovsData that = (GovsData) o;

        return getGovs() != null ? getGovs().equals(that.getGovs()) : that.getGovs() == null;
    }

    @Override
    public int hashCode() {
        return getGovs() != null ? getGovs().hashCode() : 0;
    }
}
