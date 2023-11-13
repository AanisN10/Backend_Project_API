package com.quiz.WhoAreYou.repositories;

import com.quiz.WhoAreYou.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository  extends JpaRepository<Quiz, Long> {
}
