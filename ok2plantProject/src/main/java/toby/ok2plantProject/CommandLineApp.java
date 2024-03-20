package toby.ok2plantProject;

import toby.ok2plantProject.Service.*;
import toby.ok2plantProject.Service.Models.LastFrostDate;
import toby.ok2plantProject.Service.Models.ZipToLatLong;
import toby.ok2plantProject.classes.Location;
import toby.ok2plantProject.classes.UserInterface;

import java.io.Console;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandLineApp {

private final ConsoleService consoleService = new ConsoleService();
private final LastFrostDateService lastFrostDateService = new LastFrostDateService();
private final UpcomingForecastService upcomingForecastService = new UpcomingForecastService();
private final WeatherStationService weatherStationService = new WeatherStationService();

    public static void main(String[] args) {


        //Option 1: Build locations and regionMap from Database

//        Map<String, Location> regionMap = new HashMap<>();
//
        //Option 2: build from reading in csv file
//        File newFile = new File("frost-data - Sheet1.csv");
//        Location.buildLocationAndRegionMap(newFile);

        //Option 3:  Call web API's to get first-frost dates for input zip codes


                // Disable logging for RestTemplate
                Logger.getLogger("org.springframework.web.client.RestTemplate").setLevel(Level.OFF);

              // run App

        CommandLineApp newApp = new CommandLineApp();
        newApp.run();
    }

    private void run() {

        int mainMenuSelection = -1;

        consoleService.printHeader();

        while(mainMenuSelection != 0) {
            consoleService.printMainMenu();
            mainMenuSelection = consoleService.promptForMenuSelection();

            if (mainMenuSelection == 1) {
                //call forecaster
                consoleService.printOK2PlantForecast(buildUserLocation(consoleService.promptForZipCode()));

            } else if (mainMenuSelection == 2) {
                //print last frost date
                consoleService.printLastFrostDate(buildUserLocation(consoleService.promptForZipCode()));

            } else if (mainMenuSelection == 3) {
                //print Weather Forecast
                consoleService.printWeatherForecast(buildUserLocation(consoleService.promptForZipCode()));

            } else if (mainMenuSelection == 4) {
                System.exit(1);
            }
        }

    }

    private Location buildUserLocation(String zip) {
        Location userLocation = new Location();

        //set zip
        userLocation.setZip(zip);

        //set last frost date
        ZipToLatLong location = upcomingForecastService.getLatAndLongFromZip(zip);
        int station = weatherStationService.getStation(location.getLatitude(), location.getLongitude());
        userLocation.setAvgLastFrostDate(lastFrostDateService.getLastFrostDate(station));

        //set forecast
        userLocation.setWeatherForecastList(upcomingForecastService.getForecast(zip));

        return userLocation;
    }



}
