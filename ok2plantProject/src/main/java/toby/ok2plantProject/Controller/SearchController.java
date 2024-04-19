package toby.ok2plantProject.Controller;

import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import toby.ok2plantProject.Dao.UsersDao;
import toby.ok2plantProject.Dao.jdbc.JdbcUsersDao;
import toby.ok2plantProject.Exceptions.DaoException;
import toby.ok2plantProject.FrontEnd.DTOModels.ForecastResponseDTO;
import toby.ok2plantProject.FrontEnd.DTOModels.NewUserDTO;
import toby.ok2plantProject.Service.LastFrostDateService;
import toby.ok2plantProject.Service.Models.ZipToLatLong;
import toby.ok2plantProject.Service.UpcomingForecastService;
import toby.ok2plantProject.Service.WeatherStationService;
import toby.ok2plantProject.classes.Forecaster;
import toby.ok2plantProject.classes.Location;

import java.time.LocalDate;
import java.time.Month;

@RestController
@CrossOrigin
public class SearchController {

    private final Forecaster forecaster = new Forecaster();
    private final LastFrostDateService lastFrostDateService = new LastFrostDateService();
    private final UpcomingForecastService upcomingForecastService = new UpcomingForecastService();
    private final WeatherStationService weatherStationService = new WeatherStationService();
    private final UsersDao usersDao;

    public SearchController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @GetMapping("/OK2PlantDate/{zip}")
    public ForecastResponseDTO getOK2PlantDate(@PathVariable String zip) {

        Location newLocation = buildUserLocation(zip);
//
        //return ok2PlantDate
        return forecaster.getForecastFrontEnd(newLocation);
    }

    @PostMapping("/EmailRequest")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @NonNull NewUserDTO newUserDTO) {

        try {
            int response = usersDao.createUser(newUserDTO.getEmail(), newUserDTO.getOk2PlantDate(), newUserDTO.isWantsFollowUp());

//            if (response != 1) {
//                throw new DaoException("create user didn't work properly");
//            }
        } catch (DaoException e) {
            System.out.println("error");
        }catch (CannotGetJdbcConnectionException e) {
            System.out.println("No connection");
        }

    }





    //method to build user location based on zip input
    private Location buildUserLocation(String zip) {
        Location userLocation = new Location();
        LocalDate pittsburgh = LocalDate.now().plusDays(6);
        LocalDate bc = LocalDate.now().minusDays(4);
        LocalDate Atlanta = LocalDate.now().minusDays(28);
        LocalDate Fargo = LocalDate.now().plusDays(21);

        //set zip
        userLocation.setZip(zip);

        //set last frost date
//        ZipToLatLong location = upcomingForecastService.getLatAndLongFromZip(zip);
//        int station = weatherStationService.getStation(location.getLatitude(), location.getLongitude());
//        userLocation.setAvgLastFrostDate(lastFrostDateService.getLastFrostDate(station));

        if (zip.equals("15217")) {
            userLocation.setAvgLastFrostDate(pittsburgh);
        } else if (zip.equals("19004")) {
            userLocation.setAvgLastFrostDate(bc);
        } else if (zip.equals("30033")) {
            userLocation.setAvgLastFrostDate(Atlanta);
        } else if (zip.equals("58102")) {
            userLocation.setAvgLastFrostDate(Fargo);
        } else {
            userLocation.setAvgLastFrostDate(LocalDate.now());
        }
        //set forecast
        userLocation.setWeatherForecastList(upcomingForecastService.getForecast(zip));

        //calculate List of worstCaseLows

        userLocation.getWorstCaseLowsList();

        return userLocation;
    }
}
