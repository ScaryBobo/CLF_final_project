package project_backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ImageChartApiReq {
    private List<String> answerCounts;
    private List<String> answerTexts;
    private static final String chs = "700x300";
    private static final String cht = "pd";
    private String questionText;


    public String constructImageUrl(){
        String url = "https://image-charts.com/chart";
        String imageUrl = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("chd", "t:" + String.join(",", answerCounts))
                .queryParam("chdl", String.join("|", answerTexts))
                .queryParam("chl", answerCounts.stream().map(String::valueOf).collect(Collectors.joining("|")))
                .queryParam("chli", answerCounts.stream().map(Integer::parseInt)
                        .reduce(0, Integer::sum) + " Responses")
                .queryParam("chs", chs)
                .queryParam("cht", cht)
                .queryParam("chtt", questionText)
                .toUriString();

        return imageUrl;
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
