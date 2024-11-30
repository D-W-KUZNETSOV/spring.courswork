package pro.sky.spring.cursework.exseption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends RuntimeException {

  public QuestionNotFoundException(String question) {
    super("вопрос не найден:[%s]".formatted(question));
  }
}
