package project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project_backend.model.ImageChartApiReq;
import project_backend.service.SurveyService;
import project_backend.service.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/")
public class ChartRestController {
    private Logger logger = Logger.getLogger(ChartRestController.class.getName());

    @Autowired
    private SurveyService surveySvc;

    @Autowired
    private UserService userSvc;

    @GetMapping (path = "/result/{surveyId}")
    public ResponseEntity<Map<String, String>> getResultImage (@PathVariable String surveyId) throws URISyntaxException {
        System.out.println(">>> surveyID called is " + surveyId);

        Map<String,String> questionToImageUrl = new HashMap<>();

        surveySvc.retrieveListOfChartInputs(surveyId).forEach(
                input -> {
                    questionToImageUrl.put(input.getQuestionId(),
                            ImageChartApiReq.builder()
                                    .answerCounts(Arrays.asList(input.getCounts().split(",")))
                                    .answerTexts(Arrays.asList(input.getAnswerText().split(",")))
                                    .questionText(input.getQuestionText())
                                    .build().constructImageUrl());
                }
        );


        return ResponseEntity.status(HttpStatus.OK).body(questionToImageUrl);
    }

    public static String decodeUrl(String givenUrl){
        String decodedUrl = givenUrl;
        try{
            decodedUrl = URLDecoder.decode(givenUrl, StandardCharsets.UTF_8.name());

        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return decodedUrl;
    }

}
