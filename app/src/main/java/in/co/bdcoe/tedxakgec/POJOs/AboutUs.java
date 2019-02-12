
package in.co.bdcoe.tedxakgec.POJOs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AboutUs implements Serializable {

    @SerializedName("createdAt")
    private String mCreatedAt;
    @SerializedName("footer")
    private String mFooter;
    @SerializedName("id")
    private Long mId;
    @SerializedName("ted")
    private String mTed;
    @SerializedName("tedx")
    private String mTedx;
    @SerializedName("tedx_akgec")
    private String mTedxAkgec;
    @SerializedName("updatedAt")
    private String mUpdatedAt;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getFooter() {
        return mFooter;
    }

    public void setFooter(String footer) {
        mFooter = footer;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTed() {
        return mTed;
    }

    public void setTed(String ted) {
        mTed = ted;
    }

    public String getTedx() {
        return mTedx;
    }

    public void setTedx(String tedx) {
        mTedx = tedx;
    }

    public String getTedxAkgec() {
        return mTedxAkgec;
    }

    public void setTedxAkgec(String tedxAkgec) {
        mTedxAkgec = tedxAkgec;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
