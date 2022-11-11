package project_backend.controller;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project_backend.model.CsvQuestionDto;
import project_backend.model.attemptMapper.AttemptPayloadWrapper;
import project_backend.model.questionnaire.*;
import project_backend.service.EmailService;
import project_backend.service.SurveyService;
import project_backend.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/")
public class SurveyRestController {

    private Logger logger = Logger.getLogger(SurveyRestController.class.getName());



    @Autowired
    private SurveyService surveySvc;

    @Autowired
    private UserService userSvc;

    @Autowired
    private EmailService emailSvc;

    @PostMapping(path = "/createquiz/{sessId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Survey> createQuiz(@RequestPart("myfile") MultipartFile file,
                                           @PathVariable String sessId,
                                           @RequestParam("title") String title) throws IOException {

        // actual csv parsing
        CsvToBean<CsvQuestionDto> beans = new CsvToBeanBuilder(
                new BufferedReader(new InputStreamReader((file.getInputStream()), StandardCharsets.UTF_8)))
                .withType(CsvQuestionDto.class)
                .build();

        String surveyId = constructUUID();

        Survey newSurvey = Survey.builder()
                            .surveyId(surveyId)
                            .surveyTitle(title)
                            .userId(sessId)
                            .questions(createQuestionsFromCSV(beans.parse(), surveyId))
                            .build();
        surveySvc.createSurvey(newSurvey);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSurvey);
    }

    // Search list of Surveys created by current user
    @GetMapping(path = "/getquizz/{sessId}")
    public ResponseEntity <List<Survey>> getQuiz(@PathVariable String sessId){
        List<Survey> userSurveyList = surveySvc.getSurvey(sessId);

        return ResponseEntity.status(HttpStatus.OK).body(userSurveyList);
    }


    // Search list of Surveys by Email
    @GetMapping (path = "/searchuserquizzes")
    public ResponseEntity<List<Survey>> searchQuizByUser (@RequestParam String email){
        System.out.println("payload is >>>>>" + email);
        Optional<String> userIdOpt = userSvc.getUserId(email);
        if(userIdOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Survey> surveyList = surveySvc.getSurvey(userIdOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body(surveyList);
    }

    @GetMapping (path = "/displayquiz")
    public ResponseEntity<List<Question>> loadSurvey (@RequestParam String surveyId){
        System.out.println(">>>> surveyId requested" + surveyId);
        List<Question> questionList = surveySvc.retrieveQuestionListBySurveyId(surveyId);
        return ResponseEntity.status(HttpStatus.OK).body(questionList);
    }

    @PostMapping (path = "/createattempt/{sessId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Attempt> createAttempt (@PathVariable String sessId,
                                                  @RequestBody String payload){

        System.out.println(">>>> payload : " + payload);

        Gson gson = new Gson();
        AttemptPayloadWrapper attemptPayloadWrapper = gson.fromJson(payload, AttemptPayloadWrapper.class);

        Attempt newAttempt = Attempt.builder()
                .attemptId(constructUUID())
                .userId(sessId)
                .surveyId(attemptPayloadWrapper.getSurveyId())
                .build();

        List<AnsweredSurvey> answeredSurveyList = attemptPayloadWrapper.getAnsweredSurveys().stream()
                .map(x -> {
                    return AnsweredSurvey.builder()
                            .attemptId(newAttempt.getAttemptId())
                            .questionId(x.getQuestionId())
                            .answerId(x.getAnswerId()).build();
                }).toList();
        System.out.println(">>>> attempt: " + newAttempt);
        System.out.println(">>> List of answered survey:" + answeredSurveyList);

        surveySvc.createAttempt(newAttempt, answeredSurveyList);

        String toEmail = userSvc.getUserById(sessId).get().getEmail();

        emailSvc.sendEmail(toEmail);

        return ResponseEntity.status(HttpStatus.CREATED).body(newAttempt);
    }

    @DeleteMapping (path = "/deletequiz", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteSurveyBySurveyId (@RequestParam String surveyId){
        System.out.println(">>>>>> surveyId is " + surveyId);
        if (surveySvc.deleteSurvey(surveyId)) {
            return ResponseEntity.status(HttpStatus.OK).body("");
        } else  {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }


    private List<Question> createQuestionsFromCSV(List<CsvQuestionDto> csvQuestions, String surveyId) {
        return csvQuestions.stream()
                .map(csvQuestion -> {
                        String questionId = constructUUID();
                        return Question.builder()
                                .questionId(questionId)
                                .questionText(csvQuestion.getQuestion())
                                .surveyId(surveyId)
                                .answers(new ArrayList<>(Arrays.asList(
                                        Answer.builder()
                                                .questionId(questionId)
                                                .answerText(csvQuestion.getOption1())
                                                .answerId(constructUUID())
                                                .build(),
                                        Answer.builder()
                                                .questionId(questionId)
                                                .answerText(csvQuestion.getOption2())
                                                .answerId(constructUUID())
                                                .build(),
                                        Answer.builder()
                                                .questionId(questionId)
                                                .answerText(csvQuestion.getOption3())
                                                .answerId(constructUUID())
                                                .build(),
                                        Answer.builder()
                                                .questionId(questionId)
                                                .answerText(csvQuestion.getOption4())
                                                .answerId(constructUUID())
                                                .build()
                                        ))).build();
                }).toList();
    }
    private String constructUUID(){return UUID.randomUUID().toString();}
}
