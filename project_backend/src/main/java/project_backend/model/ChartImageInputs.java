package project_backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChartImageInputs {
    private String questionText;
    private String questionId;
    private String answerText;
    private String counts;
}
