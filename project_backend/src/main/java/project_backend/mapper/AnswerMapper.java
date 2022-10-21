package project_backend.mapper;

import org.springframework.jdbc.core.RowMapper;
import project_backend.model.questionnaire.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {
    @Override
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Answer.builder()
                .answerId(rs.getString("answer_id"))
                .answerText(rs.getString("answer_text"))
                .questionId(rs.getString("question_id"))
                .build();
    }
}
