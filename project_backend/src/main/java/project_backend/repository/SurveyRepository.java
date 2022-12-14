package project_backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import project_backend.mapper.*;
import project_backend.model.ChartImageInputs;
import project_backend.model.questionnaire.*;

import java.util.List;
import java.util.Optional;

@Repository
public class SurveyRepository implements SurveyDAL{
    @Autowired
    private JdbcTemplate template;

    @Autowired
    RedisTemplate redisTemplate;
    
    //SURVEY CREATION
    private static final String SQL_INSERT_SURVEY = "Insert into survey (user_id, survey_title, survey_id) values (?,?,?)";
    private static final String SQL_INSERT_QUESTION = "Insert into question (question_id, question_text, survey_id) values (?,?,?)";
    private static final String SQL_INSERT_ANSWER = "Insert into answer (answer_id, answer_text, question_id) values (?,?,?)";

    //ATTEMPT CREATION
    private static final String SQL_INSERT_ATTEMPT = "Insert into attempt (attempt_id, survey_id, user_id) values (?,?,?)";
    private static final String SQL_INSERT_ANSWERED_SURVEY = "Insert into answeredSurvey (attempt_id, question_id, answer_id) values (?,?,?)";

    private static final String SQL_CHECK_IF_ATTEMPT_PRESENT = "select * from attempt where attempt_id =?";
    
    //RESULTS CREATION
    private static final String SQL_GET_LIST_OF_SURVEYS_BY_USER="Select * from survey_view where user_id = ?";
    private static final String SQL_GET_LIST_OF_QUESTIONS_BY_SURVEY = "Select * from question where survey_id =?";
    private static final String SQL_GET_LIST_OF_ANSWERS_BY_QUESTION = "Select * from answer where question_id =?";


    private static final String SQL_GET_LIST_OF_ATTEMPTS_BY_SURVEY = "select * from attempt where survey_id =?";
    private static final String SQL_GET_LIST_OF_QUESTIONID_AND_ANSWERID_BY_ATTEMPT = "select * from answeredSurvey where attempt_id =?";

    private static final String SQL_GET_LIST_OF_QUESTION_TEXT_ANSWER_TEXT_AND_COUNTS_BY_SURVEYID = "select question_id, question_text, GROUP_CONCAT(answer_text) as answer_texts, GROUP_CONCAT(counts) as counts FROM AGG_ANS_VIEW " +
            "where question_id in (SELECT question.question_id from question where survey_id =?) " +
            "GROUP BY question_id";


    //Deletion
    private static final String SQL_SET_DELETE_COLUMN = "update survey set deleted=TRUE where survey_id =?";

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
    public List<Survey> getListOfSurveyByUser(String userId) {
        return template.query(SQL_GET_LIST_OF_SURVEYS_BY_USER, new SurveyMapper(), userId);
    }
    @Override
    public List<Question> getListOfQuestionsBySurvey(String surveyId) {
        return template.query(SQL_GET_LIST_OF_QUESTIONS_BY_SURVEY, new QuestionMapper(), surveyId);
    }
    @Override
    public List<Answer> getListOfAnswersByQuestion(String questionId) {
        return template.query(SQL_GET_LIST_OF_ANSWERS_BY_QUESTION, new AnswerMapper(), questionId);
    }

    @Override
    public Optional<Attempt> checkAttemptIdPresent(String attemptId) {
        Attempt result = template.queryForObject(SQL_CHECK_IF_ATTEMPT_PRESENT, new AttemptMapper(), attemptId);
        return Optional.of(result);
    }

    @Override
    public boolean deleteSurveyBySurveyId(String surveyId) {
        final int rowCount = template.update(SQL_SET_DELETE_COLUMN, surveyId);
        return rowCount > 0;
    }



    @Override
    public List<Attempt> getListAttemptsBySurvey(String surveyId) {
        return template.query(SQL_GET_LIST_OF_ATTEMPTS_BY_SURVEY, new AttemptMapper(), surveyId);
    }

    @Override
    public List<AnsweredSurvey> getListOfQuestionIdAndAnswerIdByAttempt(String attemptId) {
        return template.query(SQL_GET_LIST_OF_QUESTIONID_AND_ANSWERID_BY_ATTEMPT, new AnsweredSurveyMapper(), attemptId);
    }

    @Override
    public List<ChartImageInputs> getListOfQuestionTextAnswerTextAndCountsBySurveyId(String surveyId) {
        return template.query(SQL_GET_LIST_OF_QUESTION_TEXT_ANSWER_TEXT_AND_COUNTS_BY_SURVEYID, new ChartImageInputMapper(), surveyId);
    }


}
