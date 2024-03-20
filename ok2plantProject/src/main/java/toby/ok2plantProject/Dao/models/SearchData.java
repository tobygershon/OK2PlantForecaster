package toby.ok2plantProject.Dao.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SearchData {

    private int searchId;
    private int userId;
    private String zip;
    private boolean requestFollowUp;
    private LocalDate searchDate;
    private LocalDate ok2PlantDate;

    public SearchData() {
    }

    public SearchData(int searchId, int userId, String zip, boolean requestFollowUp, LocalDate searchDate, LocalDate ok2PlantDate) {
        this.searchId = searchId;
        this.userId = userId;
        this.zip = zip;
        this.requestFollowUp = requestFollowUp;
        this.searchDate = searchDate;
        this.ok2PlantDate = ok2PlantDate;
    }

    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean isRequestFollowUp() {
        return requestFollowUp;
    }

    public void setRequestFollowUp(boolean requestFollowUp) {
        this.requestFollowUp = requestFollowUp;
    }

    public LocalDate getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDate searchDate) {
        this.searchDate = searchDate;
    }

    public LocalDate getOk2PlantDate() {
        return ok2PlantDate;
    }

    public void setOk2PlantDate(LocalDate ok2PlantDate) {
        this.ok2PlantDate = ok2PlantDate;
    }
}
