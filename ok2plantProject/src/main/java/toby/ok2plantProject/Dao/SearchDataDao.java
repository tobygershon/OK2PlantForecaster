package toby.ok2plantProject.Dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SearchDataDao {

    String getEmailBySearchId();

    List<String> getEmailListByZip();

    List<String> getEmailListBySearchDate();

    LocalDate getOk2PlantDateBySearchId();

}
