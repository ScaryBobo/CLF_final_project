package project_backend.model.questionnaire;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Answer implements Serializable {
    private String answerId;
    private String questionId;
    private String answerText;
}
