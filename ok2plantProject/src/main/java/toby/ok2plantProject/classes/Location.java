package toby.ok2plantProject.classes;

import toby.ok2plantProject.Service.Models.Day;
import toby.ok2plantProject.Service.Models.UpcomingForecast;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class Location {
    private String zip;
    private LocalDate avgLastFrostDate;
    private UpcomingForecast weatherForecastList;

    private UpcomingForecast worstCaseForecastList;


    private static Map<String, Location> regionMap = new HashMap<>();  //necessary?

    private void setWorstCaseForecastList(UpcomingForecast weatherForecastList) {
        this.worstCaseForecastList = 
    }
    public List<Day> getWorstCaseLowTemps() {
        UpcomingForecast worstcase = new UpcomingForecast();
        List<Day> worstCaseTempList = new ArrayList<>();

        for (int i = 0; i < Forecaster.MOST_RELIABLE_FORECAST_DAYS; i++) {
            double worstCaseLow = weatherForecastList.getDays()[i].getTempmin() - Forecaster.TEMP_DELTA_MOST_RELIABLE;
            worstCaseTempList.add(weatherForecastList.getDays()[i]);
            worstCaseTempList.get(i).setTempmin(worstCaseLow);
        }
        for (int i = Forecaster.MOST_RELIABLE_FORECAST_DAYS; i < Forecaster.MEDIUM_RELIABLE_FORECAST_DAYS; i++) {
            double worstCaseTemp = weatherForecastList.getDays()[i].getTempmin() - Forecaster.TEMP_DELTA_MEDIUM_RELIABLE;
            worstCaseTempList.add(weatherForecastList.getDays()[i]);
            worstCaseTempList.get(i).setTempmin(worstCaseTemp);
        }
        for (int i = Forecaster.MEDIUM_RELIABLE_FORECAST_DAYS; i < Forecaster.LEAST_RELIABLE_FORECAST_DAYS; i++) {
            double worstCaseTemp = weatherForecastList.getDays()[i].getTempmin() - Forecaster.TEMP_DELTA_LEAST_RELIABLE;
            worstCaseTempList.add(weatherForecastList.getDays()[i]);
            worstCaseTempList.get(i).setTempmin(worstCaseTemp);
        }
        worstcase.setDays(worstCaseTempList);
        return worstCaseTempList;
    }

    public LocalDate getAvgLastFrostDate() {
        return avgLastFrostDate;
    }

    public String getZipString() {
        return zip;
    }

    public UpcomingForecast getWeatherForecastList() {
        return weatherForecastList;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setAvgLastFrostDate(LocalDate avgLastFrostDate) {
        this.avgLastFrostDate = avgLastFrostDate;
    }

    public void setWeatherForecastList(UpcomingForecast weatherForecastList) {
        this.weatherForecastList = weatherForecastList;
    }

    //Constructors

    public Location() {
    }

    public Location(String zip) {
        this.zip = zip;
    }
    public Location(String zip, LocalDate avgLastFrostDate) {
        this.zip = zip;
        this.avgLastFrostDate = avgLastFrostDate;

    }
    public Location(String zip, LocalDate avgLastFrostDay, UpcomingForecast forecast) {
        this.zip = zip;
        this.avgLastFrostDate = avgLastFrostDate;
        this.weatherForecastList = forecast;

    }


    // methods to figure out if prediction is ok based on location dependent dates
    private boolean isTimeToPredictMostColdHearty(Location location) {

        if (LocalDate.now().plusDays(Forecaster.RELIABLE_FORECAST_DAYS + Plant.MOST_COLD_HEARTY_SOWING_TIME).isBefore(LocalDate.of(LocalDate.now().getYear(), location.getAvgLastFrostDate().getMonth(), location.getAvgLastFrostDate().getDayOfMonth()))) {
            return false;
        }
        return true;
    }
    private boolean isTimeToPredictMediumColdHearty(Location location) {
        if (LocalDate.now().plusDays(Forecaster.RELIABLE_FORECAST_DAYS + Plant.MEDIUM_COLD_HEARTY_SOWING_TIME).isBefore(LocalDate.of(LocalDate.now().getYear(), location.getAvgLastFrostDate().getMonth(), location.getAvgLastFrostDate().getDayOfMonth()))) {
            return false;
        }
        return true;
    }
    private boolean isTimeToPredictLeastColdHearty(Location location) {
        if (LocalDate.now().plusDays(Forecaster.RELIABLE_FORECAST_DAYS + Plant.LEAST_COLD_HEARTY_SOWING_TIME).isBefore(LocalDate.of(LocalDate.now().getYear(), location.getAvgLastFrostDate().getMonth(), location.getAvgLastFrostDate().getDayOfMonth()))) {
            return false;
        }
        return true;
    }




    // methods to build Locations from csv and region map
    //fields don't match up in Location constructor due to changes made after mapping API data compared to csv files that were read in

//    public static void buildLocationAndRegionMap(File newFile) {
//        // Build location for each file line read in
//
//        try (Scanner scanner = new Scanner(newFile)) {
//            scanner.nextLine();
//
//            while (scanner.hasNextLine()) {
//                String stringLine = scanner.nextLine();
//                String[] array = stringLine.split(",");
//                Location newLocation = new Location(array[0], Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]));
//
//                regionMap.put(array[0], newLocation);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File was not found");
//        }
//    }



}
