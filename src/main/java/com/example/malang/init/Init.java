package com.example.malang.init;

import com.example.malang.domain.Question;
import com.example.malang.domain.QuestionType;
import com.example.malang.domain.member.Member;
import com.example.malang.repository.MemberRepository;
import com.example.malang.repository.QuestionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 개발중에 데이터가 잘 들어가는지 확인하려면
 * init() 메서드에 도메인 만들고 필요한 Repo 주입 받고 save() 하면 됩니다.
 */
@Component
@RequiredArgsConstructor
public class Init {

    private final InitData initData;

    @PostConstruct
    public void init() {
        initData.init();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitData {
        private final MemberRepository memberRepository;

        private final QuestionRepository questionRepository;

        public void init() {
            if (questionRepository.findAll().size() == 0) {
                // 게임 질문 초기화
                Question question1 = new Question(QuestionType.순한맛, "버거킹 vs 맘스터치");
                Question question2 = new Question(QuestionType.순한맛, "이사하는 날 짜장면 vs 비 오는 날 파전");
                Question question3 = new Question(QuestionType.순한맛, "감자튀김 간장 찍어먹기 vs 회 케찹 찍어 먹기");
                Question question4 = new Question(QuestionType.순한맛, "피자 vs 치킨");
                Question question5 = new Question(QuestionType.순한맛, "탄산 없는 콜라 vs 치즈 없는 피자");
                Question question6 = new Question(QuestionType.순한맛, "단무지 없이 자장면 먹기 vs 김치 없이 라면 먹기");
                Question question7 = new Question(QuestionType.순한맛, "후라이드 치킨 vs 양념 치킨");
                Question question8 = new Question(QuestionType.순한맛, "짜장면 vs 짬뽕");
                Question question9 = new Question(QuestionType.순한맛, "비냉 vs 물냉");
                Question question10 = new Question(QuestionType.순한맛, "물렁물렁한 복숭아 vs 딱딱한 복숭아");
                Question question11 = new Question(QuestionType.순한맛, "찜질방, 식혜 vs 아이스커피");
                Question question12 = new Question(QuestionType.순한맛, "계란 반숙 vs 완숙");
                Question question13 = new Question(QuestionType.순한맛, "콜라 vs 사이다");
                Question question14 = new Question(QuestionType.순한맛, "찍먹 vs 부먹");
                Question question15 = new Question(QuestionType.순한맛, "산 vs 바다");
                Question question17 = new Question(QuestionType.순한맛, "똥맛 카레 vs 카레맛 똥");
                Question question18 = new Question(QuestionType.순한맛, "눈 3개 vs 눈 1개");
                Question question19 = new Question(QuestionType.순한맛, "환승 이별 vs 잠수 이별");
                Question question20 = new Question(QuestionType.순한맛, "머리카락 없는 애인 vs 머릿속 빈 애인");
                Question question21 = new Question(QuestionType.순한맛, "숨겨진 자식 있는 애인 vs 이혼 3번 한 애인");
                Question question22 = new Question(QuestionType.순한맛, "무쌍 vs 유쌍");
                Question question23 = new Question(QuestionType.순한맛, "갤럭시 vs 아이폰");

                for (Question question : Arrays.asList(question1, question2, question3, question4, question5, question6,question7,
                        question8,question9,question10,question11,question12,question13,question14,question15
                        ,question17,question18,question19,question20,question21,question22,question23)) {
                    questionRepository.save(question);
                }
            }
        }
    }
}
