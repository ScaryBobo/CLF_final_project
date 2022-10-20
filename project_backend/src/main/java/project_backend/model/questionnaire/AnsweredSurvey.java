package project_backend.model.questionnaire;

import lombok.Data;

@Data
public class AnsweredSurvey {
    private String attemptId;
    private String questionId;
    private String answerId;
}
