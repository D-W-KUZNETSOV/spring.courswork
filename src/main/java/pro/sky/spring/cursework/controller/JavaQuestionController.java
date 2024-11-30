package pro.sky.spring.cursework.controller;

import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.spring.cursework.model.Question;
import pro.sky.spring.cursework.service.QuestionService;

@RestController
@RequestMapping("/exam")

public class JavaQuestionController {

  private final QuestionService questionService;

  public JavaQuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @GetMapping("java/add")
  public Question add(@RequestParam("question") String question
      , @RequestParam("answer") String answer) {
    return questionService.add(question, answer);
  }

  @GetMapping("java/remove")
  public Question remove(@RequestParam("question") String question
      , @RequestParam("answer") String answer) {
    return questionService.remove(question, answer);
  }

  @GetMapping("/java")
  public Collection<Question> getAll() {
    return questionService.getAll();
  }
}
