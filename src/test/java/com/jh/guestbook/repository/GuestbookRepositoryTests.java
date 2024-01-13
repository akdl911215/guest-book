package com.jh.guestbook.repository;

import com.jh.guestbook.entity.Guestbook;
import com.jh.guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void testQuery2() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);

        BooleanExpression exContent = qGuestbook.content.contains(keyword);

        BooleanExpression exAll = exTitle.or(exContent);
        // 1-----------------------

        builder.and(exAll); // 2---------

        builder.and(qGuestbook.gno.gt(0L)); // 3----------

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;
        // 1. 가장 먼저 동적으로 처리하기 위해서 Q도메인 클래스를 얻어온다. Q도메인 클래스를 이용하면 엔티티 클래스에 선언된
        // title, content 같은 필드들을 변수로 활용할 수 있다.

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();
        // 2. BooleanBuilder는 where문에 들어가는 조건들을 넣어주는 컨테이너라고 간주하면 된다.

        BooleanExpression expression = qGuestbook.title.contains(keyword);
        // 3. 원하는 조건은 필드 값과 같이 결합해서 생성한다. BooleanBuilder 안에 들어가는 값은 com.querydsl.core.types.Predicate
        // 타입이어야 한다. (Java에 있는 Predicate 타입이 아니므로 주의 한다.(

        builder.and(expression);
        // 4. 만들어진 조건은 where문에 and나 or같은 키워드와 결합시킨다.

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        // 5. BooleanBuilder는 GuestbookRepository에 추가된 QuerydslPredicateExcutor 인터페이스의 findAll()을 사용할 수 있다.

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void updateTest() {

        Optional<Guestbook> result = guestbookRepository.findById(300L);
        // 존재하는 번호로 테스트

        if (result.isPresent()) {

            Guestbook guestbook = result.get();

            guestbook.changeTitle("Change Title....");
            guestbook.changeContent("Changed Content....");

            guestbookRepository.save(guestbook);
        }
    }

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {

            Guestbook guestbook = Guestbook.builder()
                    .title("Tile...." + i)
                    .content("Content...." + i)
                    .writer("user" + (i % 10))
                    .build();

            System.out.println(guestbookRepository.save(guestbook));
        });
    }
}
