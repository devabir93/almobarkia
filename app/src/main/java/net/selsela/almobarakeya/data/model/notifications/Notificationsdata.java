
package net.selsela.almobarakeya.data.model.notifications;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Notificationsdata implements Parcelable
{

    @SerializedName("notifications")
    @Expose
    private List<Notification> notifications = null;
    public final static Creator<Notificationsdata> CREATOR = new Creator<Notificationsdata>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Notificationsdata createFromParcel(Parcel in) {
            return new Notificationsdata(in);
        }

        public Notificationsdata[] newArray(int size) {
            return (new Notificationsdata[size]);
        }

    }
    ;

    protected Notificationsdata(Parcel in) {
        in.readList(this.notifications, (net.selsela.almobarakeya.data.model.notifications.Notification.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Notificationsdata() {
    }

    /**
     * 
     * @param notifications
     */
    public Notificationsdata(List<Notification> notifications) {
        super();
        this.notifications = notifications;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("notifications", notifications).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(notifications).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Notificationsdata) == false) {
            return false;
        }
        Notificationsdata rhs = ((Notificationsdata) other);
        return new EqualsBuilder().append(notifications, rhs.notifications).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(notifications);
    }

    public int describeContents() {
        return  0;
    }

}
