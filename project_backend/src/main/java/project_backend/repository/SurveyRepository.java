package project_backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import project_backend.mapper.SurveyMapper;
import project_backend.model.questionnaire.Survey;

import java.util.List;

@Repository
public class SurveyRepository implements SurveyDAL{
    @Autowired
    private JdbcTemplate template;
    
    //SURVEY CREATION
    private static final String SQL_INSERT_SURVEY = "Insert into survey (user_id, survey_title, survey_id) values (?,?,?)";
    private static final String SQL_INSERT_QUESTION = "Insert into question (question_id, question_text, survey_id) values (?,?,?)";
    private static final String SQL_INSERT_ANSWER = "Insert into answer (answer_id, answer_text, question_id) values (?,?,?)";

    //ATTEMPT CREATION
    private static final String SQL_INSERT_ATTEMPT = "Insert into attempt (attempt_id, survey_id, user_id) values (?,?,?)";
    private static final String SQL_INSERT_ANSWERED_SURVEY = "Insert into answeredSurvey (attempt_id, question_id, answer_id) values (?,?,?)";
    
    //RESULTS CREATION
    private static final String SQL_GET_LIST_OF_SURVEYS_ANSWERED_BY_USER="Select * from survey where survey_id = (select survey_id from attempt where user_id =? )";
    
    @Override
    public boolean insertSurvey(String userId, String surveyTitle, String surveyId) {
        final int rowCount = template.update(SQL_INSERT_SURVEY, userId, surveyTitle, surveyId);
        return rowCount > 0;
    }

    @Override
    public boolean insertQuestion(String questionId, String questionText, String surveyId)   {
        final int rowCount = template.update(SQL_INSERT_QUESTION, questionId, questionText, surveyId);
        return rowCount > 0;
    }

    @Override
    public boolean insertAnswer(String answerId, String answerText, String questionId) {
        final int rowCount = template.update(SQL_INSERT_ANSWER, answerId, answerText, questionId);
        return rowCount > 0;
    }

    @Override
    public boolean insertAttempt(String attemptId, String surveyId, String userId) {
        final int rowCount = template.update(SQL_INSERT_ATTEMPT, attemptId, surveyId, userId);
        return rowCount > 0;
    }

    @Override
    public boolean insertAnsweredSurvey(String attemptId, String questionId, String answerId) {
        final int rowCount = template.update(SQL_INSERT_ANSWERED_SURVEY, attemptId, questionId, answerId);
        return rowCount >0;
    }

    @Override
    public List<Survey> getListOfAnsweredSurveysByUser(String userId) {
        return template.query(SQL_GET_LIST_OF_SURVEYS_ANSWERED_BY_USER, new SurveyMapper(), userId);
    }
}
