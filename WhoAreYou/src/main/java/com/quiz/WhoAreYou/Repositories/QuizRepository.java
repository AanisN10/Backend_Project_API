package com.quiz.WhoAreYou.Repositories;

import com.quiz.WhoAreYou.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository  extends JpaRepository<Quiz, Long> {
}
