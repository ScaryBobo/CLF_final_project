package project_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project_backend.model.questionnaire.Question;
import project_backend.model.questionnaire.Survey;
import project_backend.repository.SurveyRepository;

import java.sql.SQLException;


@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepo;

    @Transactional(rollbackFor = {SQLException.class})
    public void createSurvey(Survey survey) {
        surveyRepo.insertSurvey(survey.getUserId(), survey.getSurveyTitle(), survey.getSurveyId());

        for (Question question : survey.getQuestions()) {
            surveyRepo.insertQuestion(question.getQuestionId(), question.getQuestionText(), question.getSurveyId());
            question.getAnswers().stream().forEach(answer -> surveyRepo.insertAnswer(
                    answer.getAnswerId(), answer.getAnswerText(), answer.getQuestionId()));
        }
    }
}
