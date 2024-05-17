package com.example.malang.service;

import com.example.malang.domain.QuestionType;
import com.example.malang.dto.QuestionRequest;
import com.example.malang.dto.QuestionResponse;
import com.example.malang.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionResponse> findAllByQuestionType(String questionType){

        QuestionType type = QuestionType.순한맛;

        if (questionType.equals("매운맛")) {
            type = QuestionType.매운맛;
        }


        log.info("[type] = {}", type);
        return questionRepository.findAllByQuestionType(type).stream()
                .map(question -> new QuestionResponse(question))
                .collect(Collectors.toList());
    }
}
