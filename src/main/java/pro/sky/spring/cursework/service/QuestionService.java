package pro.sky.spring.cursework.service;

import java.util.Collection;
import pro.sky.spring.cursework.model.Question;

public interface QuestionService {


  Question add(String question, String answer);

  Question add(Question question);

 Question remove(String question,String answer) ;

  Question getRandomQuestion();

  Collection<Question> getAll();
}
