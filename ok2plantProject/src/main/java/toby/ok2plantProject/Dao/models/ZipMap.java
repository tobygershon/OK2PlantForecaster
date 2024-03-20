package toby.ok2plantProject.Dao.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ZipMap {

    private String zip;
    private Date lastFrostDate;
    private Map<String, Date> zipMap = new HashMap<>();

    public ZipMap() {
    }

    public ZipMap(String zip, Date lastFrostDate) {
        this.zip = zip;
        this.lastFrostDate = lastFrostDate;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Date getLastFrostDate() {
        return lastFrostDate;
    }

    public void setLastFrostDate(Date lastFrostDate) {
        this.lastFrostDate = lastFrostDate;
    }

    public Map<String, Date> getZipMap() {
        return zipMap;
    }

    public void setZipMap(Map<String, Date> zipMap) {
        this.zipMap = zipMap;
    }
}
