package toby.ok2plantProject.classes;

import java.util.HashMap;
import java.util.Map;

public class Plant {

    private String name;
    private String hardinessString;
    public static final int MOST_COLD_HEARTY_SOWING_TIME = 28;  //how many days before last frost seeds can be sown in garden i.e. radish
    public static final int MOST_COLD_HEARTY_TEMP_TOLERANCE = 25;
    public static final int MEDIUM_COLD_HEARTY_SOWING_TIME = 14;  ////how many days before last frost seeds can be sown in garden i.e. carrots
    public static final int MEDIUM_COLD_HEARTY_TEMP_TOLERANCE = 29;
    public static final int LEAST_COLD_HEARTY_SOWING_TIME = 0;  // tomatoes, cucumber, etc.  can't tolerate below 40
    public static final int LEAST_COLD_HEARTY_TEMP_TOLERANCE = 40;

    private Map<String, String> plantHardinessHashMap= new HashMap<>();
    private Map<Integer, Plant> hardinessGroupingHashMap = new HashMap<>();

    public String getName() {
        return name;
    }

    public int getMostColeHeartySowingTime() {
        return MOST_COLD_HEARTY_SOWING_TIME;
    }

    public int getMediumColdHeartySowingTime() {
        return MEDIUM_COLD_HEARTY_SOWING_TIME;
    }

    public int getLeastColdHeartySowingTime() {
        return LEAST_COLD_HEARTY_SOWING_TIME;
    }

    public String getHardiness() {
        return hardinessString;
    }

    public Map<String, String> getPlantHardinessHashMap() {
        return plantHardinessHashMap;
    }

    public Plant(String name, String hardinessString) {
        this.name = name;
        this.hardinessString = hardinessString;
        plantHardinessHashMap.put(this.name, this.hardinessString);
    }
}
