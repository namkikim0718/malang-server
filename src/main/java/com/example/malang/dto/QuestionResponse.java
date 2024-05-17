package com.example.malang.dto;

import com.example.malang.domain.Question;
import lombok.Data;

@Data
public class QuestionResponse {

    private String content;

    public QuestionResponse(Question question) {
        this.content = question.getContent();
    }
}
