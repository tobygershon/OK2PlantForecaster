package toby.ok2plantProject.FrontEnd.DTOModels;

import toby.ok2plantProject.Service.Models.UpcomingForecast;

import java.time.LocalDate;

public class ForecastResponseDTO {

    private String ok2PlantDate;
    private String lastFrostDate;
    private String message;

    public ForecastResponseDTO() {
    }

    public ForecastResponseDTO(String ok2PlantDate) {
        this.ok2PlantDate = ok2PlantDate;
    }

    public ForecastResponseDTO(String ok2PlantDate, String lastFrostDate) {
        this.ok2PlantDate = ok2PlantDate;
        this.lastFrostDate = lastFrostDate;
    }

    public ForecastResponseDTO(String ok2PlantDate, String lastFrostDate, String message) {
        this.ok2PlantDate = ok2PlantDate;
        this.lastFrostDate = lastFrostDate;
        this.message = message;
    }

    public String getOk2PlantDate() {
        return ok2PlantDate;
    }

    public void setOk2PlantDate(String ok2PlantDate) {
        this.ok2PlantDate = ok2PlantDate;
    }

    public String getLastFrostDate() {
        return lastFrostDate;
    }

    public void setLastFrostDate(String lastFrostDate) {
        this.lastFrostDate = lastFrostDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
