package pro.sky.spring.cursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.spring.cursework.exseption.QuestionNotFoundException;
import pro.sky.spring.cursework.model.Question;

import java.util.HashSet;
import java.util.Set;


@Service
public class ExaminerServiceImpl implements ExaminerService {

  private final QuestionService questionService;

  @Autowired
  public ExaminerServiceImpl(QuestionService questionService) {
    this.questionService = questionService;
  }

  public Set<Question> getQuestions(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Количество вопросов должно быть больше нуля.");
    }

    Set<Question> questions = new HashSet<>();

    while (questions.size() < amount) {
      try {
        Question question = questionService.getRandomQuestion();
        questions.add(question);
      } catch (QuestionNotFoundException e) {

        if (questions.size() < amount) {

          throw new QuestionNotFoundException("Недостаточно уникальных вопросов для запроса.");

        }
        break;
      }
    }
    return questions;
  }
}