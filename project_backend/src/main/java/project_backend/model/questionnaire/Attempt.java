package project_backend.model.questionnaire;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Attempt implements Serializable {
    private String surveyId;
    private String userId;
    private String attemptId;
}
