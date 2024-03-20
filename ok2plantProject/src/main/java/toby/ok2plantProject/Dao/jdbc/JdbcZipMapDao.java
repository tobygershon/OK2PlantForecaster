package toby.ok2plantProject.Dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import toby.ok2plantProject.Dao.ZipMapDao;
import toby.ok2plantProject.Dao.models.ZipMap;
@Component
public class JdbcZipMapDao implements ZipMapDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcZipMapDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean inputZipLastFrostKeyValue() {
        return false;
    }

    @Override
    public ZipMap getZipMap() {
        return null;
    }
}
