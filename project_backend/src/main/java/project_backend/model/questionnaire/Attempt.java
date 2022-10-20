package project_backend.model.questionnaire;

import lombok.Data;

@Data
public class Attempt {
    private String surveyId;
    private String userId;
    private String attemptId;
}
