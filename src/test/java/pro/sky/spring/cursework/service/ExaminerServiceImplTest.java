package pro.sky.spring.cursework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.spring.cursework.exseption.QuestionNotFoundException;
import pro.sky.spring.cursework.model.Question;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExaminerServiceImplTest {

  @Mock
  private QuestionService questionService;

  @InjectMocks
  private ExaminerServiceImpl examinerService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetQuestions_ValidAmount() {
    int amount = 3;
    Question question1 = new Question("Вопрос 1", "Ответ 1");
    Question question2 = new Question("Вопрос 2", "Ответ 2");
    Question question3 = new Question("Вопрос 3", "Ответ 3");

    when(questionService.getRandomQuestion())
        .thenReturn(question1, question2, question3);

    Set<Question> result = examinerService.getQuestions(amount);

    assertEquals(amount, result.size());
    assertTrue(result.contains(question1));
    assertTrue(result.contains(question2));
    assertTrue(result.contains(question3));
  }

  @Test
  public void testGetQuestions_ZeroAmount() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> examinerService.getQuestions(0));
    assertEquals("Количество вопросов должно быть больше нуля.", exception.getMessage());
  }

  @Test
  public void testGetQuestions_NegativeAmount() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> examinerService.getQuestions(-1));
    assertEquals("Количество вопросов должно быть больше нуля.", exception.getMessage());
  }

  @Test
  public void testGetQuestions_InsufficientUniqueQuestions() {
    when(questionService.getRandomQuestion())
        .thenReturn(new Question("Вопрос 1", "Ответ 1"))
        .thenReturn(new Question("Вопрос 2", "Ответ 2"))
        .thenThrow(new QuestionNotFoundException(
            "Недоступные вопросы.")); // Исключение с другим сообщением

    QuestionNotFoundException exception = assertThrows(QuestionNotFoundException.class,
        () -> examinerService.getQuestions(5)); // Запрашиваем 5 вопросов

    System.out.println("Сообщение исключения: " + exception.getMessage());

    assertEquals("вопрос не найден:[Недостаточно уникальных вопросов для запроса.]",
        exception.getMessage());
  }
}





