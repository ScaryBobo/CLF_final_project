package project_backend.model.questionnaire;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Question implements Serializable {
    private String surveyId;
    private String questionText;
    private String questionId;
    private List<Answer> answers;
}
