
package in.co.bdcoe.tedxakgec.POJOs;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("aboutUs")
    private List<AboutUs> mAboutUs;
    @SerializedName("eventDate")
    private Long mEventDate;
    @SerializedName("speakers")
    private List<Speaker> mSpeakers;
    @SerializedName("team")
    private List<Team> mTeam;

    public List<AboutUs> getAboutUs() {
        return mAboutUs;
    }

    public void setAboutUs(List<AboutUs> aboutUs) {
        mAboutUs = aboutUs;
    }

    public Long getEventDate() {
        return mEventDate;
    }

    public void setEventDate(Long eventDate) {
        mEventDate = eventDate;
    }

    public List<Speaker> getSpeakers() {
        return mSpeakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        mSpeakers = speakers;
    }

    public List<Team> getTeam() {
        return mTeam;
    }

    public void setTeam(List<Team> team) {
        mTeam = team;
    }

}
