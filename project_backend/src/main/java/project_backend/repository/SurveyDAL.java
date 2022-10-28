package project_backend.repository;

import project_backend.model.ChartImageInputs;
import project_backend.model.questionnaire.*;

import java.util.List;
import java.util.Optional;

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


    //Check if attemptId is present
    Optional<Attempt> checkAttemptIdPresent (String attemptId);

    //Delete created survey
    boolean deleteSurveyByUser(String userId);
    boolean deleteQuestionsBySurvey (String surveyId);
    boolean deleteAnswersByQuestion (String questionId);

    List<Attempt> getListAttemptsBySurvey (String surveyId);
    List<AnsweredSurvey> getListOfQuestionIdAndAnswerIdByAttempt (String attemptId);

    List<ChartImageInputs> getListOfQuestionTextAnswerTextAndCountsBySurveyId(String surveyID);




}
