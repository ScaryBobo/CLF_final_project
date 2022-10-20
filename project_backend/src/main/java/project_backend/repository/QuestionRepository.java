package project_backend.repository;

import org.springframework.stereotype.Repository;
import project_backend.QuestionsDAL;

@Repository
public class QuestionRepository implements QuestionsDAL {

//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public List<Questionnaire> getQuestionairesByUserId(String userId) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(userId));
//
//        return  mongoTemplate.find(query,Questionnaire.class);
//    }
//
//    @Override
//    public Questionnaire addQuestionnaire(Questionnaire questionnaire) {
//        mongoTemplate.save(questionnaire);
//        return questionnaire;
//    }
//
//    @Override
//    public List<Question_1> getQuestionsByQuestionnaireId(String questionnaireId) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("questionnaireId").is(questionnaireId));
//
//        return null;
//    }
}
