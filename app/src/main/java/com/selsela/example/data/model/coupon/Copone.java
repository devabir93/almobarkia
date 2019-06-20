
package com.selsela.example.data.model.coupon;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Copone implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("used_times")
    @Expose
    private Integer usedTimes;
    @SerializedName("usese_times")
    @Expose
    private Integer useseTimes;
    @SerializedName("expiration_date")
    @Expose
    private String expirationDate;
    @SerializedName("ratio")
    @Expose
    private Integer ratio;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    public final static Creator<Copone> CREATOR = new Creator<Copone>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Copone createFromParcel(Parcel in) {
            return new Copone(in);
        }

        public Copone[] newArray(int size) {
            return (new Copone[size]);
        }

    }
    ;

    protected Copone(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.usedTimes = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.useseTimes = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.expirationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.ratio = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((Object) in.readValue((Object.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.deletedAt = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Copone() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param expirationDate
     * @param status
     * @param ratio
     * @param createdAt
     * @param useseTimes
     * @param deletedAt
     * @param code
     * @param usedTimes
     */
    public Copone(Integer id, String code, Integer usedTimes, Integer useseTimes, String expirationDate, Integer ratio, Integer status, Object createdAt, String updatedAt, Object deletedAt) {
        super();
        this.id = id;
        this.code = code;
        this.usedTimes = usedTimes;
        this.useseTimes = useseTimes;
        this.expirationDate = expirationDate;
        this.ratio = ratio;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(Integer usedTimes) {
        this.usedTimes = usedTimes;
    }

    public Integer getUseseTimes() {
        return useseTimes;
    }

    public void setUseseTimes(Integer useseTimes) {
        this.useseTimes = useseTimes;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("code", code).append("usedTimes", usedTimes).append("useseTimes", useseTimes).append("expirationDate", expirationDate).append("ratio", ratio).append("status", status).append("createdAt", createdAt).append("updatedAt", updatedAt).append("deletedAt", deletedAt).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(updatedAt).append(id).append(expirationDate).append(status).append(ratio).append(createdAt).append(useseTimes).append(deletedAt).append(code).append(usedTimes).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Copone) == false) {
            return false;
        }
        Copone rhs = ((Copone) other);
        return new EqualsBuilder().append(updatedAt, rhs.updatedAt).append(id, rhs.id).append(expirationDate, rhs.expirationDate).append(status, rhs.status).append(ratio, rhs.ratio).append(createdAt, rhs.createdAt).append(useseTimes, rhs.useseTimes).append(deletedAt, rhs.deletedAt).append(code, rhs.code).append(usedTimes, rhs.usedTimes).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(code);
        dest.writeValue(usedTimes);
        dest.writeValue(useseTimes);
        dest.writeValue(expirationDate);
        dest.writeValue(ratio);
        dest.writeValue(status);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(deletedAt);
    }

    public int describeContents() {
        return  0;
    }

}
