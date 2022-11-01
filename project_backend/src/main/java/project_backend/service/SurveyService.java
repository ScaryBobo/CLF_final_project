package project_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project_backend.model.ChartImageInputs;
import project_backend.model.questionnaire.AnsweredSurvey;
import project_backend.model.questionnaire.Attempt;
import project_backend.model.questionnaire.Question;
import project_backend.model.questionnaire.Survey;
import project_backend.repository.SurveyRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepo;

    @CacheEvict(value = "surveys", allEntries = true)
    @Transactional(rollbackFor = {SQLException.class})
    public void createSurvey(Survey survey) {
        surveyRepo.insertSurvey(survey.getUserId(), survey.getSurveyTitle(), survey.getSurveyId());

        for (Question question : survey.getQuestions()) {
            surveyRepo.insertQuestion(question.getQuestionId(), question.getQuestionText(), question.getSurveyId());
            question.getAnswers().stream().forEach(answer -> surveyRepo.insertAnswer(
                    answer.getAnswerId(), answer.getAnswerText(), answer.getQuestionId()));
        }
    }
    @Cacheable ("surveys")
    public List<Survey> getSurvey(String userId){
        System.out.println("getting list of surveys is called");
        List<Survey> surveyList = surveyRepo.getListOfSurveyByUser(userId);
        for (Survey survey : surveyList){
            survey.setQuestions(surveyRepo.getListOfQuestionsBySurvey(survey.getSurveyId()));
            
            for (Question question : survey.getQuestions()){
                question.setAnswers(surveyRepo.getListOfAnswersByQuestion(question.getQuestionId()));
            }
        }
        
        return surveyList;
    }
    @Cacheable ("questions")
    public List<Question> retrieveQuestionListBySurveyId(String surveyId){
        Survey newSurvey = Survey.builder().questions(surveyRepo.getListOfQuestionsBySurvey(surveyId)).build();
        for (Question question : newSurvey.getQuestions()){
            question.setAnswers(surveyRepo.getListOfAnswersByQuestion(question.getQuestionId()));
        }
        List<Question> questionList = newSurvey.getQuestions();
        return questionList;
    }
    @CacheEvict(value = "attempts", allEntries = true)
    @Transactional(rollbackFor = {SQLException.class})
    public void  createAttempt (Attempt attempt, List<AnsweredSurvey> answeredSurveys ){

        surveyRepo.insertAttempt(attempt.getAttemptId(), attempt.getSurveyId(), attempt.getUserId());

        for (AnsweredSurvey answeredSurvey : answeredSurveys) {
            surveyRepo.insertAnsweredSurvey(answeredSurvey.getAttemptId(), answeredSurvey.getQuestionId(), answeredSurvey.getAnswerId());
        }
    }

    public Optional<Attempt> verifyAttemptIdPresent (String attemptId){
        return surveyRepo.checkAttemptIdPresent(attemptId);
    }

    public List<Attempt> retrieveListOfAttemptsBySurvey (String surveyId) {
        return surveyRepo.getListAttemptsBySurvey(surveyId);
    }

    public List<AnsweredSurvey> retrieveListOfAnsweredSurveyByAttempt (String attemptId){
        return surveyRepo.getListOfQuestionIdAndAnswerIdByAttempt(attemptId);
    }

    public List<ChartImageInputs> retrieveListOfChartInputs (String surveyId){
        return surveyRepo.getListOfQuestionTextAnswerTextAndCountsBySurveyId(surveyId);
    }

}
