package toby.ok2plantProject.Service;

import org.springframework.web.client.RestTemplate;
import toby.ok2plantProject.Service.Models.WeatherStation;

public class WeatherStationService {
       private static final String API_BASE_URL = "https://api.farmsense.net/v1/frostdates/stations/?";  //lat=40.360278&lon=-74.957222
        private final RestTemplate restTemplate = new RestTemplate();

        public int getStation(double latitude, double longitude) {
            String url = API_BASE_URL + "lat=" + latitude + "&lon=" + longitude;
            WeatherStation[] array = restTemplate.getForObject(url, WeatherStation[].class);
            int station = Integer.parseInt(array[0].getId());
            return station;
        }
}
