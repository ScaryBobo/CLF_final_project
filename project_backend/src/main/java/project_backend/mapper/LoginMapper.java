package project_backend.mapper;

import org.springframework.jdbc.core.RowMapper;
import project_backend.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginMapper implements RowMapper<User> {

    public User mapRow (ResultSet rs, int numRow) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setUserId(rs.getString("user_id"));
        return user;
    }
}
