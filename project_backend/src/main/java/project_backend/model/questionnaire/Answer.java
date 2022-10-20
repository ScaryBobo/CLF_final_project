package project_backend.model.questionnaire;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {
    private String answerId;
    private String questionId;
    private String answerText;
}
