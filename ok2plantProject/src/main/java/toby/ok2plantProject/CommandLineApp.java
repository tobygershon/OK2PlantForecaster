package toby.ok2plantProject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import toby.ok2plantProject.Dao.UsersDao;
import toby.ok2plantProject.Dao.jdbc.JdbcUsersDao;
import toby.ok2plantProject.Service.*;
import toby.ok2plantProject.Service.Models.LastFrostDate;
import toby.ok2plantProject.Service.Models.ZipToLatLong;
import toby.ok2plantProject.classes.Location;
import toby.ok2plantProject.classes.UserInterface;

import java.io.Console;
import java.io.File;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class CommandLineApp implements CommandLineRunner {

private final ConsoleService consoleService = new ConsoleService();
private final LastFrostDateService lastFrostDateService = new LastFrostDateService();
private final UpcomingForecastService upcomingForecastService = new UpcomingForecastService();
private final WeatherStationService weatherStationService = new WeatherStationService();
private final UsersDao usersDao;

public CommandLineApp(UsersDao userDao) {
    this.usersDao = userDao;
}

    public static void main(String[] args) {


        SpringApplication.run(CommandLineApp.class, args);
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
                String emailInput = consoleService.printEmailInput();
//                usersDao.createUser(emailInput);   commented out due to change in controller for front end

            } else if (mainMenuSelection == 2) {
                //print last frost date
                consoleService.printLastFrostDate(buildUserLocation(consoleService.promptForZipCode()));

            } else if (mainMenuSelection == 3) {
                //print Weather Forecast
                consoleService.printWeatherForecast(buildUserLocation(consoleService.promptForZipCode()));

            } else if (mainMenuSelection == 4) {
                System.exit(1);
            }

            consoleService.pause();
        }

    }

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


    @Override
    public void run(String... args) throws Exception {
        run();
    }
}
