
package com.selsela.example.data.model.address;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

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
    @SerializedName("areas")
    @Expose
    private List<Area> areas;

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

    protected Gov(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            countryId = null;
        } else {
            countryId = in.readInt();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
        name = in.readString();
        areas = in.createTypedArrayList(Area.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (countryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(countryId);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(name);
        dest.writeTypedList(areas);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Gov> CREATOR = new Creator<Gov>() {
        @Override
        public Gov createFromParcel(Parcel in) {
            return new Gov(in);
        }

        @Override
        public Gov[] newArray(int size) {
            return new Gov[size];
        }
    };

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
        return "Gov{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", deletedAt=" + deletedAt +
                ", name='" + name + '\'' +
                ", areas=" + areas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gov)) return false;

        Gov gov = (Gov) o;

        if (getId() != null ? !getId().equals(gov.getId()) : gov.getId() != null) return false;
        if (getCountryId() != null ? !getCountryId().equals(gov.getCountryId()) : gov.getCountryId() != null)
            return false;
        if (getCreatedAt() != null ? !getCreatedAt().equals(gov.getCreatedAt()) : gov.getCreatedAt() != null)
            return false;
        if (getUpdatedAt() != null ? !getUpdatedAt().equals(gov.getUpdatedAt()) : gov.getUpdatedAt() != null)
            return false;
        if (getDeletedAt() != null ? !getDeletedAt().equals(gov.getDeletedAt()) : gov.getDeletedAt() != null)
            return false;
        if (getName() != null ? !getName().equals(gov.getName()) : gov.getName() != null)
            return false;
        return getAreas() != null ? getAreas().equals(gov.getAreas()) : gov.getAreas() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCountryId() != null ? getCountryId().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        result = 31 * result + (getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0);
        result = 31 * result + (getDeletedAt() != null ? getDeletedAt().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAreas() != null ? getAreas().hashCode() : 0);
        return result;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}
