
package net.selsela.almobarakeya.data.model.address;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.selsela.almobarakeya.data.model.country.Country;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Address implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("gov_id")
    @Expose
    private Integer govId;
    @SerializedName("area_id")
    @Expose
    private Integer areaId;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("sub_street")
    @Expose
    private String subStreet;
    @SerializedName("building_or_house")
    @Expose
    private Integer buildingOrHouse;
    @SerializedName("building_number")
    @Expose
    private String buildingNumber;
    @SerializedName("saved")
    @Expose
    private Integer saved;
    @SerializedName("block_number")
    @Expose
    private String blockNumber;
    @SerializedName("floor_number")
    @Expose
    private String floorNumber;
    @SerializedName("flat_number")
    @Expose
    private String flatNumber;
    @SerializedName("full_address")
    @Expose
    private String fullAddress;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("gov_txt")
    @Expose
    private String govTxt;
    @SerializedName("area_txt")
    @Expose
    private String areaTxt;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("gov")
    @Expose
    private Gov gov;
    @SerializedName("area")
    @Expose
    private Area area;

    /**
     * No args constructor for use in serialization
     *
     */
    public Address() {
    }

    /**
     *
     * @param countryId
     * @param fullAddress
     * @param street
     * @param saved
     * @param govTxt
     * @param areaId
     * @param country
     * @param updatedAt
     * @param id
     * @param area
     * @param areaTxt
     * @param subStreet
     * @param floorNumber
     * @param createdAt
     * @param userId
     * @param govId
     * @param buildingOrHouse
     * @param flatNumber
     * @param gov
     * @param buildingNumber
     * @param blockNumber
     */
    public Address(Integer id, Integer userId, Integer countryId, Integer govId, Integer areaId, String street, String subStreet, Integer buildingOrHouse, String buildingNumber, Integer saved, String blockNumber, String floorNumber, String flatNumber, String fullAddress, String createdAt, String updatedAt, String govTxt, String areaTxt, Country country, Gov gov, Area area) {
        super();
        this.id = id;
        this.userId = userId;
        this.countryId = countryId;
        this.govId = govId;
        this.areaId = areaId;
        this.street = street;
        this.subStreet = subStreet;
        this.buildingOrHouse = buildingOrHouse;
        this.buildingNumber = buildingNumber;
        this.saved = saved;
        this.blockNumber = blockNumber;
        this.floorNumber = floorNumber;
        this.flatNumber = flatNumber;
        this.fullAddress = fullAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.govTxt = govTxt;
        this.areaTxt = areaTxt;
        this.country = country;
        this.gov = gov;
        this.area = area;
    }

    protected Address(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        if (in.readByte() == 0) {
            countryId = null;
        } else {
            countryId = in.readInt();
        }
        if (in.readByte() == 0) {
            govId = null;
        } else {
            govId = in.readInt();
        }
        if (in.readByte() == 0) {
            areaId = null;
        } else {
            areaId = in.readInt();
        }
        street = in.readString();
        subStreet = in.readString();
        if (in.readByte() == 0) {
            buildingOrHouse = null;
        } else {
            buildingOrHouse = in.readInt();
        }
        buildingNumber = in.readString();
        if (in.readByte() == 0) {
            saved = null;
        } else {
            saved = in.readInt();
        }
        blockNumber = in.readString();
        floorNumber = in.readString();
        flatNumber = in.readString();
        fullAddress = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        govTxt = in.readString();
        areaTxt = in.readString();
        country = in.readParcelable(Country.class.getClassLoader());
        gov = in.readParcelable(Gov.class.getClassLoader());
        area = in.readParcelable(Area.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        if (countryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(countryId);
        }
        if (govId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(govId);
        }
        if (areaId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(areaId);
        }
        dest.writeString(street);
        dest.writeString(subStreet);
        if (buildingOrHouse == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(buildingOrHouse);
        }
        dest.writeString(buildingNumber);
        if (saved == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(saved);
        }
        dest.writeString(blockNumber);
        dest.writeString(floorNumber);
        dest.writeString(flatNumber);
        dest.writeString(fullAddress);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(govTxt);
        dest.writeString(areaTxt);
        dest.writeParcelable(country, flags);
        dest.writeParcelable(gov, flags);
        dest.writeParcelable(area, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getGovId() {
        return govId;
    }

    public void setGovId(Integer govId) {
        this.govId = govId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSubStreet() {
        return subStreet;
    }

    public void setSubStreet(String subStreet) {
        this.subStreet = subStreet;
    }

    public Integer getBuildingOrHouse() {
        return buildingOrHouse;
    }

    public void setBuildingOrHouse(Integer buildingOrHouse) {
        this.buildingOrHouse = buildingOrHouse;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getSaved() {
        return saved;
    }

    public void setSaved(Integer saved) {
        this.saved = saved;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
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

    public String getGovTxt() {
        return govTxt;
    }

    public void setGovTxt(String govTxt) {
        this.govTxt = govTxt;
    }

    public String getAreaTxt() {
        return areaTxt;
    }

    public void setAreaTxt(String areaTxt) {
        this.areaTxt = areaTxt;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Gov getGov() {
        return gov;
    }

    public void setGov(Gov gov) {
        this.gov = gov;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("userId", userId).append("countryId", countryId).append("govId", govId).append("areaId", areaId).append("street", street).append("subStreet", subStreet).append("buildingOrHouse", buildingOrHouse).append("buildingNumber", buildingNumber).append("saved", saved).append("blockNumber", blockNumber).append("floorNumber", floorNumber).append("flatNumber", flatNumber).append("fullAddress", fullAddress).append("createdAt", createdAt).append("updatedAt", updatedAt).append("govTxt", govTxt).append("areaTxt", areaTxt).append("country", country).append("gov", gov).append("area", area).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(countryId).append(fullAddress).append(street).append(saved).append(govTxt).append(areaId).append(country).append(updatedAt).append(id).append(area).append(areaTxt).append(floorNumber).append(subStreet).append(createdAt).append(govId).append(userId).append(buildingOrHouse).append(flatNumber).append(gov).append(blockNumber).append(buildingNumber).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Address) == false) {
            return false;
        }
        Address rhs = ((Address) other);
        return new EqualsBuilder().append(countryId, rhs.countryId).append(fullAddress, rhs.fullAddress).append(street, rhs.street).append(saved, rhs.saved).append(govTxt, rhs.govTxt).append(areaId, rhs.areaId).append(country, rhs.country).append(updatedAt, rhs.updatedAt).append(id, rhs.id).append(area, rhs.area).append(areaTxt, rhs.areaTxt).append(floorNumber, rhs.floorNumber).append(subStreet, rhs.subStreet).append(createdAt, rhs.createdAt).append(govId, rhs.govId).append(userId, rhs.userId).append(buildingOrHouse, rhs.buildingOrHouse).append(flatNumber, rhs.flatNumber).append(gov, rhs.gov).append(blockNumber, rhs.blockNumber).append(buildingNumber, rhs.buildingNumber).isEquals();
    }

}
