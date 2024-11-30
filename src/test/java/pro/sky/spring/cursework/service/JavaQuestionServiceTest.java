package pro.sky.spring.cursework.service;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.sky.spring.cursework.exseption.QuestionNotFoundException;
import pro.sky.spring.cursework.model.Question;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaQuestionServiceTest {

  private final QuestionService javaQuestionService = new JavaQuestionService();

  @Test
  @DisplayName("Проверяет корректное добавление вопроса")
  void adds_A_Question() {
    Question model = new Question("как день", "как ночь");
    javaQuestionService.add(model);
    Collection<Question> actual = javaQuestionService.getAll();
    assertTrue(actual.contains(model));
  }

  @Test
  @DisplayName("Проверяет удаление вопроса")
  void remove() {
    Question question = new Question("как день", "как ночь");
    javaQuestionService.add(question);

    Question model = javaQuestionService.remove("как день", "как ночь");

    Collection<Question> actual = javaQuestionService.getAll();
    assertThat(actual).doesNotContain(question);
  }

  @Test
  @DisplayName("Проверяет получение случайного числа")
  void testGetRandomQuestionMultipleCalls() {

    javaQuestionService.add("Как день", "Как ночь");
    javaQuestionService.add("Как день2", "Как ночь2");
    javaQuestionService.add("Как день3", "Как ночь3");

    Question firstRandomQuestion = javaQuestionService.getRandomQuestion();
    Question secondRandomQuestion = javaQuestionService.getRandomQuestion();

    Collection<Question> allQuestions = javaQuestionService.getAll();
    assertTrue(allQuestions.contains(firstRandomQuestion),
        "Первый случайный вопрос должен быть в наборе вопросов");
    assertTrue(allQuestions.contains(secondRandomQuestion),
        "Второй случайный вопрос должен быть в наборе вопросов");
  }

  @Test
  @DisplayName("Проверяет пустой ли список")
  void getAll_ThenContainsQuestion_ThenReturnAllQuestion() {
    Collection<Question> actual = javaQuestionService.getAll();

    assertThat(actual).isEmpty();
  }

  @Test
  @DisplayName("Корректно возвращает вопрос и его позицию")
  void getAll_ThenContainsQuestion_ThenReturnAllQuestio() {
    Question question = new Question("как день", "как ночь");
    javaQuestionService.add(question);

    Collection<Question> actual = javaQuestionService.getAll();
    assertThat(actual).hasSize(1);

    assertThat(actual.iterator().next()).isEqualTo(question);
  }

  @Test
  @DisplayName("Корректно возвращает лист вопросов из сервиса ")
  void getAll_ThenContainsQuestion_ThenReturnAllQuestions() {
    Question question = new Question("как день", "как ночь");
    Question question2 = new Question("как день1", "как ночь1");
    Question question3 = new Question("как день2", "как ночь2");

    javaQuestionService.add(question);
    javaQuestionService.add(question2);
    javaQuestionService.add(question3);

    Collection<Question> actual = javaQuestionService.getAll();
    assertThat(actual).containsExactlyInAnyOrder(question, question2, question3);
  }

  @Test
  @DisplayName("Проверка корректности добавления вопроса")
  void testAddValidQuestion() {

    Question question = new Question("Как день", "Как ночь");

    Question addedQuestion = javaQuestionService.add(question);

    assertEquals(question, addedQuestion, "Добавленный вопрос должен совпадать с оригиналом");
  }

  @Test
  @DisplayName("Проверка на пустое поле запроса")
  void testAddNullQuestion() {

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      javaQuestionService.add(null);
    });
    assertEquals("Вопрос не может быть пустым", exception.getMessage());
  }


  @Test
  @DisplayName("Проверка на наличие повторяющегося вопроса")
  void testAddDuplicateQuestion() {
    Question question = new Question("Как день", "Как ночь");
    javaQuestionService.add(question);

    QuestionNotFoundException exception = assertThrows(QuestionNotFoundException.class, () -> {
      javaQuestionService.add(question);
    });
    assertEquals("вопрос не найден:[Как день]", exception.getMessage());
  }

  @Test
  void testAddDifferentQuestions() {

    Question question1 = new Question("Как день", "Как ночь");
    Question question2 = new Question("Как ночь", "Как день");

    javaQuestionService.add(question1);

    Question addedQuestion2 = javaQuestionService.add(question2);

    assertEquals(question2, addedQuestion2,
        "Добавленный второй вопрос должен совпадать с оригиналом");

  }
}
