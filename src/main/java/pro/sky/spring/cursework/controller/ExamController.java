package pro.sky.spring.cursework.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import pro.sky.spring.cursework.model.Question;
import pro.sky.spring.cursework.service.ExaminerService;

@RestController
@RequestMapping("/ordinary/exam")
public class ExamController {

  private final ExaminerService examinerService;

  @Autowired
  public ExamController(ExaminerService examinerService) {
    this.examinerService = examinerService;
  }

  @GetMapping("/questions")
  public ResponseEntity<Collection<Question>> getQuestions(@RequestParam int amount) {
    if (amount <= 0) {
      return ResponseEntity.badRequest().build();
    }

    Collection<Question> questions = examinerService.getQuestions(amount);
    return ResponseEntity.ok(questions);
  }
}



