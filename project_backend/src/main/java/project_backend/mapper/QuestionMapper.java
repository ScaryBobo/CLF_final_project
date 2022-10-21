package project_backend.mapper;

import org.springframework.jdbc.core.RowMapper;
import project_backend.model.questionnaire.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Question.builder()
                .questionId(rs.getString("question_id"))
                .questionText(rs.getString("question_text"))
                .surveyId(rs.getString("survey_id"))
                .build();
    }
}
