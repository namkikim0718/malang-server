package com.example.malang.controller;

import com.example.malang.domain.QuestionType;
import com.example.malang.dto.QuestionRequest;
import com.example.malang.dto.QuestionResponse;
import com.example.malang.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    // 질문 유형에 따른 질문 목록 반환
    @GetMapping("/questions")
    public List<QuestionResponse> findAllByQuestionType(@RequestParam("questionType") String questionType) {
        log.info("[questionType] = {}", questionType);
        return questionService.findAllByQuestionType(questionType);
    }
}
