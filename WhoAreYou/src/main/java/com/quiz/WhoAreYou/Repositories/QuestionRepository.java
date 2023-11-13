package com.quiz.WhoAreYou.Repositories;

import com.quiz.WhoAreYou.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
