package project_backend.model.questionnaire;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnsweredSurvey {
    private String attemptId;
    private String questionId;
    private String answerId;
}
