package toby.ok2plantProject.Controller;

import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.*;
import toby.ok2plantProject.FrontEnd.DTOModels.ForecastResponseDTO;
import toby.ok2plantProject.Service.LastFrostDateService;
import toby.ok2plantProject.Service.Models.ZipToLatLong;
import toby.ok2plantProject.Service.UpcomingForecastService;
import toby.ok2plantProject.Service.WeatherStationService;
import toby.ok2plantProject.classes.Forecaster;
import toby.ok2plantProject.classes.Location;

import java.time.LocalDate;

@RestController
@CrossOrigin
public class SearchController {

    private final Forecaster forecaster = new Forecaster();
    private final LastFrostDateService lastFrostDateService = new LastFrostDateService();
    private final UpcomingForecastService upcomingForecastService = new UpcomingForecastService();
    private final WeatherStationService weatherStationService = new WeatherStationService();

    @GetMapping("/OK2PlantDate/{zip}")
    public ForecastResponseDTO getOK2PlantDate(@PathVariable String zip) {
        ForecastResponseDTO forecastResponseDTO = new ForecastResponseDTO();

        Location newLocation = buildUserLocation(zip);
//
        int okDay = forecaster.getFirstOkDayLeastTolerant(newLocation);
        LocalDate baseDate = LocalDate.now().plusDays(okDay);

        forecastResponseDTO.setOk2PlantDate(baseDate.getDayOfWeek() + ", " + baseDate.getMonth() + " " + baseDate.getDayOfMonth());

        LocalDate lastFrost = newLocation.getAvgLastFrostDate();

        forecastResponseDTO.setLastFrostDate(lastFrost.getDayOfWeek() + ", " + lastFrost.getMonth() + " " + lastFrost.getDayOfMonth());

        //return ok2PlantDate
        return forecastResponseDTO;
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
