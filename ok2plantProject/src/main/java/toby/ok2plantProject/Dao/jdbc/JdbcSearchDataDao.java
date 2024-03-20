package toby.ok2plantProject.Dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import toby.ok2plantProject.Dao.SearchDataDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Component
public class JdbcSearchDataDao implements SearchDataDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcSearchDataDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getEmailBySearchId() {
        return null;
    }

    @Override
    public List<String> getEmailListByZip() {
        return null;
    }

    @Override
    public List<String> getEmailListBySearchDate() {
        return null;
    }

    @Override
    public LocalDate getOk2PlantDateBySearchId() {
        return null;
    }
}
