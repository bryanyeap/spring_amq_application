package AssetClassExample;

import java.util.Date;

public class AssetClass {
    private int id;
    private String date;
    private String location;
    private String description;
    private String reportedBy;

    public AssetClass(int id, String date, String location, String description, String reportedBy) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.description = description;
        this.reportedBy = reportedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    @Override
    public String toString() {
        return "AssetExample: [id=" + getId() + ", date:" + getDate() + ", location:" + getLocation() + ", Reported By:" + getReportedBy() + "]";
    }
}

