package com.example.malang.controller;

import com.example.malang.domain.QuestionType;
import com.example.malang.dto.QuestionRequest;
import com.example.malang.dto.QuestionResponse;
import com.example.malang.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    // 질문 유형에 따른 질문 목록 반환
    @GetMapping("/questions")
    public List<QuestionResponse> findAllByQuestionType(@RequestBody QuestionRequest questionRequest) {
        log.info("[questionRequest Type] = {}", questionRequest.getQuestionType());
        return questionService.findAllByQuestionType(questionRequest);
    }
}
