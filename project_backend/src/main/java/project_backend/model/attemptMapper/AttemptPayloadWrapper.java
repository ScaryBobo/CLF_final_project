package project_backend.model.attemptMapper;

import java.util.List;
import lombok.Data;

@Data
public class AttemptPayloadWrapper{
	private List<AnsweredSurveysItem> answeredSurveys;
	private String surveyId;
	private String userId;
}