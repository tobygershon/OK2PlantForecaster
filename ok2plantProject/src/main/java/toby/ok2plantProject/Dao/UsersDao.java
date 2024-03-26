package toby.ok2plantProject.Dao;

public interface UsersDao {
    String getEmailByUserId();

    int createUser(String email);

}
