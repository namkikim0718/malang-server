package com.example.malang.repository;

import com.example.malang.domain.Question;
import com.example.malang.domain.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByQuestionType(QuestionType questionType);
}
