package project_backend.mapper;

import org.springframework.jdbc.core.RowMapper;
import project_backend.model.questionnaire.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyMapper implements RowMapper<Survey> {
    @Override
    public Survey mapRow (ResultSet rs, int numRow) throws SQLException {

        return Survey.builder()
                .userId(rs.getString("user_id"))
                .surveyTitle(rs.getString("survey_title"))
                .dateCreated(rs.getString("created_ts"))
                .surveyId(rs.getString("survey_id"))
                .build();
    }
}
