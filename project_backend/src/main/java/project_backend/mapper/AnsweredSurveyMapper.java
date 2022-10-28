package project_backend.mapper;

import org.springframework.jdbc.core.RowMapper;
import project_backend.model.questionnaire.AnsweredSurvey;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnsweredSurveyMapper implements RowMapper<AnsweredSurvey> {
    @Override
    public AnsweredSurvey mapRow(ResultSet rs, int rowNum) throws SQLException {
        return AnsweredSurvey.builder()
                .attemptId(rs.getString("attempt_id"))
                .questionId(rs.getString("question_id"))
                .answerId(rs.getString("answer_id"))
                .build();
    }
}
