package project_backend.model.questionnaire;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Question {
    private String surveyId;
    private String questionText;
    private String questionId;
    private List<Answer> answers;
}
