package toby.ok2plantProject.Controller;

import org.springframework.web.bind.annotation.*;
import toby.ok2plantProject.Service.LastFrostDateService;
import toby.ok2plantProject.Service.Models.ZipToLatLong;
import toby.ok2plantProject.Service.UpcomingForecastService;
import toby.ok2plantProject.Service.WeatherStationService;
import toby.ok2plantProject.classes.Forecaster;
import toby.ok2plantProject.classes.Location;

import java.time.LocalDate;

@RestController
@RequestMapping("/html/ok2plant.html/")
@CrossOrigin
public class SearchController {

    private final Forecaster forecaster = new Forecaster();
    private final LastFrostDateService lastFrostDateService = new LastFrostDateService();
    private final UpcomingForecastService upcomingForecastService = new UpcomingForecastService();
    private final WeatherStationService weatherStationService = new WeatherStationService();

    @GetMapping("{zip}/OK2PlantDate")
    public String getOK2PlantDate(@PathVariable String zip) {
        String ok2PlantDate = "";

        Location newLocation = buildUserLocation(zip);

        forecaster.getForecast(newLocation);

        return ok2PlantDate;
    }


    //method to build user location based on zip input
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

        //calculate List of worstCaseLows

        userLocation.getWorstCaseLowsList();

        return userLocation;
    }
}
