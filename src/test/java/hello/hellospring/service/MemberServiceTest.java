package hello.hellospring.service;

import hello.hellospring.domain.Member;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {


    MemberService memberService;

    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        // 각 테스트 전에 MemoryMemberRepository 만들고
        memberService = new MemberService(memberRepository);
        // MemoryMemberRepository 를 넣어줌으로 항상 같은 MemoryMemberRepository 가 사용된다
        // 왜냐하면 MemberService 는 자체적으로 new MemoryMemberRepository 를 새로운 인스턴스로 받아오는 것이 아닌
        // MemberServiceTest 에서 생성된 값을 받아오기 때문이다.
    }



    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); // 하나의 테스트종료후 clearStore 실핼시켜줌
    }


    @Test
    void 회원가입() { // 테스트 페이지는 한글써도 상관 없다
        //given 주어진 값
        Member member = new Member();
        member.setName("hello");

        //when 이걸 실행 했을 때
        Long saveId = memberService.join(member);

        //then 나오는 결과
        // 우리가 저장한 값이 repository 에 있는지 확인하고 싶음
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        // 처음 지정한 member 의 네임값이 repository 에 저장된 이름값과 같음을 확인
    }
    
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // memberService.join(member2) 이 걸 실행하면 IllegalStateException.class 예외가 터져야함 이라는 뜻
        /*
        try{
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) { // 일로 와야 성공임 MemberService 에서 미리 예외로 지정해줌
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        //then
        
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}