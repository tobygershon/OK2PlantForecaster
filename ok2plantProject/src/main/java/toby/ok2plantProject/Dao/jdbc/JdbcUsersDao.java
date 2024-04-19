package toby.ok2plantProject.Dao.jdbc;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import toby.ok2plantProject.Dao.UsersDao;
import toby.ok2plantProject.Exceptions.DaoException;

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

    @Override
    public int createUser(String email, String ok2PlantDate, boolean wantsFollowUp) {
        int returnedUserId = 0;

        String sql = "insert into users (email, ok2plant_date, request_follow_up) " +
                "values (?, ?, ?) returning user_id";

        try {
            returnedUserId = jdbcTemplate.queryForObject(sql, int.class, email, ok2PlantDate, wantsFollowUp);

            if (returnedUserId == 0) {
                System.out.println("didn't work");
                throw new DaoException("Couldn't create user");
            } else {
                return returnedUserId;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }

}
