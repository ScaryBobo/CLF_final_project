package project_backend.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;



@Data
public class CsvQuestionDto {
    @CsvBindByName
    private String question;
    @CsvBindByName
    private String option1;
    @CsvBindByName
    private String option2;
    @CsvBindByName
    private String option3;
    @CsvBindByName
    private String option4;
}
