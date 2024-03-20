package toby.ok2plantProject.Service.Models;


public class Day {
    //using API from visualcrossing.com ex: https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/15217?unitGroup=metric&include=days&key=T4URRAZHKGS3VGVNZH9886XV5&contentType=json
   //tested in lecture 12
//@JsonProperty("tempmax")
    private double tempmax;   //highTemp

//@JsonProperty("tempmin")
    private double tempmin;   //lowTemp

//@JsonProperty("precipprob")
    private double precipprob;  //chanceOfPrecip

    public Day() {
    }

    public Day(double tempmax, double tempmin, double precipprob) {
        this.tempmax = tempmax;
        this.tempmin = tempmin;
        this.precipprob = precipprob;
    }

    public double getTempmax() {
        return tempmax;
    }

    public void setTempmax(double tempmax) {
        this.tempmax = tempmax;
    }

    public double getTempmin() {
        return tempmin;
    }

    public void setTempmin(double tempmin) {
        this.tempmin = tempmin;
    }

    public double getPrecipprob() {
        return precipprob;
    }

    public void setPrecipprob(double precipprob) {
        this.precipprob = precipprob;
    }
//    public Day(double highTemp, double lowTemp, int chanceOfPrecip) {
//        this.highTemp = highTemp;
//        this.lowTemp = lowTemp;
//        this.chanceOfPrecip = chanceOfPrecip;
//    }
//
//    public double getHighTemp() {
//        return highTemp;
//    }
//
//    public void setHighTemp(double highTemp) {
//        this.highTemp = highTemp;
//    }
//
//    public double getLowTemp() {
//        return lowTemp;
//    }
//
//    public void setLowTemp(double lowTemp) {
//        this.lowTemp = lowTemp;
//    }
//
//    public double getChanceOfPrecip() {
//        return chanceOfPrecip;
//    }
//
//    public void setChanceOfPrecip(int chanceOfPrecip) {
//        this.chanceOfPrecip = chanceOfPrecip;
//    }
}
