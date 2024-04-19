package toby.ok2plantProject.Service;

import org.springframework.web.client.RestTemplate;
import toby.ok2plantProject.Service.Models.UpcomingForecast;
import toby.ok2plantProject.Service.Models.ZipToLatLong;

public class UpcomingForecastService {

    private static final String API_BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private String forecastQuery = "?unitGroup=us&include=days&key=T4URRAZHKGS3VGVNZH9886XV5&contentType=json";
    private final RestTemplate restTemplate = new RestTemplate();

    public UpcomingForecast getForecast(String zip) {
        String url = API_BASE_URL + zip + forecastQuery;
        return restTemplate.getForObject(url, UpcomingForecast.class);
    }

    public ZipToLatLong getLatAndLongFromZip(String zip) {
        String url = API_BASE_URL + zip + forecastQuery;
        return restTemplate.getForObject(url, ZipToLatLong.class);

    }

}
