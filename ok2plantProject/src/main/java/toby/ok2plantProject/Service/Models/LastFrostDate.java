package toby.ok2plantProject.Service.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LastFrostDate {
    @JsonProperty("prob_90")
    private String lastFrostDate;

    public LastFrostDate() {
    }

    public String getLastFrostDate() {
        return lastFrostDate;
    }

    public void setLastFrostDate(String lastFrostDate) {
        this.lastFrostDate = lastFrostDate;
    }
}
