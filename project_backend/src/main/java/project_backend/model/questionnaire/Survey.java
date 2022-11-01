package project_backend.model.questionnaire;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Survey implements Serializable {
    private String surveyId;
    private String userId;
    private String surveyTitle;
    private String dateCreated;
    private List<Question> questions;
}
