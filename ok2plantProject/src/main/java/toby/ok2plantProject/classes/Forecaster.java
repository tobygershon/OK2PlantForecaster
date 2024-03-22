package toby.ok2plantProject.classes;

import toby.ok2plantProject.Service.Models.Day;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Forecaster {

    public static final int RELIABLE_FORECAST_DAYS = 14;
    public static final int MOST_RELIABLE_FORECAST_DAYS = 3;
    public static final int MEDIUM_RELIABLE_FORECAST_DAYS = 7;
    public static final int LEAST_RELIABLE_FORECAST_DAYS = 14;
    public static final int TEMP_DELTA_MOST_RELIABLE = 2;
    public static final int TEMP_DELTA_MEDIUM_RELIABLE = 5;

    public static final int TEMP_DELTA_LEAST_RELIABLE = 9;
    public final int PRECIP_MOST_RELIABLE_CUTOFF = 35;
    public final int PRECIP_MEDIUM_RELIABLE_CUTOFF = 45;
    public final int PRECIP_LEAST_RELIABLE_CUTOFF = 55;


    public int getMOST_RELIABLE_FORECAST_DAYS() {
        return MOST_RELIABLE_FORECAST_DAYS;
    }

    public int getMEDIUM_RELIABLE_FORECAST_DAYS() {
        return MEDIUM_RELIABLE_FORECAST_DAYS;
    }

    public int getLEAST_RELIABLE_FORECAST_DAYS() {
        return LEAST_RELIABLE_FORECAST_DAYS;
    }

    //methods


    // 3 methods to get index of first ok day for Most, Medium , and least tolerant
    private int getFirstOkDayMostTolerant(Location newLocation) {
        //get worst case low list

        List<Double> worstCaseLows = newLocation.getWorstCaseLowsList();

        int firstOkDayMostTolerant = 0;

        //find index of first ok day

        for (int i = worstCaseLows.size() - 1; i >= 0; i--) {
            if (worstCaseLows.get(i) < Plant.MOST_COLD_HEARTY_TEMP_TOLERANCE) {
                firstOkDayMostTolerant = i + 1;
                break;
            }
        }

        return firstOkDayMostTolerant;
    }

    private int getFirstOkDayMediumTolerant(Location newLocation) {
        //get worst case low list
        List<Double> worstCaseLows = newLocation.getWorstCaseLowsList();

        int firstOkDayMediumTolerant = 0;

        //find index of first ok day

        for (int i = worstCaseLows.size() - 1; i >= 0; i--) {
            if (worstCaseLows.get(i) < Plant.MEDIUM_COLD_HEARTY_TEMP_TOLERANCE) {
                firstOkDayMediumTolerant = i + 1;
                break;
            }
        }

        return firstOkDayMediumTolerant;
    }

    private int getFirstOkDayLeastTolerant(Location newLocation) {
        //get worst case low list
        List<Double> worstCaseLows = newLocation.getWorstCaseLowsList();

        int firstOkDayLeastTolerant = 0;

        //find index of first ok day

        for (int i = worstCaseLows.size() - 1; i >= 0; i--) {
            if (worstCaseLows.get(i) < Plant.LEAST_COLD_HEARTY_TEMP_TOLERANCE) {
                firstOkDayLeastTolerant = i + 1;
                break;
            }
        }

        return firstOkDayLeastTolerant;
    }

    private void getForecastMostTolerant(Location newLocation) {

        if (getFirstOkDayMediumTolerant(newLocation) == 14) {
            if (getFirstOkDayMostTolerant(newLocation) == 0) {
                System.out.println();
                System.out.println("For the Most Cold Tolerant Plants, the forecast looks great going forward!");
                System.out.println();

                //check rain
                int dayToPlant = getBestPlantingDayWithRainForecast(getFirstOkDayLeastTolerant(newLocation), newLocation);
                if (dayToPlant == -1) {
                    System.out.println("The Rain forecast isn't looking great coming up");
                    System.out.println("You'll need to keep your seeds or plants watered daily!");
                } else {
                    System.out.println("The best day to plant looks like " + LocalDate.now().plusDays(dayToPlant));
                    System.out.println("The rainy forecast after " + LocalDate.now().plusDays(dayToPlant) + " should help keep the soil moist");
                }

//            create method to check high temps going forward for cold tolerant plants that may bolt if temps are too high
            } else if (getFirstOkDayMostTolerant(newLocation) == 14) {
                System.out.println();
                System.out.println("The forecast is still too cold even for the Most Cold Tolerant crops :( ");
                System.out.println();
                System.out.println("Check back in a few days to see if the temperatures will be more favorable soon");
//            ask user if they want email reminder when it looks better
            } else {
                int ok2PlantDay = getFirstOkDayMostTolerant(newLocation);
                System.out.println();
                System.out.print("It will be OK2Plant the most cold tolerant crops starting on " + LocalDate.now().plusDays(ok2PlantDay) + "!");
                // get precipitation forecast to recommend a day before or during rainy spell
                int dayToPlant = getBestPlantingDayWithRainForecast(getFirstOkDayLeastTolerant(newLocation), newLocation);
                if (dayToPlant == -1) {
                    System.out.println("The Rain forecast isn't looking great coming up");
                    System.out.println("You'll need to keep your seeds or plants watered daily!");
                } else {
                    System.out.println("The best day to plant looks like " + LocalDate.now().plusDays(dayToPlant));
                    System.out.println("The rainy forecast after " + LocalDate.now().plusDays(dayToPlant) + " should help keep the soil moist");
                    if (isRainy(dayToPlant, newLocation)) {
                        System.out.println("But " + LocalDate.now().plusDays(dayToPlant) + " looks rainy");
                    }
                }
            }
        }
    }

    private void getForecastMediumTolerant(Location newLocation) {
        if (getFirstOkDayLeastTolerant(newLocation) == 14) {
            if (getFirstOkDayMediumTolerant(newLocation) == 0) {
                System.out.println();
                System.out.println("For Medium Tolerant Plants, the forecast looks great going forward!");
                System.out.println();
//
                // rain forecast

                int dayToPlant = getBestPlantingDayWithRainForecast(getFirstOkDayLeastTolerant(newLocation), newLocation);
                if (dayToPlant == -1) {
                    System.out.println("The Rain forecast isn't looking great coming up");
                    System.out.println("You'll need to keep your seeds or plants watered daily!");
                } else {
                    System.out.println("The best day to plant looks like " + LocalDate.now().plusDays(dayToPlant));
                    System.out.println("The rainy forecast after " + LocalDate.now().plusDays(dayToPlant) + " should help keep the soil moist");
                }
            } else if (getFirstOkDayMediumTolerant(newLocation) == 14) {
                System.out.println();
                System.out.println("The forecast isn't looking good for Medium Tolerant crops :( ");
                System.out.println();
                System.out.println("Check back in a few days to see if the temperatures will be more favorable soon");
//            ask user if they want email reminder when it looks better
            } else {
                int ok2PlantDay = getFirstOkDayMediumTolerant(newLocation);
                System.out.println();
                System.out.print("It will be OK2Plant medium tolerant crops starting on " + LocalDate.now().plusDays(ok2PlantDay) + "!");
                // get precipitation forecast to recommend a day before or during rainy spell
                int dayToPlant = getBestPlantingDayWithRainForecast(getFirstOkDayLeastTolerant(newLocation), newLocation);
                if (dayToPlant == -1) {
                    System.out.println("The Rain forecast isn't looking great coming up");
                    System.out.println("You'll need to keep your seeds or plants watered daily!");
                } else {
                    System.out.println("The best day to plant looks like " + LocalDate.now().plusDays(dayToPlant));
                    System.out.println("The rainy forecast after " + LocalDate.now().plusDays(dayToPlant) + " should help keep the soil moist");
                    if (isRainy(dayToPlant, newLocation)) {
                        System.out.println("But " + LocalDate.now().plusDays(dayToPlant) + " looks rainy");
                    }
                }
            }
        }
    }

    private void getForecastLeastTolerant(Location newLocation) {
        if (getFirstOkDayLeastTolerant(newLocation) == 0) {
            System.out.println();
            System.out.println("The forecast looks great going forward!  You can plant all summer crops!");
            System.out.println();

//            method to check high temps going forward for cold tolerant plants that may bolt if temps are too high

            if (checkIfTooHotForMostColdTolerant(newLocation)) {
                System.out.println("However, the forecast is looking too hot for the most cold tolerant plants to thrive");
                System.out.println();
            }

            // method to check rain forecast and give best planting day

            int dayToPlant = getBestPlantingDayWithRainForecast(getFirstOkDayLeastTolerant(newLocation), newLocation);
            if (dayToPlant == -1) {
                System.out.println("The Rain forecast isn't looking great coming up");
                System.out.println("If you plant soon, you'll need to keep your seeds or plants watered daily!");
            } else {
                System.out.println("The best day to plant looks like " + LocalDate.now().plusDays(dayToPlant));
                System.out.println("There's rain in the forecast after " + LocalDate.now().plusDays(dayToPlant) + " which should help keep the soil moist");
                if (isRainy(dayToPlant, newLocation)) {
                    System.out.println("But " + LocalDate.now().plusDays(dayToPlant) + " looks rainy");
                }
            }
        } else if (getFirstOkDayLeastTolerant(newLocation) == 14) {
            System.out.println("The forecast isn't looking good for summer crops :( ");
            System.out.println();
            System.out.println("Check back in a few days to see if the temperatures will be more favorable soon");
//            ask user if they want email reminder when it looks better
        } else {
            int ok2PlantDay = getFirstOkDayLeastTolerant(newLocation);
            System.out.println();
            System.out.print("It will be OK2Plant summer crops starting on " + LocalDate.now().plusDays(ok2PlantDay) + "!");
            System.out.println();

            if (checkIfTooHotForMostColdTolerant(newLocation)) {
                System.out.println();
                System.out.println("However, the forecast after that is looking too hot for the most cold tolerant plants to thrive");
                System.out.println();
            }
            // get precipitation forecast to recommend a day before or during rainy spell
            int dayToPlant = getBestPlantingDayWithRainForecast(getFirstOkDayLeastTolerant(newLocation), newLocation);
            if (dayToPlant == -1) {
                System.out.println("The forecast isn't predicting much rain as of now");
                System.out.println("Make sure to keep your seeds or plants watered daily!");
            } else {
                System.out.println("The best day to plant looks like " + LocalDate.now().plusDays(dayToPlant));
                System.out.println("The rainy forecast after " + LocalDate.now().plusDays(dayToPlant) + " should help keep the soil moist");
                }
            }
        }

        private boolean isRainy(int day, Location location) {
            boolean isRainy = false;

            if ( day < MOST_RELIABLE_FORECAST_DAYS && location.getWeatherForecastList().getDays()[day].getPrecipprob() > PRECIP_MOST_RELIABLE_CUTOFF) {
                isRainy = true;
            } else if (day < MEDIUM_RELIABLE_FORECAST_DAYS && location.getWeatherForecastList().getDays()[day].getPrecipprob() > PRECIP_MEDIUM_RELIABLE_CUTOFF) {
                isRainy = true;
            } else if (day < LEAST_RELIABLE_FORECAST_DAYS && location.getWeatherForecastList().getDays()[day].getPrecipprob() > PRECIP_LEAST_RELIABLE_CUTOFF) {
                isRainy = true;
            }

            return isRainy;
        }


    private int getBestPlantingDayWithRainForecast(int firstOkDay, Location location) {//input is int output from getFirstOkDay method.  Used for range of iterating through forecast.

        for (int i = firstOkDay; i < location.getWeatherForecastList().getDays().length - 3; i++) {
                //  get avg chance of precip over 3 day stretches, and if over precip cutoff, recommend i as good planting day due to precip in forecast
                double day1Chance = location.getWeatherForecastList().getDays()[i + 1].getPrecipprob();
                double day2Chance = location.getWeatherForecastList().getDays()[i + 2].getPrecipprob();
                double day3Chance = location.getWeatherForecastList().getDays()[i + 3].getPrecipprob();
                double avg3DayChance = (day1Chance + day2Chance + day3Chance) / 3;

                if (i < MOST_RELIABLE_FORECAST_DAYS) {
                    if (avg3DayChance > PRECIP_MOST_RELIABLE_CUTOFF) {
                        return i;      // i is index of day before precip and after ok2plant day
                    }
                } else if (i < MEDIUM_RELIABLE_FORECAST_DAYS) {
                    if (avg3DayChance > PRECIP_MEDIUM_RELIABLE_CUTOFF) {
                        return i;
                    }
                } else if (i < LEAST_RELIABLE_FORECAST_DAYS) {
                    if (avg3DayChance > PRECIP_LEAST_RELIABLE_CUTOFF) {
                        return i;
                    }
                }
        }
        return -1;
    }


    private boolean checkIfTooHotForMostColdTolerant(Location newLocation) {
        boolean isTooHot = false;

        for (int i = 0; i < newLocation.getWeatherForecastList().getDays().length - 2; i++) {
            double day1 = newLocation.getWeatherForecastList().getDays()[i].getTempmax();
            double day2 = newLocation.getWeatherForecastList().getDays()[i + 1].getTempmax();
            double day3 = newLocation.getWeatherForecastList().getDays()[i + 2].getTempmax();

            double threeDayAvgHigh = (day1 + day2 + day3) / 3;

            if (threeDayAvgHigh > 77.5) {
                isTooHot = true;
                break;
            }
        }

        for (int i = 0; i < newLocation.getWeatherForecastList().getDays().length - 4; i++) {
            double day1 = newLocation.getWeatherForecastList().getDays()[i].getTempmax();
            double day2 = newLocation.getWeatherForecastList().getDays()[i + 1].getTempmax();
            double day3 = newLocation.getWeatherForecastList().getDays()[i + 2].getTempmax();
            double day4 = newLocation.getWeatherForecastList().getDays()[i + 3].getTempmax();
            double day5 = newLocation.getWeatherForecastList().getDays()[i + 4].getTempmax();

            double fiveDayAvgHigh = (day1 + day2 + day3 + day4 + day5) / 5;

            if (fiveDayAvgHigh >= 72.5) {
                isTooHot = true;
                break;
            }
        }

        return isTooHot;
    }


    // if isTimeToPredict = true
    //
    public void getForecast(Location newLocation) {
//       take in location, calculate worst case low temps, find ideal day

        //       if least tolerant is ok to plant (i.e. first day ok index is found to be -1 meaning all
//       days in worst case scenario were greater than least hearty tolerance)
//       then, declare that the forecast looks fine to plant anything! (except warn that high temps too hot not good for some most cold tolerant)

        getForecastLeastTolerant(newLocation);
        getForecastMediumTolerant(newLocation);
        getForecastMostTolerant(newLocation);

//        add separate methods for precip forecast?


//        return day after which it's ok to plant
//        return any warnings to watch out for

//        take in precip forecast
//        calculate reliability
//        return best day based on precip forecast after ok2plant day

    }

    //
}
