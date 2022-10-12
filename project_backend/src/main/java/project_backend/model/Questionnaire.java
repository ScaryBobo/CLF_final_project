package project_backend.model;

import lombok.Data;

import java.util.List;

@Data
public class Questionnaire {
    List<Question> questionList;
    private String userId;
}
