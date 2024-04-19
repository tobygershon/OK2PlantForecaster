package toby.ok2plantProject.Service;

import org.springframework.web.client.RestTemplate;
import toby.ok2plantProject.Service.Models.LastFrostDate;


import java.time.LocalDate;

public class LastFrostDateService {

    private static final String API_BASE_URL = "https://api.farmsense.net/v1/frostdates/probabilities/?station=";
    private static final String END_URL = "&season=1";

    private RestTemplate restTemplate = new RestTemplate();

    public LocalDate getLastFrostDate(int station) {

        String url = API_BASE_URL + station + END_URL;
        LastFrostDate[] array = restTemplate.getForObject(url, LastFrostDate[].class);
        String lastFrost = array[0].getLastFrostDate();
        int year = LocalDate.now().getYear();
        int month = Integer.parseInt(lastFrost.substring(0, 2));
        int day = Integer.parseInt(lastFrost.substring(2));
        LocalDate lastFrostDate = LocalDate.of(year, month, day);
        return lastFrostDate;
    }
}
