package toby.ok2plantProject.Service.Models;

public class UpcomingForecast {
    private Day[] days;

    public UpcomingForecast() {
    }

    public UpcomingForecast(Day[] forecast) {
        this.days = forecast;
    }

    public Day[] getDays() {
        return days;
    }

    public void setDays(Day[] forecast) {
        this.days = forecast;
    }


}
