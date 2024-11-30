package pro.sky.spring.cursework.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import pro.sky.spring.cursework.exseption.QuestionNotFoundException;
import pro.sky.spring.cursework.model.Question;
import org.springframework.stereotype.Service;

@Service
public class JavaQuestionService implements QuestionService {

  private final Set<Question> questionSet = new HashSet<>();

  public JavaQuestionService() {

  }


  @Override
  public Question add(String question, String answer) {
    Question model = new Question(question, answer);

    if (questionSet.contains(model)) {
      throw new QuestionNotFoundException(question);
    }
    questionSet.add(model);
    return model;
  }

  @Override
  public Question add(Question question) {

    if (question == null) {
      throw new IllegalArgumentException("Вопрос не может быть пустым");
    }

    if (questionSet.contains(question)) {
      throw new QuestionNotFoundException(question.getQuestion());
    }
    questionSet.add(question);
    return question;

  }

  @Override
  public Question remove(String question, String answer) {
    Question questionRemoved = new Question(question, answer);

    if (!questionSet.contains(questionRemoved)) {
      throw new QuestionNotFoundException(question);
    }

    questionSet.remove(questionRemoved);
    return questionRemoved;
  }

  @Override
  public Collection<Question> getAll() {
    return Collections.unmodifiableCollection(questionSet);
  }

  @Override
  public Question getRandomQuestion() {
    if (questionSet.isEmpty()) {
      throw new QuestionNotFoundException("Нет доступных вопросов.");
    }
    Question[] questionsArray = questionSet.toArray(new Question[0]);
    Random random = new Random();
    int randomIndex = random.nextInt(questionsArray.length);

    return questionsArray[randomIndex];
  }


}
