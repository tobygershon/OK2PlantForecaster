package toby.ok2plantProject.Service;

import toby.ok2plantProject.Service.Models.Day;
import toby.ok2plantProject.classes.Forecaster;
import toby.ok2plantProject.classes.Location;

import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    // UserInterface methods

    public void printHeader() {
        System.out.println();
        System.out.println("**************************************");
        System.out.println("Welcome to the OK 2 Plant Forecaster!");
        System.out.println("**************************************");
    }
    public void printMainMenu() {
        System.out.println();
        System.out.println("(1) Get your OK 2 Plant Forecast!");
        System.out.println("(2) Look up your location's Last Frost Date");
        System.out.println("(3) Look up your location's 14 Day Weather Forecast");
        System.out.println("(4) Exit");
        System.out.println();
    }

    public int promptForMenuSelection() {
        int userSelection = -1;

        System.out.println();
        System.out.println("Please select an option:");

        try {
            userSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please choose a valid option (1, 2, 3, or 4)");
            System.out.println();
            promptForMenuSelection();
        }

        return userSelection;
    }

    public String promptForZipCode() {

        System.out.println();
        System.out.println("Please enter your five digit Zip Code");
        System.out.println();

        String inputZip = scanner.nextLine();

        if (inputZip.length() != 5) {
            System.out.println();
            System.out.println("Make sure to enter 5 digits");
            promptForZipCode();
        }

        return inputZip;
    }

    public void printOK2PlantForecast(Location newLocation) {
        Forecaster newForecast = new Forecaster();
        newForecast.getForecast(newLocation);
    }

    public void printWeatherForecast(Location newLocation) {
        int forecastDay = 1;
        for (Day day : newLocation.getWeatherForecastList().getDays()) {
            System.out.println("Day " + forecastDay + ":");
            System.out.println("High Temp (F): " + day.getTempmax());
            System.out.println("Low Temp (F): " + day.getTempmin());
            System.out.println("Chance of Precipitation: " + day.getPrecipprob() + "%");
            System.out.println();

            forecastDay += 1;
        }
    }

    public void printLastFrostDate(Location newLocation) {
        System.out.println();
        System.out.println("Your area's average last frost date is " + newLocation.getAvgLastFrostDate().getMonth() + " " + newLocation.getAvgLastFrostDate().getDayOfMonth());
        System.out.println();
    }


}
