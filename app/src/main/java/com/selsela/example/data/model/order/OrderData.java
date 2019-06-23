
package com.selsela.example.data.model.order;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selsela.example.data.model.order.Order;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OrderData implements Parcelable
{

    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderData() {
    }

    /**
     *
     * @param orders
     */
    public OrderData(List<Order> orders) {
        super();
        this.orders = orders;
    }

    protected OrderData(Parcel in) {
        orders = in.createTypedArrayList(Order.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(orders);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderData> CREATOR = new Creator<OrderData>() {
        @Override
        public OrderData createFromParcel(Parcel in) {
            return new OrderData(in);
        }

        @Override
        public OrderData[] newArray(int size) {
            return new OrderData[size];
        }
    };

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("orders", orders).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(orders).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderData) == false) {
            return false;
        }
        OrderData rhs = ((OrderData) other);
        return new EqualsBuilder().append(orders, rhs.orders).isEquals();
    }

}
