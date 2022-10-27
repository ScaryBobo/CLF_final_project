package project_backend.mapper;

import org.springframework.jdbc.core.RowMapper;
import project_backend.model.questionnaire.Attempt;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttemptMapper implements RowMapper<Attempt> {

    @Override
    public Attempt mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Attempt.builder()
                .attemptId(rs.getString("attempt_id"))
                .surveyId(rs.getString("survey_id"))
                .userId(rs.getString("user_id"))
                .build();
    }
}
