package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore(); // 하나의 테스트종료후 clearStore 실핼시켜줌
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member); // repository 에 저장 id값 생성

        Member result = repository.findById(member.getId()).get();

        //Assertions.assertEquals(result,member);

        //Assertions.assertEquals(result,null);
        //Expected :hello.hellospring.domain.Member@c540f5a
        //Actual   :null

        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result); // alt enter 눌러서 추가가능

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shith f6 리네임
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();// ctrl alt v 로 자동완성 가능

        assertThat(result).isEqualTo(member1);


    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
