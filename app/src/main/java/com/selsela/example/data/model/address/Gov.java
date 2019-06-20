
package com.selsela.example.data.model.address;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Gov implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<Gov> CREATOR = new Creator<Gov>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Gov createFromParcel(Parcel in) {
            return new Gov(in);
        }

        public Gov[] newArray(int size) {
            return (new Gov[size]);
        }

    }
    ;

    protected Gov(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.countryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.deletedAt = ((Object) in.readValue((Object.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Gov() {
    }

    /**
     * 
     * @param updatedAt
     * @param countryId
     * @param id
     * @param createdAt
     * @param name
     * @param deletedAt
     */
    public Gov(Integer id, Integer countryId, String createdAt, String updatedAt, Object deletedAt, String name) {
        super();
        this.id = id;
        this.countryId = countryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("countryId", countryId).append("createdAt", createdAt).append("updatedAt", updatedAt).append("deletedAt", deletedAt).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(updatedAt).append(countryId).append(id).append(createdAt).append(name).append(deletedAt).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Gov) == false) {
            return false;
        }
        Gov rhs = ((Gov) other);
        return new EqualsBuilder().append(updatedAt, rhs.updatedAt).append(countryId, rhs.countryId).append(id, rhs.id).append(createdAt, rhs.createdAt).append(name, rhs.name).append(deletedAt, rhs.deletedAt).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(countryId);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(deletedAt);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
