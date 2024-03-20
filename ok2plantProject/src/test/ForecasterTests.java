package src.test;

import org.junit.Before;
import src.main.java.Forecaster;
import src.main.java.Location;
import src.main.java.Weather;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ForecasterTests {
    List<Weather> testForecast = new ArrayList<>();
    @Before
    public void setup() {


        Weather day1 = new Weather(59, 44, 0);
        Weather day2 = new Weather(50, 39, 30);
        Weather day3 = new Weather(42, 25, 30);
        Weather day4 = new Weather(43, 32, 40);
        Weather day5 = new Weather(51, 44, 10);
        Weather day6 = new Weather(59,52, 20);
        Weather day7 = new Weather(61, 49, 80);
        Weather day8 = new Weather(67, 55, 60);
        Weather day9 = new Weather(68, 47, 0);
        Weather day10 = new Weather(59, 37, 10);
        Weather day11 = new Weather(58, 48, 50);
        Weather day12 = new Weather(79, 54, 60);
        Weather day13 = new Weather(82, 57, 30);
        Weather day14 = new Weather(83, 56, 40);
        testForecast.add(day1);
        testForecast.add(day2);
        testForecast.add(day3);
        testForecast.add(day4);
        testForecast.add(day5);
        testForecast.add(day6);
        testForecast.add(day7);
        testForecast.add(day8);
        testForecast.add(day9);
        testForecast.add(day10);
        testForecast.add(day11);
        testForecast.add(day12);
        testForecast.add(day13);
        testForecast.add(day14);

        Location testLocation = new Location("15217", 04, 26, testForecast);

    }

    @Test
    public void calculate_worse_case_lows_is_accurate() {
//        Arrange

        Forecaster forecasterTest = new Forecaster();
        Location testLocation = new Location("15217", 04, 26, testForecast);

        //        Act

        List<Weather> worstCaseLows = forecasterTest.calculateWorstCaseLowTemps(testLocation);


//        Assert

        Assert.assertEquals(42, worstCaseLows.get(0).getLowTemp());
        Assert.assertEquals(47, worstCaseLows.get(6).getLowTemp());
        Assert.assertEquals(47, worstCaseLows.get(13).getLowTemp());
    }

    @Test
    public void calculate_worst_case_lows_returns_correct_length_list() {
//        Arrange
        Forecaster forecasterTest = new Forecaster();
        Location testLocation = new Location("15217", 04, 26, testForecast);

//        Act

        List<Weather> sut = forecasterTest.calculateWorstCaseLowTemps(testLocation);

//        Assert
        Assert.assertEquals("incorrect length list returned", testForecast.size(), sut.size());
    }

    @Test
    public void get_first_ok_day_most_tolerant_returns_correct_day() {
        Forecaster testForecaster = new Forecaster();
        Location testLocation = new Location("15217", 04, 26, testForecast);

        int output = testForecaster.getFirstOkDayMostTolerant(testLocation);
        int expected = 3;

        Assert.assertEquals("wrong day returned", expected ,output);

    }
    @Test
    public void get_first_ok_day_medium_tolerant_returns_correct_day() {
        Forecaster testForecaster = new Forecaster();
        Location testLocation = new Location("15217", 04, 26, testForecast);

        int output = testForecaster.getFirstOkDayMediumTolerant(testLocation);
        int expected = 10;

        Assert.assertEquals("wrong day returned", expected ,output);
    }
    @Test
    public void get_first_ok_day_least_tolerant_returns_correct_day() {
        Forecaster testForecaster = new Forecaster();
        Location testLocation = new Location("15217", 04, 26, testForecast);

        int output = testForecaster.getFirstOkDayLeastTolerant(testLocation);
        int expected = 11;

        Assert.assertEquals("wrong day returned", expected ,output);
    }

    @Test
    public void check_if_too_hot_returns_correct() {
        Forecaster testForecaster = new Forecaster();
        Location testLocation = new Location("15217", 04, 26, testForecast);

        //first check if 3 day avg works (last 3 days should avg to > 77.5 but no 5 day stretch should be >72.5)
        boolean output = testForecaster.checkIfTooHotForMostColdTolerant(testLocation);
        Assert.assertTrue("expected true but returned false" ,output);

        // next check if 5 day stretch above 72.5 works by altering temps
        testForecast.get(12).setHighTemp(71);
        testForecast.get(13).setHighTemp(70);
        testForecast.get(9).setHighTemp(72);
        testForecast.get(10).setHighTemp(72);

        output = testForecaster.checkIfTooHotForMostColdTolerant(testLocation);
        Assert.assertTrue("expect true but returned false", output);


        //reduce one more day to get false result
        testForecast.get(13).setHighTemp(60);

        output = testForecaster.checkIfTooHotForMostColdTolerant(testLocation);
        Assert.assertFalse("expect false but returned true", output);
    }

    @Test
    public void getBestPlantingDayWithRainForecast_returns_correct_day_starting_from_index_zero() {
        Forecaster testForecaster = new Forecaster();
        Location testLocation = new Location("15217", 04, 26, testForecast);

        //first check if works for medium reliable timeframe (day4-7) with 3 day avg > 45% chance rain
        int bestDayTest = testForecaster.getBestPlantingDayWithRainForecast(0, testLocation);
        Assert.assertEquals(4, bestDayTest);

        //adjust chance of precip to test most reliable timeframe
        testForecast.get(6).setChanceOfPrecip(0);
        testForecast.get(2).setChanceOfPrecip(50);

        bestDayTest = testForecaster.getBestPlantingDayWithRainForecast(0, testLocation);
        Assert.assertEquals(0, bestDayTest);

        //adjust to test least reliable days
        testForecast.get(2).setChanceOfPrecip(0);
        testForecast.get(12).setChanceOfPrecip(70);

        bestDayTest = testForecaster.getBestPlantingDayWithRainForecast(0, testLocation);
        Assert.assertEquals(9, bestDayTest);

    }

    @Test
    public void getBestPlantingDayWithRainForecast_returns_correct_day_starting_from_firstokDay() {
        Forecaster testForecaster = new Forecaster();
        Location testLocation = new Location("15217", 04, 26, testForecast);

        int bestDayTest = testForecaster.getBestPlantingDayWithRainForecast(4, testLocation);
        Assert.assertEquals(4, bestDayTest);

        bestDayTest = testForecaster.getBestPlantingDayWithRainForecast(6, testLocation);
        Assert.assertEquals(-1, bestDayTest);

    }

    @Test
    public void isRainy_is_accurate_for_all_timeframes() {
        Forecaster testForecaster = new Forecaster();
        Location testLocation = new Location("15217", 04, 26, testForecast);

        boolean test = testForecaster.isRainy(2, testLocation);
        Assert.assertFalse("should be false", test);

        test = testForecaster.isRainy(3, testLocation);
        Assert.assertFalse("should be false", test);

        test = testForecaster.isRainy(11, testLocation);
        Assert.assertTrue("should be true", test);
    }

    }

