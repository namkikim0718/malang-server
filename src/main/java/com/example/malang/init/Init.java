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
            for (int i = 0; i < 50; i++) {
                Member member = Member.builder()
                        .name("malang" + i)
                        .username("malng@inu.ac.kr")
                        .build();
                memberRepository.save(member);

            }
//            Member member = Member.builder()
//                    .name("malang")
//                    .username("malng@inu.ac.kr")
//                    .build();
//            memberRepository.save(member);

            Question question1 = new Question(QuestionType.순한맛, "순순순");
            Question question2 = new Question(QuestionType.순한맛, "한한한");
            Question question3 = new Question(QuestionType.순한맛, "맛맛맛");
            Question question4 = new Question(QuestionType.매운맛, "매매매");
            Question question5 = new Question(QuestionType.매운맛, "운운운");
            Question question6 = new Question(QuestionType.매운맛, "맛맛맛");

            for (Question question : Arrays.asList(question1, question2, question3, question4, question5, question6)) {
                questionRepository.save(question);
            }
        }
    }
}
