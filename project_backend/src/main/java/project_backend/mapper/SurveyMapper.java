package project_backend.mapper;

import org.springframework.jdbc.core.RowMapper;
import project_backend.model.questionnaire.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyMapper implements RowMapper<Survey> {

    public Survey mapRow (ResultSet rs, int numRow) throws SQLException {
        // Survey
        // survey.setSurveyId(rs.getString("survey_id"));
        // survey.setSurveyTitle(rs.getString("survey_title"));
        // survey.setUserId(rs.getString("user_id"));
        // survey.setDateCreated(rs.getString("created_ts"));
        return null;
        // return survey;
    }
}
