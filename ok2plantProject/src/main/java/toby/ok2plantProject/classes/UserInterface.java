package toby.ok2plantProject.classes;

import toby.ok2plantProject.Service.Models.Day;
import toby.ok2plantProject.Service.Models.UpcomingForecast;
import toby.ok2plantProject.Service.LastFrostDateService;
import toby.ok2plantProject.Service.UpcomingForecastService;
import toby.ok2plantProject.classes.Location;

import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    private final UpcomingForecastService upcomingForecastService = new UpcomingForecastService();
    private final LastFrostDateService lastFrostDateService = new LastFrostDateService();

    public void mainMenu() {
        boolean returnToMain = true;


//        System.out.println();
//        System.out.println("**************************************");
//        System.out.println("Welcome to the OK 2 Plant Forecaster!");
//        System.out.println("**************************************");
//
//        do {  // do-while loops back to main menu unless returnToMain is set to false
//            // print main menu
//
//            System.out.println();
//            System.out.println("(1) Get your forecast");
//            System.out.println("(2) See plant information");
//            System.out.println("(3) Look up your location's regional information");
//            System.out.println("(4) Exit");
//            System.out.println();
//            System.out.println("Please choose a number");
//
//            //get user's choice
//            Scanner scanner = new Scanner(System.in);
//            String userInput = scanner.nextLine();
//
//            if (userInput.equals("1")) {
//                forecastInputScreen();
//            } else if (userInput.equals("2")) {
//
//            } else if (userInput.equals("3")) {
//                UpcomingForecast newForecast = upcomingForecastService.getForecast("15217");
//                printWeatherForecast(newForecast.getDays());
//
//            } else if (userInput.equals("4")) {
//                returnToMain = false;
//            }
//
//        } while (returnToMain);
//    }
//
//    public void forecastInputScreen() {
//
//        System.out.println("**************************************");
//        System.out.println("***** Please enter your zip code *****");
//        System.out.println("**************************************");
//        System.out.println();
//
////        // imagine map is built as below
////        main.src.src.Location location = new main.src.src.Location("15217", 20, 4);
////        Map<String, main.src.src.Location> regionMap = new HashMap<>();
////        regionMap.put("15217", location);
////
////        //above is fictional map for testing//
//
//        Scanner scanner = new Scanner(System.in);
//        String zipInput = scanner.nextLine();
//
//        // construct location object
//        Location newLocation = Location.getRegionMap().get(zipInput);
//        // input user location and output if prediction can be made based on date/region
//
//        // call forecasting algorithm for all types of plants
//        if (newLocation.isTimeToPredictLeastColdHearty(Location.getRegionMap().get(zipInput))) {
//
//            //call forecaster
//
//        } else if (newLocation.isTimeToPredictMostColdHearty(Location.getRegionMap().get(zipInput))) {
//            // call forecasting algorithm for medium and most cold hearty plants
//
//            System.out.println();
//            System.out.println("It's around the time to plant cold hearty vegetables!");
//            System.out.println();
//            System.out.println("We'll get your forecast in a second");
//            System.out.println();
//            System.out.println("However, it's too early to accurately forecast a good planting time for summer crops");
//            System.out.println();
//            System.out.println("We can send you an email reminder when the forecaster will be accurate for summer crops!");
//            System.out.println();
//
//            getEmailReminderScreen(Location.getRegionMap().get(zipInput));
//
//            // next call forecaster
//
//        } else {
//
//            System.out.println();
//            System.out.println("Sorry, it's too early to get an accurate forecast");
//            System.out.println();
//            System.out.println("Can we send you an email reminder when the forecaster will be ready for your region?");
//            System.out.println();
//
//            getEmailReminderScreen(Location.getRegionMap().get(zipInput));
//
//        }
//    }
//
//
//        //Below is case that it's too early to predict and user can input email to receive reminder
//
//public void getEmailReminderScreen(Location location) {
//
//            Scanner scanner = new Scanner(System.in);
//
//            boolean loop = true;
//            do {
//                System.out.println("Would you like to enter your email?");
//                System.out.println("(Y/N)");
//
//                String input = scanner.nextLine().toLowerCase(Locale.ROOT);
//
//                if (input.equals("y")) {
//                    System.out.println("Please enter your email");
//                    String userEmail = scanner.nextLine();
//                    //need to store userEmail along with location (input for method) info to determine when to send reminder
//
//                    System.out.println("Thank you!  We'll let you know when it's time for your forecast");
//                    loop = false;
//                } else if (input.equals("n")) {
//                    loop = false;
//                } else {
//                    System.out.println();
//                    System.out.println("Please enter a Y or N");
//                }
//            } while (loop);
//        }
//
//    public void printWeatherForecast(Day[] days) {
//        for (int i = 0; i < days.length; i++) {
//            System.out.println("Day " + i + " high is: " + days[i].getTempmax());
//            System.out.println("Day " + i + " low is: " + days[i].getTempmin());
//            System.out.println("Day " + i + " chance of rain is: " + days[i].getPrecipprob());
//        }
//        System.out.println(lastFrostDateService.getLastFrostDate(365573));
        }


    }


