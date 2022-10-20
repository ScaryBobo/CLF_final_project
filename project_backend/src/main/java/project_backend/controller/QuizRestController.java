package project_backend.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project_backend.model.CsvQuestionDto;
import project_backend.model.questionnaire.Answer;
import project_backend.model.questionnaire.Question;
import project_backend.model.questionnaire.Survey;
import project_backend.repository.QuestionRepository;
import project_backend.service.SurveyService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/")
public class QuizRestController {

    private Logger logger = Logger.getLogger(QuizRestController.class.getName());

    @Autowired
    private QuestionRepository quizRepo;

    @Autowired
    private SurveyService surveySvc;

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
