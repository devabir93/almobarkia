
package com.selsela.example.data.model.order;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Status implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("color_hex")
    @Expose
    private String colorHex;
    @SerializedName("color_r")
    @Expose
    private Integer colorR;
    @SerializedName("color_g")
    @Expose
    private Integer colorG;
    @SerializedName("color_b")
    @Expose
    private Integer colorB;
    @SerializedName("has_action")
    @Expose
    private Integer hasAction;
    @SerializedName("action_id")
    @Expose
    private Integer actionId;
    @SerializedName("btn_text")
    @Expose
    private String btnText;
    @SerializedName("btn_color")
    @Expose
    private String btnColor;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<Status> CREATOR = new Creator<Status>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        public Status[] newArray(int size) {
            return (new Status[size]);
        }

    }
    ;

    protected Status(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.colorHex = ((String) in.readValue((String.class.getClassLoader())));
        this.colorR = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.colorG = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.colorB = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.hasAction = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.actionId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.btnText = ((String) in.readValue((String.class.getClassLoader())));
        this.btnColor = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Status() {
    }

    /**
     * 
     * @param btnText
     * @param id
     * @param colorG
     * @param name
     * @param colorR
     * @param actionId
     * @param hasAction
     * @param colorB
     * @param btnColor
     * @param colorHex
     */
    public Status(Integer id, String colorHex, Integer colorR, Integer colorG, Integer colorB, Integer hasAction, Integer actionId, String btnText, String btnColor, String name) {
        super();
        this.id = id;
        this.colorHex = colorHex;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
        this.hasAction = hasAction;
        this.actionId = actionId;
        this.btnText = btnText;
        this.btnColor = btnColor;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public Integer getColorR() {
        return colorR;
    }

    public void setColorR(Integer colorR) {
        this.colorR = colorR;
    }

    public Integer getColorG() {
        return colorG;
    }

    public void setColorG(Integer colorG) {
        this.colorG = colorG;
    }

    public Integer getColorB() {
        return colorB;
    }

    public void setColorB(Integer colorB) {
        this.colorB = colorB;
    }

    public Integer getHasAction() {
        return hasAction;
    }

    public void setHasAction(Integer hasAction) {
        this.hasAction = hasAction;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public String getBtnColor() {
        return btnColor;
    }

    public void setBtnColor(String btnColor) {
        this.btnColor = btnColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("colorHex", colorHex).append("colorR", colorR).append("colorG", colorG).append("colorB", colorB).append("hasAction", hasAction).append("actionId", actionId).append("btnText", btnText).append("btnColor", btnColor).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(btnText).append(id).append(colorG).append(name).append(colorR).append(actionId).append(hasAction).append(colorB).append(btnColor).append(colorHex).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Status) == false) {
            return false;
        }
        Status rhs = ((Status) other);
        return new EqualsBuilder().append(btnText, rhs.btnText).append(id, rhs.id).append(colorG, rhs.colorG).append(name, rhs.name).append(colorR, rhs.colorR).append(actionId, rhs.actionId).append(hasAction, rhs.hasAction).append(colorB, rhs.colorB).append(btnColor, rhs.btnColor).append(colorHex, rhs.colorHex).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(colorHex);
        dest.writeValue(colorR);
        dest.writeValue(colorG);
        dest.writeValue(colorB);
        dest.writeValue(hasAction);
        dest.writeValue(actionId);
        dest.writeValue(btnText);
        dest.writeValue(btnColor);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
