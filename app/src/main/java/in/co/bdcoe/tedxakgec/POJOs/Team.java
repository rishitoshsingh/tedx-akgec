
package in.co.bdcoe.tedxakgec.POJOs;

import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("createdAt")
    private String mCreatedAt;
    @SerializedName("designation")
    private String mDesignation;
    @SerializedName("id")
    private Long mId;
    @SerializedName("imgurl")
    private String mImgurl;
    @SerializedName("name")
    private String mName;
    @SerializedName("updatedAt")
    private String mUpdatedAt;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDesignation() {
        return mDesignation;
    }

    public void setDesignation(String designation) {
        mDesignation = designation;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImgurl() {
        return mImgurl;
    }

    public void setImgurl(String imgurl) {
        mImgurl = imgurl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
