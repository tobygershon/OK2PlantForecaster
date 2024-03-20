package toby.ok2plantProject.Dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import toby.ok2plantProject.Dao.UsersDao;
@Component
public class JdbcUsersDao implements UsersDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUsersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getEmailByUserId() {
        return null;
    }
}
