package project_backend.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project_backend.model.Question;
import project_backend.model.Questionnaire;

import javax.print.attribute.standard.Media;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/")
public class QuizRestController {

    private Logger logger = Logger.getLogger(QuizRestController.class.getName());

    @PostMapping(path = "/createquiz/{sessId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Question> createQuiz (@RequestPart("myfile") MultipartFile file, @PathVariable String sessId) throws IOException {

        logger.info(">>>> file:" + file);
        System.out.println(file.getName());
        System.out.println("sessionID is >>>>" + sessId );

        InputStream inputStream = file.getInputStream();
        String originalName = file.getOriginalFilename();
        String contentType = file.getContentType();
        System.out.println(inputStream);
        System.out.println(originalName);
        System.out.println(contentType);

        Reader reader = new BufferedReader(new InputStreamReader((inputStream), StandardCharsets.UTF_8));
        CsvToBean<Question> beans = new CsvToBeanBuilder(reader)
                .withType(Question.class)
                .build();

        List<Question> questions = beans.parse();
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestionList(questions);
        questionnaire.setUserId(sessId);

        System.out.println(questionnaire);


        return null;
    }

}
