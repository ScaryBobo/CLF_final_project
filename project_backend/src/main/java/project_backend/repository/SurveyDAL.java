package project_backend.repository;

import project_backend.model.questionnaire.Answer;
import project_backend.model.questionnaire.Question;
import project_backend.model.questionnaire.Survey;

import java.util.List;

public interface SurveyDAL {

    //Create Survey
    boolean insertSurvey (String userId, String surveyId, String surveyTitle);
    boolean insertQuestion (String surveyId, String questionText, String questionId);
    boolean insertAnswer (String questionId, String answerText, String answerId);


    //Create attempts
    boolean insertAttempt (String attemptId, String surveyId, String userId);
    boolean insertAnsweredSurvey (String attemptId, String questionId, String answerId);


    //Get list of surveys by user
    List<Survey> getListOfSurveyByUser(String userId);
    List<Question> getListOfQuestionsBySurvey (String surveyId);
    List<Answer> getListOfAnswersByQuestion(String questionId);







}
