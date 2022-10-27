package project_backend.model.questionnaire;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attempt {
    private String surveyId;
    private String userId;
    private String attemptId;
}
