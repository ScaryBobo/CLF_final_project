package project_backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import project_backend.mapper.UserMapper;
import project_backend.model.User;

import java.util.Optional;

@Repository
public class UserRepository {

    private static String SQL_INSERT_NEW_USER = " insert into user (user_id, password, email) values (?,?,?)";
    private static String SQL_FIND_USER_BY_EMAIL = "select email from user where email = ?";
    private static String SQL_AUTH_USER = "select email, password from user where email =? and password =?";

    @Autowired
    private JdbcTemplate template;

    public boolean insertNewUser (String userId, String password, String email){
        final int rowCount = template.update(SQL_INSERT_NEW_USER, userId, password
        , email);

        return rowCount > 0;
    }
    public Optional<String> getUserRecordByEmail (String email){
        final SqlRowSet result = template.queryForRowSet(SQL_FIND_USER_BY_EMAIL, email);
        if (!result.next()){
            return Optional.empty();
        }
        return Optional.of(result.getString("email"));
    }

    public Optional<User> verifyUser (String email, String password){

        User result = template.queryForObject(SQL_AUTH_USER, new UserMapper(), email, password);
        return Optional.of(result);

    }







}
