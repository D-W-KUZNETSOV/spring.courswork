package pro.sky.spring.cursework.service;

import java.util.Collection;
import pro.sky.spring.cursework.model.Question;

public interface ExaminerService {

  Collection<Question> getQuestions(int amount);
}

