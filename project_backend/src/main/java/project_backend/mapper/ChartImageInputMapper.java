package project_backend.mapper;

import org.springframework.jdbc.core.RowMapper;
import project_backend.model.ChartImageInputs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChartImageInputMapper implements RowMapper<ChartImageInputs> {
    @Override
    public ChartImageInputs mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ChartImageInputs.builder()
                .questionText(rs.getString("question_text"))
                .questionId(rs.getString("question_id"))
                .answerText(rs.getString("answer_texts"))
                .counts(rs.getString("counts"))
                .build();
    }
}
