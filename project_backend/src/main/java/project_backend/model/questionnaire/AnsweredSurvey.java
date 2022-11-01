package project_backend.model.questionnaire;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AnsweredSurvey implements Serializable {
    private String attemptId;
    private String questionId;
    private String answerId;
}
