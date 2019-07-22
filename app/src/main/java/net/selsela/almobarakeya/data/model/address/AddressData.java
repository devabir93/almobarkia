package net.selsela.almobarakeya.data.model.address;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class AddressData implements Parcelable {

    @SerializedName("addresses")
    @Expose
    private List<Address> addresses;

    /**
     * No args constructor for use in serialization
     */
    public AddressData() {
    }


    protected AddressData(Parcel in) {
        addresses = in.createTypedArrayList(Address.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(addresses);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddressData> CREATOR = new Creator<AddressData>() {
        @Override
        public AddressData createFromParcel(Parcel in) {
            return new AddressData(in);
        }

        @Override
        public AddressData[] newArray(int size) {
            return new AddressData[size];
        }
    };

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
