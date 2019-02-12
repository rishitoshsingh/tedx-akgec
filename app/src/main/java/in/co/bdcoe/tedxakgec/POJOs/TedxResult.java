
package in.co.bdcoe.tedxakgec.POJOs;

import com.google.gson.annotations.SerializedName;

public class TedxResult {

    @SerializedName("data")
    private Data mData;
    @SerializedName("success")
    private Boolean mSuccess;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}
