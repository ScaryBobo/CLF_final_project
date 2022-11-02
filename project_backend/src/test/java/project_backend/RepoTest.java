package project_backend;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import project_backend.model.questionnaire.Survey;
import project_backend.repository.SurveyRepository;
import project_backend.service.SurveyService;
import java.util.List;

@Disabled
@SpringBootTest
public class RepoTest {
   
    @Autowired
    public SurveyRepository surveyRepository;
    
    @Autowired
    public SurveyService surveySvc;

    // @Test
    // void test(){
    //     surveyRepository.insertSurvey("2b9cef88-9bea-43d1-b99d-4e825b0d6a5b",
    //             "survey1234","testUploadsurvey");
    //     surveyRepository.insertQuestion("survey1234","questionUploadTest",
    //             "question12345");
    //     surveyRepository.insertAnswer("question12345", "answerUploadTest1","answer12345");
    //     surveyRepository.insertAnswer("question12345", "answerUploadTest2","answer123456");
    // }
    
    // @Test
    // void getListOfSurveys(){
    //     System.out.println(surveySvc.getSurvey("45b52f9a"));
    // }
    
}
